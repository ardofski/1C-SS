package Model.Options;

import Model.Card;
import Model.Cards.CardFactory;
import Model.Character;
import Model.Options.Option;

import java.util.ArrayList;

public class TakeGiveRandomCard extends Option {

    private ArrayList<Card> allCards;
    public TakeGiveRandomCard()
    {
        this.description = "Take one card, give another card randomly";
        this.allCards = CardFactory.getAllCards();
    }
    @Override
    public void applyOption(Character character)
    {
        int locationRemove = (int) (character.getDeck().getCards().size()*Math.random());
        character.getDeck().getCards().remove(locationRemove);
        int locationAdd = (int) (allCards.size()*Math.random());
        character.getDeck().addCard(allCards.get(locationAdd));
    }

    @Override
    public String toString() {
        return "description: " + this.description;
    }
}
