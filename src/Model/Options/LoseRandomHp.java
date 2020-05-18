package Model.Options;

import Model.Character;
import Model.Options.Option;

/**
 * The type Lose random hp.
 */
public class LoseRandomHp extends Option {
    /**
     * Instantiates a new Lose random hp.
     */
    public LoseRandomHp()
    {
        this.description = "Lose random amount of Hp (3 - 5)";
    }

    /**
     * Apply option.
     *
     * @param character the character
     */
    @Override
    public void applyOption(Character character) {
        int hp = 3 +(int) (Math.random()*3);
        character.setHp(character.getHp()-hp);
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
