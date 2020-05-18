package Model.Options;

import Model.Card;
import Model.Cards.CardFactory;
import Model.Character;
import Model.Options.Option;

import java.util.ArrayList;

/**
 * The type Take give random card.
 */
public class TakeGiveRandomCard extends Option {

    /**
     * The All cards.
     */
    private ArrayList<Card> allCards;

    /**
     * Instantiates a new Take give random card.
     */
    public TakeGiveRandomCard()
    {
        this.description = "Take one card, give another card randomly";
        this.allCards = CardFactory.getAllCards();
    }

    /**
     * Apply option.
     *
     * @param character the character
     */
    @Override
    public void applyOption(Character character)
    {
        int locationRemove = (int) (character.getDeck().getCards().size()*Math.random());
        character.getDeck().getCards().remove(locationRemove);
        int locationAdd = (int) (allCards.size()*Math.random());
        character.getDeck().addCard(allCards.get(locationAdd));
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "description: " + this.description;
    }
}
