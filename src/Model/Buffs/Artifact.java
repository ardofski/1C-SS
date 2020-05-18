package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Effects.ApplyBuff;
import Model.Effects.Effect;
import Model.Effects.EmptyEffect;

import java.util.ArrayList;
import java.util.Stack;

/**
 * The type Artifact.
 */
public class Artifact extends Buff {

    /**
     * Instantiates a new Artifact.
     *
     * @param x the x
     */
    public Artifact(int x) {
        super("Artifact",x);
        description = "Negates X debuffs.";
    }

    /*
        Negates X debuffs.
    */

    /**
     * Gets turn effects.
     *
     * @param dep the dep
     * @return the turn effects
     */
    @Override
    public ArrayList<Effect> getTurnEffects(BuffDependencies dep) {
        Stack<Effect> stack = dep.getEffectStack();
        Effect e = stack.peek();
        //check if e is debuff
        if( e instanceof ApplyBuff  && ((ApplyBuff) e).getBuff().isDebuff() ){
            if( x > 0){
                stack.pop();
                stack.push(new EmptyEffect() );
                x--;
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
        //TODO if this buff is active for one round, set x to 0
        return super.getNextTurnEffects(dep);
    }

}
