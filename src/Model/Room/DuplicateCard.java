package Model.Room;

import Model.Character;
import Model.Option;

public class DuplicateCard  extends Option {

    public DuplicateCard()
    {
        this.description = " Duplicate a card randomly in the Deck";
    }

    @Override
    public void applyOption(Character character)
    {
        // no need deep copy I guess
        int location = (int) (Math.random() * character.getDeck().getCards().size());
        character.getDeck().getCards().add(character.getDeck().getCards().get(location));
    }
    @Override
    public String toString() {
        return "description: " + this.description;
    }
}
