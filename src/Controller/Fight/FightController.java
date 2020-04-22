package Controller.Fight;

import Controller.RoomController;
import Model.*;
import Model.Character;
import Model.Room.Room;
import Model.Effects.Effect;

import java.util.ArrayList;

public class FightController extends RoomController {

    //instances
    private ArrayList<Enemy> enemies;
    private Integer turn, currentEnergy;
    private Pile handPile, drawPile, discardPile, exhaustPile;
    private EffectHandler effectHandler;

    //Constructor
    public FightController(Character character, Room room) {
        super(character, room);
        turn = 0;

        currentEnergy = 3;  //TODO change
        drawPile = null;    //TODO initilize according to cards of character.
        handPile = new Pile();
        discardPile = new Pile();
        exhaustPile = new Pile();
        effectHandler = new EffectHandler(  enemies,turn,currentEnergy,handPile,drawPile,
                                            exhaustPile,discardPile,character);

    }

    /**
    This function will apply the effects of given card to given enemy.
    card: card that is played to enemy.
    enemy: target of the played card.
     */
    public void playCard(Card card, Enemy enemy){

        effectHandler.playCard( card , enemy);
        
    }

    /**
    Apply the effects of given card.
    card: played card
     */
    public void playCard(Card card){
        ArrayList<Effect> effects = effectHandler.getEffect(card);
        for(int i = 0;i<effects.size();i++){
            this.applyEffect( effects.get(i) );
        }
    }

    /**
     Finishes the current turn.
     */
    public void endTurn(){
        effectHandler.nextTurn();
        turn++;
        playEnemy();
    }

    /**
     Plays the enemies one by one in order and applies the effect of them.
     */
    public void playEnemy(){
        ArrayList<Effect> enemyEffects;

        for( int i = 0 ; i < enemies.size() ; i++){
            /*
                TODO read enemy effects and apply them.
                enemyEffects = enemies.getEffects();
                for( int j = 0 ; j < enemies.size() ;j++){
                    effectHandler.applyEffect( enemyEffects.get(j));
                }
             */
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
        //TODO
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

}
