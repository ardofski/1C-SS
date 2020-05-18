package Controller.Fight;

import Model.*;
import Model.Character;
import Model.Effects.Effect;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The type Card dependencies.
 */
public class CardDependencies {
    /**
     * The Target.
     */
    Enemy target;
    /**
     * The Hand pile.
     */
    Pile handPile, /**
     * The Discard pile.
     */
    discardPile, /**
     * The Draw pile.
     */
    drawPile, /**
     * The Exhaust pile.
     */
    exhaustPile;
    /**
     * The Character.
     */
    Character character;
    /**
     * The Enemies.
     */
    ArrayList<Enemy> enemies;

    /**
     * Instantiates a new Card dependencies.
     *
     * @param t     the target of the card
     * @param piles the piles of character in the fight
     * @param c     the character
     * @param en    the enemy list
     */
    public CardDependencies(Enemy t, PileCollection piles, Character c, ArrayList<Enemy> en){
        target=t;
        handPile=piles.getHandPile();
        discardPile=piles.getDiscardPile();
        drawPile=piles.getDrawPile();
        exhaustPile=piles.getExhaustPile();
        character=c;
        enemies = en;
    }

    /**
     * Get target enemy.
     *
     * @return the enemy
     */
//getters
    public Enemy getTarget(){
        return target;
    }

    /**
     * Get hand pile pile.
     *
     * @return the pile
     */
    public Pile getHandPile(){
        return handPile;
    }

    /**
     * Get discard pile pile.
     *
     * @return the pile
     */
    public Pile getDiscardPile(){
        return discardPile;
    }

    /**
     * Get draw pile pile.
     *
     * @return the pile
     */
    public Pile getDrawPile(){
        return drawPile;
    }

    /**
     * Get exhaust pile pile.
     *
     * @return the pile
     */
    public Pile getExhaustPile(){
        return exhaustPile;
    }

    /**
     * Get character character.
     *
     * @return the character
     */
    public Character getCharacter(){return character;}

    /**
     * Get enemies array list.
     *
     * @return the array list
     */
    public ArrayList<Enemy> getEnemies(){return enemies;}
}
