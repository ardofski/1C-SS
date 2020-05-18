package Model.Options;

import Model.Character;
import Model.Options.Option;

/**
 * The type Lose random gold.
 */
public class LoseRandomGold extends Option {

    /**
     * Instantiates a new Lose random gold.
     */
    public LoseRandomGold()
    {
        this.description = "Lose random amount of Gold (40 - 110)";
    }

    /**
     * Apply option.
     *
     * @param character the character
     */
    @Override
    public void applyOption(Character character) {
        int gold = 40 +(int) (Math.random()*70);
        character.setGold(character.getGold()-gold);
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
