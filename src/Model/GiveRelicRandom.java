package Model;

import Model.Relics.Relic;
import Model.Relics.RelicFactory;

import java.util.ArrayList;

public class GiveRelicRandom extends Option {

    private ArrayList<Relic> allRelics;
    public GiveRelicRandom()
    {
        this.allRelics = RelicFactory.getAllRelics();
        this.description = "Receive a Relic";
    }
    @Override
    public void applyOption(Character character) {
            int range = allRelics.size();
            int location = ( int ) (Math.random()* range);
            character.getRelics().add(allRelics.get(location));
    }
    @Override
    public String toString() {
        return "description: " + this.description;
    }
}
