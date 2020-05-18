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
     * @param turn          the turn of the fight
     * @param currentEnergy the current energy of character
     * @param piles         the piles of the character in the fight
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

    }


}
