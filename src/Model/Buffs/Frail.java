package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Effects.Block;
import Model.Effects.Effect;

import java.util.ArrayList;
import java.util.Stack;

/**
 * The type Frail.
 */
public class Frail extends Buff {

    /**
     * Instantiates a new Frail.
     *
     * @param x the x
     */
    public Frail(int x) {
        super("Frail",x);
    }

    /**
     * Gets turn effects.
     *
     * @param dep the dep
     * @return the turn effects
     */
/*
    Block gained from cards is reduced by 25%.
     */
    public ArrayList<Effect> getTurnEffects(BuffDependencies dep) {
        Stack<Effect> effectStack = dep.getEffectStack();
        Effect e = effectStack.peek();
        if( e instanceof Block && ((Block)e).getTarget() == dep.getOwner()){
            effectStack.pop();
            effectStack.push( new Block((int)(((Block)e).getBlock()*0.75),dep.getOwner()) );
        }
        return null;
    }
}
