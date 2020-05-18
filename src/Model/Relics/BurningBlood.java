package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Effects.Effect;
import Model.Effects.Heal;

import java.util.ArrayList;

/**
 * The type Burning blood.
 */
public class BurningBlood extends Relic {
    /**
     * Instantiates a new Burning blood.
     */
//
    public BurningBlood(){
        name = "BurningBlood";
        description = "At the end of combat, heal 6 HP.";
        type = "Starter";
        price = 0;
    }

    /**
     * Gets end of fight effects.
     *
     * @param dep the dep
     * @return the end of fight effects
     */
    @Override
    public ArrayList<Effect> getEndOfFightEffects(RelicDependencies dep) {
        ArrayList<Effect> effects = new ArrayList<Effect>();
        System.out.println( "INSIDE END EFFECTS OF BUFNING BLOOD.");
        effects.add(new Heal(6));
        return effects;
    }
}
