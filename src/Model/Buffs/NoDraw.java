package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Effects.Effect;
import Model.Effects.EmptyEffect;

import java.util.ArrayList;

/**
 * The type No draw.
 */
public class NoDraw extends Buff {

    /**
     * Instantiates a new No draw.
     *
     * @param x the x
     */
    public NoDraw(int x){
        super("NoDraw",x);
    }
    /*
    You can not draw any more cards this turn.
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
        //System.out.println("heeeeeeeeeeeeeeeeeeeeeeeeeeeee"+e.getClass());
        if(e instanceof Model.Effects.DrawCard){
                System.out.println("NoDraw is active no card draws this turn.");
                dep.getEffectStack().pop();
                dep.getEffectStack().push(new EmptyEffect());
            }
        return null;
    }
}
