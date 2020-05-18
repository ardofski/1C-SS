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

/**
 * The type Effect handler.
 */
public class EffectHandler {

    /**
     * The Enemies.
     */
//instances
    private ArrayList<Enemy> enemies;
    /**
     * The Enemy controller.
     */
    private EnemyController enemyController;
    /**
     * The Turn.
     */
    private Integer turn ;
    /**
     * The Piles.
     */
    private PileCollection piles;
    /**
     * The Character.
     */
    private Character character;

    /**
     * The Card effect manager.
     */
    private CardEffectManager cardEffectManager;
    /**
     * The Buff manager.
     */
    private BuffManager buffManager;
    /**
     * The Relic manager.
     */
    private RelicManager relicManager;
    /**
     * The Potion controller.
     */
    private PotionController potionController;

    /**
     * The Effect stack.
     */
    private Stack<Effect> effectStack;
    /**
     * The Next tun effect stack.
     */
    private Stack<Effect> nextTunEffectStack;

    /**
     * Instantiates a new Effect handler.
     *
     * @param enemies       the enemies
     * @param eC            the enemy Controller
     * @param turn          the turn of fight
     * @param currentEnergy the current energy of character
     * @param piles         the piles of character in the fight
     * @param character     the character
     */
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

    /**
     * Start fight.
     * This method applies the effects of relics that should be active at the begining of the fight
     */
    public void startFight(){
        System.out.println("-------------------------------INSIDE START FIGHT OF EFFECT HANDLER---------------------------");
        ArrayList<Effect> beginingOfFightEffects = relicManager.applyBeginingOfFightEffects(effectStack,enemies);
        for( int i = 0 ; i < beginingOfFightEffects.size() ; i++){
            effectStack.push( beginingOfFightEffects.get(i) );
        }
        runStartStack();
    }

    /**
     * This method plays the given card to given target.
     * Returns true if card is successfully played, false if card is not played.
     * @param card   the card
     * @param target the target of the card
     * @return true if card is played, false otherwise.
     */
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

    /**
     * Play potion. This applies the potion effects to given target
     *
     * @param p      the potion
     * @param target the target
     */
    public void playPotion( Potion p, Enemy target){
        Effect potionEffect = potionController.getPotionEffect(target,p,character);
        applyEffect( potionEffect );
    }

    /**
     * Play enemy. This method applies the effects of the enemy with given index.
     *
     * @param enemyIndex the enemy ındex
     */
    public void playEnemy( int enemyIndex ){
        ArrayList<Effect> enemyEffects = enemyController.getEnemyEffects(enemyIndex);
        for( int i = 0 ; i < enemyEffects.size() ; i++){
            effectStack.push( enemyEffects.get(i) );
        }
        effectStack.push( new Block(-enemies.get(enemyIndex).getBlock(), enemies.get(enemyIndex) ));
        runStack();
    }

    /**
     * This method applies the effects that should be active at the begginning of the player.
     * Starts player turn.
     */
    public void startPlayerTurn(){

        ArrayList<Effect> startTurnEffects;
        startTurnEffects = relicManager.getStartOfTurnEffects(effectStack,enemies);
        for( int i = 0 ; i < startTurnEffects.size(); i++ ){
            effectStack.push( startTurnEffects.get(i) );
        }
        runStartStack();

    }

    /**
     * This method applies the effects if player ends its turns.
     * Ends player turn.
     */
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

    /**
     * End enemy turn. This method applies the end turn effects of the enemy with given index.
     *
     * @param enemyIndex the enemy ındex
     */
    public void endEnemyTurn( int enemyIndex){
        Enemy enemy = enemyController.getEnemy( enemyIndex );
        ArrayList<Effect> enemyEffects = buffManager.getEnemyNextTurnEffect(enemy);
        for( int i = 0 ; i < enemyEffects.size() ; i++){
            effectStack.push( enemyEffects.get(i) );
        }

        buffManager.cleanBuffs();
        runStartStack();
    }

    /**
     * End game. This method applies the effects of the game that should be active at the end of the fight.
     */
    public void endGame(){
        ArrayList<Effect> endEffects =  relicManager.getEndOfFightEffects(effectStack,enemies);
        for( int i = endEffects.size()-1; i >= 0 ; i-- ){
            effectStack.push( endEffects.get(i) );
        }
        runStartStack();
    }



    /**
     * Run stack.
     * This method runs the effect stack that is filled by card effects. For each card effect, it also
     * controls the buff effects and relic effects to handle all type of effects and appleis them one by one.
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

    /**
     * This method applies the effects in the stack but without checking buffs and relics.
     * Run start stack.
     */
    private void runStartStack(){
        Effect effect;
        while( !effectStack.isEmpty() ){
            effect = effectStack.pop();
            applyEffect( effect );
        }
    }


    /**
     * Get effect array list.
     *
     * @param card   the card
     * @param target the target
     * @return the array list
     */
    public ArrayList<Effect> getEffect(Card card, Enemy target){

        return cardEffectManager.getEffects( card,target );
    }

    /**
     * Get effect array list.
     *
     * @param card the card
     * @return the array list
     */
    public ArrayList<Effect> getEffect(Card card){

        return cardEffectManager.getEffects( card,null);
    }

    /**
     * Get potion effect effect.
     *
     * @param potion the potion
     * @return the effect
     */
    public Effect getPotionEffect( Potion potion){
        //TODO
        return null;
    }

    /**
     * Get card relic effects array list.
     *
     * @return the array list
     */
    public ArrayList<Effect> getCardRelicEffects(){
        //TODO
        return null;
    }

    /**
     * Get turn relic effects array list.
     *
     * @return the array list
     */
    public ArrayList<Effect> getTurnRelicEffects(){
        //TODO

        return null;
    }

    /**
     * Apply effect.
     * This method calls the correct apply method for given effect
     * @param effect the effect
     */
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
        //removeDeadEnemies();
    }

    /**
     * Apply damage effect.
     *
     * @param damage the damage
     */
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
     * Apply block effect.
     *
     * @param blockEffect the block effect
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
     * Apply energy effect.
     *
     * @param energy the energy
     */
    public void applyEnergyEffect(ChangeEnergy energy){
        character.increaseEnergy( energy.getEnergy() );
    }

    /**
     * Apply buff effect.
     *
     * @param applyBuff the apply buff
     */
    private void applyBuffEffect(ApplyBuff applyBuff){
        Fightable target = applyBuff.getTarget();
        //System.out.println("*********BUFF*****: "+applyBuff.getBuff());
        //System.out.println("*********TARGET*****: "+target);
        //apply given buff to character or enemy
        target.addBuff( applyBuff.getBuff() );
    }

    /**
     * Apply move card effect.
     *
     * @param moveCard the move card
     */
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

    /**
     * Apply draw card effect.
     *
     * @param drawCard the draw card
     */
    private void applyDrawCardEffect(DrawCard drawCard){
        piles.drawCard();
    }

    /**
     * Apply upgrade card effect.
     *
     * @param upgradeCard the upgrade card
     */
    private void applyUpgradeCardEffect(UpgradeCard upgradeCard){
        //apply given upgrade card effect
        Card card = upgradeCard.getCard();
        card.upgrade();

    }

    /**
     * Apply remove block effect.
     *
     * @param removeBlock the remove block
     */
    private void applyRemoveBlockEffect(RemoveBlock removeBlock){
        int block = removeBlock.getTarget().getBlock();
        removeBlock.getTarget().decreaseBlock( block );
    }

    /**
     * Apply heal effect.
     *
     * @param healEffect the heal effect
     */
    private void applyHealEffect(Heal healEffect ){
        character.increaseHp( healEffect.getHealAmount() );
    }

    /**
     * Remove dead enemies.
     */
    private void removeDeadEnemies(){
        enemyController.removeDeadEnemies();
    }

}
