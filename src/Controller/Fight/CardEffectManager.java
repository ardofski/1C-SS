package Controller.Fight;

import Model.*;
import Model.Cards.Card;
import Model.Character;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Card effect manager.
 */
public class CardEffectManager {

    /**
     * The Enemies.
     */
//instances
    private ArrayList<Enemy> enemies;
    /**
     * The Turn.
     */
    private Integer turn, /**
     * The Current energy.
     */
    currentEnergy;
    /**
     * The Block.
     */
    private Integer block;
    /**
     * The Piles.
     */
    private PileCollection piles;
    /**
     * The Character.
     */
    private Character character;

    /**
     * Instantiates a new Card effect manager.
     *
     * @param enemies       the enemies
     * @param turn          the turn
     * @param currentEnergy the current energy
     * @param piles         the piles
     * @param character     the character
     */
    public CardEffectManager(ArrayList<Enemy> enemies,
                         Integer turn, Integer currentEnergy,
                         PileCollection piles,
                         Character character
    ){
        this.enemies = enemies;
        this.turn = turn;
        this.currentEnergy = currentEnergy;
        this.piles = piles;
        this.character = character;
    }

    /**
     * Get effects array list.
     *
     * @param card   the card
     * @param target the target
     * @return the array list
     */
    public ArrayList<Effect> getEffects(Card card, Enemy target){
        CardDependencies dependencies = new CardDependencies(target,piles,character,enemies);
        return card.play( dependencies );
        /*
        if(card instanceof Anger){
            Anger castedCard = (Anger)card;
            return castedCard.getEffects(target, handPile);
        }
        else if( card instanceof Armaments){
            Armaments castedCard = (Armaments)card;
            return castedCard.getEffects(target,handPile);
        }
        else if( card instanceof Bash){
            Bash castedCard = (Bash)card;
            return castedCard.getEffects(target);
        }
        else if( card instanceof BodySlam){
            BodySlam castedCard = (BodySlam)card;
            return castedCard.getEffects(target,block);
        }
        else if(card instanceof Defend){
            Defend castedCard = (Defend)card;
            return castedCard.getEffects(target);
        }
        else if(card instanceof Strike){
            Strike castedCard = (Strike)card;
            return castedCard.getEffects(target);
        }
        return null;
        */

    }


}
