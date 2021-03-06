package Controller.Fight;

import Controller.RoomController;
import Model.*;
import Model.Buffs.BuffFactory;
import Model.Cards.Card;
import Model.Character;
import Model.Effects.Block;
import Model.Effects.ChangeEnergy;
import Model.Room.EnemyRoom;
import Model.Room.Room;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Fight controller.
 */
public class FightController extends RoomController {

    /**
     * The Enemies.
     */
//instances
    private ArrayList<Enemy> enemies;
    /**
     * The Turn.
     */
    private Integer turn;

    /**
     * The Piles.
     */
    private PileCollection piles;
    /**
     * The Effect handler.
     */
    private EffectHandler effectHandler;

    /**
     * The Enemy controller.
     */
    private EnemyController enemyController;

    /**
     * The Reward.
     */
    Reward reward;

    /**
     * The Card reward given.
     */
    boolean cardRewardGiven;
    /**
     * The Relic reward given.
     */
    boolean relicRewardGiven;
    /**
     * The Gold reward given.
     */
    boolean goldRewardGiven;
    /**
     * The Pot reward given.
     */
    boolean potRewardGiven;

    /**
     * The Is game over.
     */
    boolean isGameOver;

    /**
     * Instantiates a new Fight controller.
     *
     * @param character the character
     * @param room      the room
     */
//Constructor
    public FightController(Character character, Room room) {
        super(character, room);
        cardRewardGiven = false;
        relicRewardGiven = false;
        goldRewardGiven = false;
        potRewardGiven = false;
        isGameOver = false;
        turn = 0;
        enemies = ((EnemyRoom)room).getEnemies();
        enemyController = new EnemyController(room,character);
        System.out.println( "number of cards in draw pile : " + character.getDeck().getCards().size() );
        piles = new PileCollection( new Pile(),character.getDeck().getClone() , new Pile( ) , new Pile());

        effectHandler = new EffectHandler(  enemies,enemyController,turn,3,piles,character);

        BuffFactory bF = new BuffFactory();
        //character.addBuff(bF.createBuff("Metallicize",5) );
        //character.getBuffs().getBuffs().get(0).setRemainingTurn(5);//character
     //   piles.getHandPile().addCard(CardFactory.getCard("PommelStrike"));
       // character.getBuffs().getBuffs().get(0).setX(5);

        start();

    }

    /**
     * Start. it calls the correct method of effect handler to apply the effects that are active at the
     * begginning of the fight.
     */
    private void start(){
        character.fillEnergy();
        piles.getDrawPile().shuffle();
        for(int i = 1 ; i <= 5 ; i++ ){
            piles.drawCard();
        }
        effectHandler.startFight();

    }

    /**
     * Play card. It returns true if given card can be played to given target enemy.
     * returns false otherwise.
     * @param card  the card
     * @param enemy the target enemy
     * @return true if card is played, false otherwise.
     */
    public boolean playCard(Card card, Enemy enemy){
        if( isGameOver() )return false;
        if( card.isHasTarget() && !enemyController.hasEnemy(enemy) )return false;

        System.out.println("IN PLAYCARD METHOD");
        boolean b = effectHandler.playCard( card , enemy);
        System.out.println( "HAND CARDS : ");
        for( int i = 0 ; i < piles.getHandPile().getCards().size() ; i++ ){
            System.out.println( piles.getHandPile().getCards().get(i).getName() );
        }

        return b;
    }

    /**
     * Play card. Plays the given card if card is targetless.
     * returns true if card can be played, false otherwise.
     * @param card the card
     * @return the boolean
     */
    public boolean playCard(Card card){
        boolean b = effectHandler.playCard( card , null);
        return b;
    }

    /**
     * End turn.
     * calls the effect handler methods in correct order to apply the player end enemy effects when
     * character turn is over.
     */
    public void endTurn(){
        int block = character.getBlock();
        int energy = character.getEnergy();
        if( !isGameOver() ){
            effectHandler.endPlayerTurn();
            piles.handToDiscard();
            playEnemy();
            effectHandler.startPlayerTurn();
            effectHandler.applyBlockEffect(new Block(-block,character));
            effectHandler.applyEnergyEffect(new ChangeEnergy(-energy+3));
            turn++;
            //character.fillEnergy();
            //TODO change draw cards system

            for(int i = 1 ; i <= 5 ; i++ ){
                piles.drawCard();
            }
        }
     //   System.out.println("haaaaaaaaaaaaaaaaaaaaaaaa"+character.getBlock());
    }

    /**
     * Play enemy. This method calls the effect handler methods in correct order to applys the enemy
     * effects in order.
     */
    public void playEnemy(){
      //  System.out.println("staaaaaaaaaaaaaaaaaaaaar"+character.getBlock());
        for(int i = 0 ; i < enemyController.getSize() ; i++){
            if (! enemies.get(i).isDead())
                effectHandler.playEnemy( i );
        }
        for( int i = 0 ; i < enemyController.getSize() ; i++){
            if (! enemies.get(i).isDead())
                effectHandler.endEnemyTurn(i);
        }

    }


    /**
     * Get hand pile pile.
     *
     * @return the pile
     */
    public Pile getHandPile(){
        return piles.getHandPile();
    }

    /**
     * Sets room.
     *
     * @param eR the e r
     */
    public void setRoom(EnemyRoom eR)
    {
      room = eR;
    }

    /**
     * Get discard pile pile.
     *
     * @return the pile
     */
    public Pile getDiscardPile(){
        return piles.getDiscardPile();
    }

    /**
     * Get exhaust pile pile.
     *
     * @return the pile
     */
    public Pile getExhaustPile(){
        return piles.getExhaustPile();
    }

    /**
     * Get draw pile pile.
     *
     * @return the pile
     */
    public Pile getDrawPile(){
        return piles.getDrawPile();
    }

    /**
     * Get enemy room enemy room.
     *
     * @return the enemy room
     */
    public EnemyRoom getEnemyRoom(){
        return (EnemyRoom) room;
    }

    /**
     * Get character character.
     *
     * @return the character
     */
    public Character getCharacter(){
        return character;
    }

    /**
     * Is game over boolean.
     * returns true if game is over and calls the endGame() method for it to apply end game effects.
     * returns false otherwise.
     * @return the boolean
     */
    public boolean isGameOver(){
        if( character.getHp() <= 0  ){
            System.out.println("GAME IS OVER BECAUSE CHARACTER DEAD");
            return true;
        }
        for (int i = 0 ; i < enemies.size() ; i++)
        {
            if(!enemies.get(i).isDead()){
                System.out.println("NOT ALL ENEMIES DEAD");
                return false;
            }
        }
        if( !isGameOver ){
            endGame();
            isGameOver = true;
        }
        System.out.println("GAME IS OVER BECAUSE ALL ENEMIES ARE DEAD");
        return true;
    }

    /**
     * Get rewards reward. Get the rewards of the current fight.
     *
     * @return the reward
     */
    public Reward getRewards(){
        reward = ((EnemyRoom)room).giveReward();
        return reward;
    }

    /**
     * Take gold reward.
     * Gives the fold reward of current fight to character.
     * returns true if character wins the game, false otherwise or if gold reward is already taken.
     * @return the boolean
     */
    public boolean takeGoldReward(){
        if(!isGameOver() || character.getHp() <= 0 )return false;
        if( goldRewardGiven )return false;
        int gold = character.getGold();
        character.setGold( gold + reward.getGold() );
        goldRewardGiven=true;
        return true;
    }

    /**
     * Take card reward boolean.
     * Gives the card reward of current fight to character.
     * returns true if character wins the game, false otherwise or if card reward is already taken.
     * @param i the index of card
     * @return the boolean
     */
    public boolean takeCardReward(int i){
        if(!isGameOver() || character.getHp() <= 0 )return false;
        if( cardRewardGiven )return false;
        Pile cPile = character.getDeck();
        Card c = reward.getCards().get(i);
        System.out.println("**IN takeCardReward, card name is : " + c.getName());
        character.getDeck().addCard(c);
        cardRewardGiven=true;
        return true;
    }

    /**
     * Take potion reward boolean.
     * Gives the potion reward of current fight to character.
     * returns true if character wins the game, false otherwise or if potion reward is already taken.
     * @return the boolean
     */
    public boolean takePotionReward(){
        if(!isGameOver() || character.getHp() <= 0 )return false;
        if( potRewardGiven )return false;
        boolean isAdded = character.addPotion( reward.getPot() );
        potRewardGiven=true;
        return isAdded;
    }

    /**
     * Take relic reward boolean.
     * Gives the relic reward of current fight to character.
     * returns true if character wins the game, false otherwise or if potion reward is already taken.
     * @return the boolean
     */
    public boolean takeRelicReward(){
        if(!isGameOver() || character.getHp() <= 0 )return false;
        if( relicRewardGiven )return false;
        character.addRelic( reward.getRelic() );
        relicRewardGiven=true;
        return true;
    }


    /**
     * End game. calls the effect handler to apply end game effects
     */
    public void endGame(){
        if(character.getHp() > 0 )effectHandler.endGame();
        character.clearBuffs();
    }

    /**
     * Apply potion.
     * returns true if potion is played.
     * returns false otherwise.
     * @param potion the potion
     * @return the boolean
     */
    public boolean applyPotion( Potion potion){
        if(potion.isHasTarget() )return false;
        if(!character.hasPotion(potion))return false;
        effectHandler.playPotion( potion ,null);
        character.getPotions().remove(potion);
        return true;
    }

    /**
     * Apply potion to given target.
     * returns true if potion can be applied to given enemy.
     * @param potion the potion
     * @param enemy  the target enemy
     * @return true if potion can be appleid, false otherwise
     */
    public boolean applyPotion( Potion potion, Enemy enemy){
        if(!character.hasPotion(potion))return false;
        if( potion.isHasTarget() && !enemyController.hasEnemy(enemy) )return false;
        effectHandler.playPotion( potion ,enemy);
        character.getPotions().remove(potion);
        return true;

    }

    /**
     * Get enemy effects array list.
     * Gets the enemy effects that enemy is going to be played at the next turn.
     * @param index the index of enemy
     * @return the array list of enemy of effects
     */
    public ArrayList<Effect> getEnemyEffects(int index){
        return enemyController.seeEnemyEffect(index);
    }

    /**
     * Apply effect.
     *
     * @param effects the effects
     */
    private void applyEffect( Effect effects){
        //can be depracated, seems not neccesery.
    }

    /**
     * Get block int.
     *
     * @return the int
     */
    public int getBlock(){
        return character.getBlock();
    }

    /**
     * Get energy int.
     *
     * @return the int
     */
    public int getEnergy(){
        return character.getEnergy();
    }

    /**
     * Is final room boolean.
     *
     * @return the boolean
     */
    public boolean isFinalRoom(){
        System.out.println("------------------------------------------ ->"+((EnemyRoom) room).getType());
        return ((EnemyRoom) room).getType().equals("Boss");
    }
}
