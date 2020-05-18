package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Fightable;

import java.util.ArrayList;

/**
 * The type Thorns.
 */
public class Thorns extends Buff {


    /**
     * Instantiates a new Thorns.
     *
     * @param x the x
     */
    public Thorns(int x) {
        super("Thorns",x);
        stackProperty = INTENSITY;
        description = "When attacked, deals X damage back.";
    }

    /*
        When attacked, deals X damage back.
     */

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
        if( e instanceof Damage){
            Damage d = (Damage)e;
            if( d.getTarget() == owner ){
                Damage returnDamage = new Damage( x, d.getSource() , d.getTarget() );
                ArrayList<Effect> returnList = new ArrayList<Effect>();
                returnList.add(returnDamage);
                return returnList;
            }
        }
        return null;
    }

}
