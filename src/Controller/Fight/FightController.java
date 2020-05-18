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
     * Start.
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
     * Play card boolean.
     *
     * @param card  the card
     * @param enemy the enemy
     * @return the boolean
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
     * Play card boolean.
     *
     * @param card the card
     * @return the boolean
     */
    public boolean playCard(Card card){
        boolean b = effectHandler.playCard( card , null);
        return b;
    }

    /**
     * End turn.
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
     * Play enemy.
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
     *
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
     * Get rewards reward.
     *
     * @return the reward
     */
    public Reward getRewards(){
        reward = ((EnemyRoom)room).giveReward();
        return reward;
    }

    /**
     * Take gold reward boolean.
     *
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
     *
     * @param i the
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
     *
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
     *
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
     * End game.
     */
    public void endGame(){
        if(character.getHp() > 0 )effectHandler.endGame();
        character.clearBuffs();
    }

    /**
     * Apply potion boolean.
     *
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
     * Apply potion boolean.
     *
     * @param potion the potion
     * @param enemy  the enemy
     * @return the boolean
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
     *
     * @param index the index
     * @return the array list
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
