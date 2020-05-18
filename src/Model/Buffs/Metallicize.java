package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Effects.Block;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Metallicize.
 */
public class Metallicize extends Buff {

    /**
     * Instantiates a new Metallicize.
     *
     * @param x the x
     */
    public Metallicize( int x) {
        super("Metallicize",x);

        stackProperty = INTENSITY;
        description = "At the end of turn, gain X Block.";
    }

    /*
         	At the end of your/its turn, gain X Block.
     */

    /**
     * Gets next turn effects.
     *
     * @param dep the dep
     * @return the next turn effects
     */
    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
       // System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxx"+x);
        Block b = new Block( x , dep.getOwner() );
        ArrayList<Effect> effects = new ArrayList<Effect>();
        effects.add( b );
        return effects;
    }

}
