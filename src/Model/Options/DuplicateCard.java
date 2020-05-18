package Model.Options;

import Model.Character;
import Model.Options.Option;

/**
 * The type Duplicate card.
 */
public class DuplicateCard  extends Option {

    /**
     * Instantiates a new Duplicate card.
     */
    public DuplicateCard()
    {
        this.description = " Duplicate a card randomly in the Deck";
    }

    /**
     * Apply option.
     *
     * @param character the character
     */
    @Override
    public void applyOption(Character character)
    {
        // no need deep copy I guess
        int location = (int) (Math.random() * character.getDeck().getCards().size());
        character.getDeck().getCards().add(character.getDeck().getCards().get(location));
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
