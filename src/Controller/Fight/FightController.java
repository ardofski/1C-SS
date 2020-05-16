package Controller.Fight;

import Controller.RoomController;
import Model.*;
import Model.Character;
import Model.Room.EnemyRoom;
import Model.Room.Room;
import Model.Effects.Effect;

import java.util.ArrayList;
import java.util.Queue;

public class FightController extends RoomController {

    //instances
    private ArrayList<Enemy> enemies;
    private Integer turn;

    private PileCollection piles;
    private EffectHandler effectHandler;

    private EnemyController enemyController;

    Reward reward;

    boolean cardRewardGiven;
    boolean relicRewardGiven;
    boolean goldRewardGiven;

    //Constructor
    public FightController(Character character, Room room) {
        super(character, room);
        cardRewardGiven = false;
        relicRewardGiven = false;
        goldRewardGiven = false;
        turn = 0;
        enemies = ((EnemyRoom)room).getEnemies();
        enemyController = new EnemyController(room,character);
        System.out.println( "number of cards in draw pile : " + character.getDeck().getCards().size() );
        piles = new PileCollection( new Pile(),character.getDeck().getClone() , new Pile( ) , new Pile());

        effectHandler = new EffectHandler(  enemies,enemyController,turn,3,piles,character);
        character.fillEnergy();


        start();

    }

    private void start(){
        piles.getDrawPile().shuffle();
        for(int i = 1 ; i <= 5 ; i++ ){
            piles.drawCard();
        }

    }

    /**
    This function will apply the effects of given card to given enemy.
    card: card that is played to enemy.
    enemy: target of the played card.
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
    Apply the effects of given card.
    card: played card
     */
    public boolean playCard(Card card){
        boolean b = effectHandler.playCard( card , null);
        return b;
    }

    /**
     Finishes the current turn.
     */
    public void endTurn(){
        if( !isGameOver() ){
            effectHandler.endPlayerTurn();
            piles.handToDiscard();
            playEnemy();
            character.removeBlock( );
            turn++;
            character.fillEnergy();
            //TODO change draw cards system

            for(int i = 1 ; i <= 5 ; i++ ){
                piles.drawCard();
            }
        }
    }

    /**
     Plays the enemies one by one in order and applies the effect of them.
     */
    public void playEnemy(){
        for(int i = 0 ; i < enemyController.getSize() ; i++){
            effectHandler.playEnemy( i );
        }
        for( int i = 0 ; i < enemyController.getSize() ; i++){
            effectHandler.endEnemyTurn(i);
        }
    }


    /**
     Returns the hand pile of the character.
     */
    public Pile getHandPile(){
        return piles.getHandPile();
    }

    public void setRoom(EnemyRoom eR)
    {
      room = eR;
    }

    /**
     Returns the discard pile of the character.
     */
    public Pile getDiscardPile(){
        return piles.getDiscardPile();
    }

    /**
     Returns the exhaust pile of the character.
     */
    public Pile getExhaustPile(){
        return piles.getExhaustPile();
    }

    /**
     Returns the draw pile of the character.
     */
    public Pile getDrawPile(){
        return piles.getDrawPile();
    }

    public EnemyRoom getEnemyRoom(){
        return (EnemyRoom) room;
    }

    public Character getCharacter(){
        return character;
    }

    /**
     Returns wether game is over or not.
     */
    public boolean isGameOver(){
        if( character.getHp() <= 0  ){
            System.out.println("GAME IS OVER BECAUSE CHARACTER DEAD");
            return true;
        }
        if( enemies.size() == 0){
            System.out.println("ALL ENEMIES DEAD");
            return true;
        }
        return false;
    }

    public Reward getRewards(){
        reward = ((EnemyRoom)room).giveReward();
        return reward;
    }

    public boolean takeGoldReward(){
        if(!isGameOver() || character.getHp() <= 0 )return false;
        if( goldRewardGiven )return false;
        int gold = character.getGold();
        character.setGold( gold + reward.getGold() );
        goldRewardGiven=true;
        return true;
    }

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

    public boolean takeRelicReward(){
        if(!isGameOver() || character.getHp() <= 0 )return false;
        if( relicRewardGiven )return false;
        character.getRelics().add( reward.getRelic() );
        relicRewardGiven=true;
        return true;
    }


    public void endGame(){

    }

    /**
     * applys the potion effect of given potion
     * @param potion given potion
     */
    public void applyPotion( Potion potion){
        //TODO
    }

    /**
     * applys the effect of potion to target enemy
     * @param potion given potion
     * @param enemy target enemy
     */
    public void applyPotion( Potion potion, Enemy enemy){
        //TODO

    }

    /**
     * applys the given effects in the fight
     * @param effects list of effects
     */
    private void applyEffect( Effect effects){
        //can be depracated, seems not neccesery.
    }

    public int getBlock(){
        return character.getBlock();
    }

    public int getEnergy(){
        return character.getEnergy();
    }


}
