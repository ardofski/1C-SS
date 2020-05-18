package Model.Options;

import Model.Character;
import Model.Options.Option;

/**
 * The type Heal hp.
 */
public class HealHp extends Option {

    /**
     * Instantiates a new Heal hp.
     */
    public HealHp()
    {
        this.description = "Heal 1/3 of your max HP.";
    }

    /**
     * Apply option.
     *
     * @param character the character
     */
    @Override
    public void applyOption(Character character) {
        character.setHp((int) (character.getHp() +  character.getMaxHp()/3));
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription()
    {
        return description;
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
