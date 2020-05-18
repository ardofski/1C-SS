package Model.Options;

import Model.Character;
import Model.Options.Option;

/**
 * The type Increment max hp.
 */
public class IncrementMaxHp extends Option {
    /**
     * Instantiates a new Increment max hp.
     */
    public IncrementMaxHp()
    {
        this.description = "Increment Max Hp by 5";
    }

    /**
     * Apply option.
     *
     * @param character the character
     */
    @Override
    public void applyOption(Character character) {
        character.setMaxHp(character.getMaxHp()+5);
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
