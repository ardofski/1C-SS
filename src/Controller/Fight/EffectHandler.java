package Controller.Fight;

import Model.*;
import Model.Character;
import Model.Effects.*;
import Model.Effects.EffectFactory;


import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.Stack;

public class EffectHandler {

    //instances
    private ArrayList<Enemy> enemies;
    private Integer turn, currentEnergy;
    private Integer block;
    private Pile handPile, drawPile, exhaustPile, discardPile;
    private Character character;
    private ScriptEngineManager manager;
    private ScriptEngine engine;
    private Invocable inv;
    private EffectFactory effectFactory;
    private CardEffectManager cardEffectManager;
    private BuffManager buffManager;
    private Stack<Effect> effectStack;
    private Stack<Effect> nextTunEffectStack;

    public EffectHandler(ArrayList<Enemy> enemies,
                         Integer turn, Integer currentEnergy,
                         Pile handPile, Pile drawPile, Pile exhaustPile, Pile discardPile,
                         Character character,Integer block
    ){
        this.enemies = enemies;
        this.turn = turn;
        this.currentEnergy = currentEnergy;
        this.handPile = handPile;
        this.drawPile = drawPile;
        this.exhaustPile = exhaustPile;
        this.discardPile = discardPile;
        this.character = character;
        effectStack = new Stack<Effect>();
        cardEffectManager = new CardEffectManager(enemies,turn,currentEnergy,handPile,drawPile,exhaustPile,discardPile,character);
        buffManager = new BuffManager(enemies,turn,currentEnergy,handPile,drawPile,exhaustPile,discardPile,character,effectStack);
        this.block = block;
        nextTunEffectStack = new Stack<Effect>();
    }

    public boolean playCard(Card card,Enemy target){
        if( card.getEnergy() > currentEnergy )return false;

        ArrayList<Effect> cardEffects = cardEffectManager.getEffects(card , target);
        for( int i = cardEffects.size() - 1 ; i >= 0 ; i-- ){
            effectStack.push( cardEffects.get(i) );
        }
        effectStack.push( new MoveCard(handPile,discardPile,card) );
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
    }

    private void applyDamageEffect(Damage damage){
        System.out.println( "apply damage .. ");
        //if target is caracter, decrease character block and hp
        if( damage.getTarget() == null ){
            int damageAmount = damage.getDamage();
            int blockDamage = Math.min( block, damageAmount );
            block -=  blockDamage;
            damageAmount -= blockDamage;

            if( damageAmount > 0){
                int characterHP = character.getHp();
                characterHP -= damageAmount;
                character.setHp( characterHP );
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
                int enemyHP = target.getHp();
                enemyHP -= damageAmount;
                target.setHp( enemyHP );
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
            this.block += blockEffect.getBlock();
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
        currentEnergy += energy.getEnergy();
    }


    private void applyBuffEffect(ApplyBuff applyBuff){
        Enemy target = applyBuff.getTarget();

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
        Card c= drawPile.takeTop();
        handPile.addCard(c);
    }

    private void applyUpgradeCardEffect(UpgradeCard upgradeCard){
        //apply given upgrade card effect
        Card card = upgradeCard.getCard();
        card.upgrade();

    }

    public int getBlock(){
        return block;
    }

    public int getCurrentEnergy(){
        return currentEnergy;
    }

    public void setCurrentEnergy(int e){
        currentEnergy = e;
    }

    public void setBlock(int b){
        block = b;
    }

}
