package Model.Options;

import Model.Character;
import Model.Options.Option;
import Model.Relics.Relic;
import Model.Relics.RelicFactory;

import java.util.ArrayList;

public class GiveRelicRandom extends Option {

    private ArrayList<Relic> allRelics;
    public GiveRelicRandom()
    {
        this.allRelics = RelicFactory.getAllRelics();
        this.description = "Receive a random Relic";
    }
    @Override
    public void applyOption(Character character) {
        boolean done = false;
        do {
            int range = allRelics.size();
            int location = (int) (Math.random() * range);
            done = character.addRelic(allRelics.get(location));
        }while (!done);
    }
    @Override
    public String toString() {
        return "description: " + this.description;
    }
}
