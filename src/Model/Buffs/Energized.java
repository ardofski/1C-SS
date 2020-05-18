package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Effects.ChangeEnergy;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Energized.
 */
public class Energized extends Buff {

    /**
     * Instantiates a new Energized.
     *
     * @param x the x
     */
    public Energized(int x) {
        super("Energized",x);
        stackProperty = INTENSITY;
        description = "Gain X additional Energy next turn.";
    }

    /*
     	Gain X additional Energy next turn.
    */

    /**
     * Gets next turn effects.
     *
     * @param dep the dep
     * @return the next turn effects
     */
    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
        System.out.println("hereeeeeeeeeeee"+ x);
        ArrayList<Effect> effects = new ArrayList<Effect>();
        ChangeEnergy ce = new ChangeEnergy( x );
        effects.add( ce );
        //setX(0);
        return effects;
    }

}
