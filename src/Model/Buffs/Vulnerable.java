package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Fightable;

import java.util.ArrayList;

/**
 * The type Vulnerable.
 */
public class Vulnerable extends Buff {


    /**
     * Instantiates a new Vulnerable.
     *
     * @param x the x
     */
    public Vulnerable(int x) {
        super("Vulnerable",x);
        isDebuff = true;
        stackProperty = DURATION;
        description = "Target takes 50% more damage from attacks.";
    }

    /*
     * Target takes 50% more damage from attacks.
     * Duration
     * */

    /**
     * Gets turn effects.
     *
     * @param dep the dep
     * @return the turn effects
     */
    @Override
    public ArrayList<Effect> getTurnEffects(BuffDependencies dep) {
        Effect e = dep.getEffectStack().peek();
        Fightable owner = dep.getOwner();
        if(e instanceof Damage) {
            Damage d = (Damage)e;
            if(d.getTarget()==owner) {
                int amount = d.getDamage();
                amount *= 1.5;
                d.setDamage( amount );
            }
        }
        return null;
    }

}
