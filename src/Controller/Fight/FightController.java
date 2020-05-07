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
    private ArrayList< Queue<ArrayList<Effect>> > enemyEffects;

    //Constructor
    public FightController(Character character, Room room) {
        super(character, room);
        turn = 0;
        enemies = ((EnemyRoom)room).getEnemies();
        //currentEnergy = 3;  //TODO change
        System.out.println( "number of cards in draw pile : " + character.getDeck().getCards().size() );
        piles = new PileCollection( new Pile(),character.getDeck().getClone() , new Pile( ) , new Pile());

        enemyEffects = new ArrayList<>();
        for( int i = 0 ; i < enemies.size() ; i++ ){
            enemyEffects.add( enemies.get(i).getEffects() );
            //System.out.println( "effects of enemy" + i + " added : " + enemies.get(i).getEffects() );
        }
        
        effectHandler = new EffectHandler(  enemies,enemyEffects,turn,3,piles,character,0);
        character.fillEnergy();


        start();

    }

    private void start(){

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

        System.out.println("IN PLAYCARD METHOD");
        boolean b = effectHandler.playCard( card , enemy);
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
            effectHandler.nextTurn();
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

        ArrayList<Effect> effects;
        for( int i = 0 ; i < enemies.size() ; i++){
            //get enemy effects from queue
            effects = enemyEffects.get(i).poll();

            //run enemy effects
            effectHandler.playEnemy( effects );

            //pass them at the back of queue
            enemyEffects.get(i).add( effects );
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
        if( character.getHp() <= 0  )return true;
        if( enemies.size() == 0)return true;
        return false;
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
