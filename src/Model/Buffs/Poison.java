package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Effects.Damage;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Poison.
 */
public class Poison extends Buff {

    /**
     * Instantiates a new Poison.
     *
     * @param x the x
     */
    public Poison(int x){
        super("Poison",x);
        description  = "At the beginning of the next turn, the target loses X HP and 1 stack of Poison.";
    }
    /*
    At the beginning of its turn, the target loses X HP and 1 stack of Poison.
     */

    /**
     * Gets next turn effects.
     *
     * @param dep the dep
     * @return the next turn effects
     */
    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
        Damage damage = new Damage( x,dep.getOwner(),dep.getOwner() );
        x--;
        ArrayList<Effect> effects = new ArrayList<>();
        effects.add(damage);
        return effects;
    }
}
