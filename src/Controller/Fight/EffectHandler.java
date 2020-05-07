package Controller.Fight;

import Model.*;
import Model.Character;
import Model.Effects.*;
import Model.Effects.EffectFactory;


import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class EffectHandler {

    //instances
    private ArrayList<Enemy> enemies;
    private ArrayList<Queue<ArrayList<Effect>>> enemyEffects;
    private Integer turn ;
    private PileCollection piles;
    private Character character;
    private ScriptEngineManager manager;
    private ScriptEngine engine;
    private Invocable inv;
    private EffectFactory effectFactory;
    private CardEffectManager cardEffectManager;
    private BuffManager buffManager;
    private Stack<Effect> effectStack;
    private Stack<Effect> nextTunEffectStack;

    public EffectHandler(ArrayList<Enemy> enemies,ArrayList<Queue<ArrayList<Effect>>> enemyEffects,
                         Integer turn, Integer currentEnergy, PileCollection piles,
                         Character character,Integer block
    ){

        this.enemies = enemies;
        this.enemyEffects = enemyEffects;
        this.turn = turn;
        this.piles = piles;
        this.character = character;
        effectStack = new Stack<>();
        cardEffectManager = new CardEffectManager(enemies,turn,currentEnergy,piles,character);
        buffManager = new BuffManager(enemies,turn,currentEnergy,piles,character,effectStack);
        nextTunEffectStack = new Stack<>();
    }

    public boolean playCard(Card card,Enemy target){
        if( card.getEnergy() > character.getEnergy() )return false; //TODO change with card.isPlayable()

        ArrayList<Effect> cardEffects = cardEffectManager.getEffects(card , target);
        if(cardEffects != null ){
            for( int i = cardEffects.size() - 1 ; i >= 0 ; i-- ){
                effectStack.push( cardEffects.get(i) );
            }
        }

        effectStack.push( new MoveCard(piles.getHandPile(),piles.getDiscardPile(),card) );
        effectStack.push( new ChangeEnergy((-1)*card.getEnergy() ) );
        //call run stack
        runStack();
        return true;
    }

    public void playEnemy(ArrayList<Effect> enemyEffects ){
        for( int i = enemyEffects.size() -  1 ; i >= 0 ; i-- ){
            effectStack.push( enemyEffects.get(i) );
        }
        runStack();
    }

    public void nextTurn(){
        ArrayList<Effect> nextTurnEffects;
        nextTurnEffects = buffManager.getNextTurnEffects();
        for( int i = 0 ; i < nextTurnEffects.size(); i++ ){
            effectStack.push( nextTurnEffects.get(i) );
        }
        effectStack.push( new ChangeEnergy(3) );
        buffManager.cleanBuffs();
        runStartStack();
    }

    private void runStack(){

        while( !effectStack.isEmpty() ){
            ArrayList<Effect> buffEffects;

            //read all affects considering the top of stack

            Effect effect = effectStack.peek();
            System.out.println( "in stack effect is " + effect );
            buffEffects = buffManager.nextEffects();
            effectStack.pop();
            buffEffects.add(0,effect);

            //run all effects in the stack

            for( int i = 0 ; i < buffEffects.size() ; i++){

                applyEffect( buffEffects.get(i) );
            }

        }
    }

    private void runStartStack(){
        Effect effect;
        while( !effectStack.isEmpty() ){
            effect = effectStack.pop();
            applyEffect( effect );
        }
    }


    public ArrayList<Effect> getEffect(Card card, Enemy target){

        return cardEffectManager.getEffects( card,target );
    }

    public ArrayList<Effect> getEffect(Card card){

        return cardEffectManager.getEffects( card,null);
    }

    public Effect getPotionEffect( Potion potion){
        //TODO
        return null;
    }

    public ArrayList<Effect> getCardRelicEffects(){
        //TODO
        return null;
    }

    public ArrayList<Effect> getTurnRelicEffects(){
        //TODO

        return null;
    }

    public void applyEffect( Effect effect){
        if(effect instanceof Damage){
            applyDamageEffect( (Damage)effect );
        }
        else if(effect instanceof Block){
            applyBlockEffect((Block)effect);
        }
        else if(effect instanceof ApplyBuff){
            applyBuffEffect((ApplyBuff) effect);
        }
        else if(effect instanceof ChangeEnergy){
            applyEnergyEffect((ChangeEnergy) effect);
        }
        else if(effect instanceof MoveCard){
            applyMoveCardEffect((MoveCard) effect);
        }
        else if(effect instanceof UpgradeCard ){
            applyUpgradeCardEffect((UpgradeCard) effect);
        }
        else if(effect instanceof DrawCard){
            applyDrawCardEffect( (DrawCard) effect);
        }
        removeDeadEnemies();
    }

    private void applyDamageEffect(Damage damage){
        System.out.println( "apply damage .. ");
        //if target is caracter, decrease character block and hp
        if( damage.getTarget() == null ){
            int damageAmount = damage.getDamage();
            int blockDamage = Math.min( character.getBlock(), damageAmount );
            character.decreaseBlock( blockDamage);
            damageAmount -= blockDamage;

            if( damageAmount > 0){
                character.decreaseHp( damageAmount );
            }

        }
        //if target is enemy, decrease enemy block and hp
        else{
            int damageAmount = damage.getDamage();
            Enemy target = damage.getTarget();
            int enemyBlock = target.getBlock();
            int blockDamage = Math.min( enemyBlock , damageAmount );
            enemyBlock -= blockDamage;
            damageAmount -= blockDamage;
            if( damageAmount > 0) {
                target.decreaseHP(damageAmount);
            }
        }
    }


    /**
     * applys the given Block effect to correct target
     * @param blockEffect amount of block
     */
    private void applyBlockEffect(Block blockEffect){
        Enemy target = blockEffect.getTarget();

        if( target == null){
            character.addBlock( blockEffect.getBlock() );
        }
        else{
            int enemyBlock = target.getBlock();
            enemyBlock += blockEffect.getBlock();
            target.setHp( enemyBlock );
        }
    }

    /**
     * applys the given energy effect to character
     * @param energy
     */
    private void applyEnergyEffect(ChangeEnergy energy){
        character.increaseEnergy( energy.getEnergy() );
    }

    private void applyBuffEffect(ApplyBuff applyBuff){
        Enemy target = applyBuff.getTarget();
        System.out.println("*********BUFF*****: "+applyBuff.getBuff().getName());
        System.out.println("*********TARGET*****: "+target);
        //apply given buff to character
        if( target == null ){
            ArrayList<Buff> buffs = character.getBuffs();
            buffs.add( applyBuff.getBuff() );
        }
        //apply given buff to enemy
        else{
            ArrayList<Buff> buffs = target.getBuffs();
            buffs.add( applyBuff.getBuff() );
        }
    }

    //apply given move card effect
    private void applyMoveCardEffect(MoveCard moveCard){
        Pile source = moveCard.getSourcePile();
        Pile dest = moveCard.getDestPile();
        Card c = moveCard.getCard();
        if( source != null ){
            source.removeCard( c );
        }

        dest.addCard( c );
    }
    private void applyDrawCardEffect(DrawCard drawCard){
        piles.drawCard();
    }

    private void applyUpgradeCardEffect(UpgradeCard upgradeCard){
        //apply given upgrade card effect
        Card card = upgradeCard.getCard();
        card.upgrade();

    }

    private void removeDeadEnemies(){

        for( int i = 0 ; i < enemies.size() ; i++ ){
            if( enemies.get(i).getHp() <= 0 ){
                System.out.println( "remove:" + enemies.get(i).getName() );
                enemies.remove(i);
                enemyEffects.remove(i);
            }
        }
    }

}
