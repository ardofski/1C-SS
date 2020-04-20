package Controller.Fight;

import Controller.RoomController;
import Model.*;
import Model.Character;
import Model.Room.Room;
import Model.Effect;

import java.lang.reflect.Array;
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

    }
    /**
    This function will apply the effects of given card to given enemy.
    card: card that is played to enemy.
    enemy: target of the played card.
     */
    public void playCard(Card card, Enemy enemy){

    }

    /**
    Apply the effects of given card.
    card: played card
     */
    public void playCard(Card card){

    }

    /**
     Finishes the current turn.
     */
    public void endTurn(){

    }

    /**
     Plays the enemies one by one in order and applies the effect of them.
     */
    public void playEnemy(){

    }

    /**
     Returns the hand pile of the character.
     */
    public Pile getHandPile(){
        return null;
    }

    /**
     Returns the discard pile of the character.
     */
    public Pile getDiscardPile(){
        return null;
    }

    /**
     Returns the exhaust pile of the character.
     */
    public Pile getExhaustPile(){
        return null;
    }

    /**
     Returns the draw pile of the character.
     */
    public Pile getDrawPile(){
        return null;
    }

    /**
     Returns wether game is over or not.
     */
    public boolean isGameOver(){

        return false;
    }

    /**
     * applys the potion effect of given potion
     * @param potion given potion
     */
    public void applyPotion( Potion potion){

    }

    /**
     * applys the effect of potion to target enemy
     * @param potion given potion
     * @param enemy target enemy
     */
    public void applyPotion( Potion potion, Enemy enemy){


    }

    /**
     * applys the given effects in the fight
     * @param effects list of effects
     */
    private void applyEffect(ArrayList<Effect> effects){

    }

}
