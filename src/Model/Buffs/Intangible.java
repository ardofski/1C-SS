package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;
import Model.Fightable;

import java.util.ArrayList;
import java.util.Stack;

/**
 * The type Intangible.
 */
public class Intangible extends Buff {

    /**
     * Instantiates a new Intangible.
     *
     * @param x the x
     */
    public Intangible(int x) {
        super("Intangible",x);
        stackProperty = DURATION;
        description = "Reduce ALL damage taken and HP loss to 1 this turn. (lasts X turns ).";
    }

    /*
        Reduce ALL damage taken and HP loss to 1 this turn. (lasts X turns )
     */

    /**
     * Gets turn effects.
     *
     * @param dep the dep
     * @return the turn effects
     */
    @Override
    public ArrayList<Effect> getTurnEffects(BuffDependencies dep) {
        Stack<Effect> s = dep.getEffectStack();
        Effect e = s.peek();
        if( e instanceof Damage){
            Damage d = (Damage)e;
            if( d.getTarget() == dep.getOwner() ){
                s.pop();
                d = new Damage( 1 , d.getTarget() , d.getSource() );
                s.push( d );
                return null;
            }
        }
        return null;
    }

    /**
     * Gets next turn effects.
     *
     * @param dep the dep
     * @return the next turn effects
     */
    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
        return super.getNextTurnEffects(dep);
    }



}
