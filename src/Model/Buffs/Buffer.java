package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Effects.EmptyEffect;

import java.util.ArrayList;
import java.util.Stack;

/**
 * The type Buffer.
 */
public class Buffer extends Buff {

    /**
     * The X.
     */
    int x;

    /**
     * Instantiates a new Buffer.
     *
     * @param x the x
     */
    public Buffer(int x) {
        super("Buffer",1);
        this.x = x;
        stackProperty = COUNTER;
        description = "Prevent the next X times you would lose HP.";
    }

    /*
        Prevent the next X times you would lose HP.
    */

    /**
     * Gets turn effects.
     *
     * @param dep the dep
     * @return the turn effects
     */
    @Override
    public ArrayList<Effect> getTurnEffects(BuffDependencies dep) {
        Stack<Effect> effectStack = dep.getEffectStack();
        Effect e = effectStack.peek();
        System.out.println( "In Buffer, ");
        if( e instanceof Damage && ((Damage)e).getTarget() == dep.getOwner() ){
            if( x > 0){
                effectStack.pop();
                effectStack.push(new EmptyEffect() );
                decreaseRemainingTurn();
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
