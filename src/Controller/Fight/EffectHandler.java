package Controller.Fight;

import Model.*;
import Model.Character;
import Model.Effects.*;
import Model.Relics.Relic;


import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class EffectHandler {

    //instances
    private ArrayList<Enemy> enemies;
    private EnemyController enemyController;
    private Integer turn ;
    private PileCollection piles;
    private Character character;

    private CardEffectManager cardEffectManager;
    private BuffManager buffManager;
    private RelicManager relicManager;
    private PotionController potionController;

    private Stack<Effect> effectStack;
    private Stack<Effect> nextTunEffectStack;

    public EffectHandler(ArrayList<Enemy> enemies,EnemyController eC,
                         Integer turn, Integer currentEnergy, PileCollection piles,
                         Character character
    ){

        this.enemies = enemies;
        this.enemyController = eC;
        this.turn = turn;
        this.piles = piles;
        this.character = character;
        effectStack = new Stack<>();
        cardEffectManager = new CardEffectManager(enemies,turn,currentEnergy,piles,character);
        buffManager = new BuffManager(enemies,turn,currentEnergy,piles,character,effectStack);
        relicManager = new RelicManager(character);
        potionController = new PotionController();
        nextTunEffectStack = new Stack<>();
    }

    public void startFight(){
        ArrayList<Effect> beginingOfFightEffects = relicManager.applyBeginingOfFightEffects(effectStack,enemies);
        for( int i = 0 ; i < beginingOfFightEffects.size() ; i++){
            effectStack.push( beginingOfFightEffects.get(i) );
        }
        runStartStack();
    }

    public boolean playCard(Card card,Enemy target){
        CardDependencies dependencies = new CardDependencies(target,piles,character,enemies);
        if( !card.isPlayable(dependencies) )return false;

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

    public void playPotion( Potion p, Enemy target){
        Effect potionEffect = potionController.getPotionEffect(target,p,character);
        applyEffect( potionEffect );
    }

    public void playEnemy( int enemyIndex ){
        ArrayList<Effect> enemyEffects = enemyController.getEnemyEffects(enemyIndex);
        for( int i = 0 ; i < enemyEffects.size() ; i++){
            effectStack.push( enemyEffects.get(i) );
        }
        runStack();
    }

    public void endPlayerTurn(){
        //effectStack.push( new ChangeEnergy(3) );

        ArrayList<Effect> nextTurnEffects;
        nextTurnEffects = buffManager.getCharacterNextTurnEffects();
        for( int i = 0 ; i < nextTurnEffects.size(); i++ ){
            effectStack.push( nextTurnEffects.get(i) );
        }

        buffManager.cleanBuffs();
        runStartStack();
    }

    public void endEnemyTurn( int enemyIndex){
        Enemy enemy = enemyController.getEnemy( enemyIndex );
        ArrayList<Effect> enemyEffects = buffManager.getEnemyNextTurnEffect(enemy);
        for( int i = 0 ; i < enemyEffects.size() ; i++){
            effectStack.push( enemyEffects.get(i) );
        }

        buffManager.cleanBuffs();
        runStartStack();
    }

    public void endGame(){
        ArrayList<Effect> endEffects =  relicManager.getEndOfFightEffects(effectStack,enemies);
        for( int i = endEffects.size()-1; i >= 0 ; i-- ){
            effectStack.push( endEffects.get(i) );
        }
        runStartStack();
    }

    /*
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
    */


    private void runStack(){

        while( !effectStack.isEmpty() ){
            ArrayList<Effect> buffEffects;
            ArrayList<Effect> relicEffects;
            //read all affects considering the top of stack


            //System.out.println( "in stack effect is " + effect );
            buffEffects = buffManager.getTurnEffects();
            relicEffects = relicManager.getTurnEffects(effectStack,enemies);
            Effect effect = effectStack.pop();


            buffEffects.add(0,effect);
            buffEffects.addAll(relicEffects);

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
        else if(effect instanceof RemoveBlock)
            applyRemoveBlockEffect( (RemoveBlock) effect );
        else if(effect instanceof Heal)
            applyHealEffect((Heal)effect);
        removeDeadEnemies();
    }

    private void applyDamageEffect(Damage damage){
        System.out.println( "apply damage .. " + damage);
        //if target is caracter, decrease character block and hp
        Fightable target = damage.getTarget();
        int damageAmount = damage.getDamage();
        int blockDamage = Math.min( target.getBlock(), damageAmount );
        target.decreaseBlock( blockDamage);
        damageAmount -= blockDamage;
        if( damageAmount > 0){
            target.decreaseHp( damageAmount );
        }

    }


    /**
     * applys the given Block effect to correct target
     * @param blockEffect amount of block
     */
    public void applyBlockEffect(Block blockEffect){
        Fightable target = blockEffect.getTarget();
        if(blockEffect.getBlock()>0) {

            target.increaseBlock(blockEffect.getBlock());
        }
        else
            target.decreaseBlock(-(blockEffect.getBlock()));
    }

    /**
     * applys the given energy effect to character
     * @param energy
     */
    public void applyEnergyEffect(ChangeEnergy energy){
        character.increaseEnergy( energy.getEnergy() );
    }

    private void applyBuffEffect(ApplyBuff applyBuff){
        Fightable target = applyBuff.getTarget();
        //System.out.println("*********BUFF*****: "+applyBuff.getBuff());
        //System.out.println("*********TARGET*****: "+target);
        //apply given buff to character or enemy
        target.addBuff( applyBuff.getBuff() );
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

    private void applyRemoveBlockEffect(RemoveBlock removeBlock){
        int block = removeBlock.getTarget().getBlock();
        removeBlock.getTarget().decreaseBlock( block );
    }

    private void applyHealEffect(Heal healEffect ){
        character.increaseHp( healEffect.getHealAmount() );
    }

    private void removeDeadEnemies(){
        enemyController.removeDeadEnemies();
    }

}
