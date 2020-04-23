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
    private Integer turn,block;
    //private Integer currentEnergy; TODO
    private Pile handPile, drawPile, discardPile, exhaustPile;
    private EffectHandler effectHandler;
    private ArrayList< Queue<ArrayList<Effect>> > enemyEffects;

    //Constructor
    public FightController(Character character, Room room) {
        super(character, room);
        turn = 0;
        enemies = ((EnemyRoom)room).getEnemies();
        //currentEnergy = 3;  //TODO change
        drawPile = character.getDeck();    //Tinitilize according to cards of character.
        handPile = new Pile();
        discardPile = new Pile();
        exhaustPile = new Pile();

        enemyEffects = new ArrayList<>();
        for( int i = 0 ; i < enemies.size() ; i++ ){
            enemyEffects.add( enemies.get(i).getEffects() );
            System.out.println( "effects of enemy" + i + " added : " + enemies.get(i).getEffects() );
        }
        
        effectHandler = new EffectHandler(  enemies,enemyEffects,turn,3,handPile,drawPile,
                                            exhaustPile,discardPile,character,0);



        start();

    }

    private void start(){

        for(int i = 1 ; i <= 5 ; i++ ){
            if( drawPile.isEmpty() ){
                discardToDraw();
            }
            else{
                drawCard();
            }
        }

    }

    /**
    This function will apply the effects of given card to given enemy.
    card: card that is played to enemy.
    enemy: target of the played card.
     */
    public boolean playCard(Card card, Enemy enemy){

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
        effectHandler.nextTurn();
        ArrayList<Card> handToDiscard = handPile.takeAll();
        for( int i = 0 ; i < handToDiscard.size() ; i++){
            discardPile.addCard( handToDiscard.get(i) );
        }
        effectHandler.setBlock( 0 );
        turn++;
        playEnemy();

        //TODO change draw cards system

        for(int i = 1 ; i <= 5 ; i++ ){
            if( drawPile.isEmpty() ){
                discardToDraw();
            }
            else{
                drawCard();
            }
        }
        effectHandler.setCurrentEnergy( 3 );
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

    private boolean drawCard(){
        Card c = drawPile.takeTop();
        if( c == null )return false;
        handPile.addCard( c );
        return true;
    }

    private void discardToDraw(){
        discardPile.shuffle();
        ArrayList<Card> allCards = discardPile.takeAll();
        for( int i = 0 ; i < allCards.size(); i++){
            discardPile.addCard( allCards.get(i) );
        }
    }

    /**
     Returns the hand pile of the character.
     */
    public Pile getHandPile(){
        return handPile;
    }

    /**
     Returns the discard pile of the character.
     */
    public Pile getDiscardPile(){
        return discardPile;
    }

    /**
     Returns the exhaust pile of the character.
     */
    public Pile getExhaustPile(){
        return exhaustPile;
    }

    /**
     Returns the draw pile of the character.
     */
    public Pile getDrawPile(){
        return drawPile;
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
        return effectHandler.getBlock();
    }

    public int getEnergy(){
        return effectHandler.getCurrentEnergy();
    }


}
