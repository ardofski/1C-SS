package Model.Options;

import Model.Character;
import Model.Options.Option;
import Model.Relics.Relic;
import Model.Relics.RelicFactory;

import java.util.ArrayList;

/**
 * The type Give relic random.
 */
public class GiveRelicRandom extends Option {

    /**
     * The All relics.
     */
    private ArrayList<Relic> allRelics;

    /**
     * Instantiates a new Give relic random.
     */
    public GiveRelicRandom()
    {
        this.allRelics = RelicFactory.getAllRelics();
        this.description = "Receive a random Relic";
    }

    /**
     * Apply option.
     *
     * @param character the character
     */
    @Override
    public void applyOption(Character character) {
        boolean done = false;
        do {
            int range = allRelics.size();
            int location = (int) (Math.random() * range);
            done = character.addRelic(allRelics.get(location));
        }while (!done);
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
