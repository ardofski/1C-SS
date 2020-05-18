package Model.Options;

import Model.Character;
import Model.Options.Option;

/**
 * The type Ignore.
 */
public class Ignore extends Option
{
    /**
     * Instantiates a new Ignore.
     */
    public Ignore()
    {
        this.description = " Do nothing and leave.";
    }

    /**
     * Apply option.
     *
     * @param character the character
     */
    @Override
    public void applyOption(Character character) {
        //Do nothing
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
