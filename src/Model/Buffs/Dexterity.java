package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;

import java.util.ArrayList;
import java.util.Stack;

/**
 * The type Dexterity.
 */
public class Dexterity extends Buff {
    /**
     * Instantiates a new Dexterity.
     *
     * @param x the x
     */
    public Dexterity(int x) {
        super("Dexterity",x);
        stackProperty = INTENSITY;
        description = "Increases Block gained from cards by X.";
    }

    /**
     * Gets next turn effects.
     *
     * @param dep the dep
     * @return the next turn effects
     */
/*
        Increases Icon Block Block gained from cards by X.
     */
    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
      return super.getNextTurnEffects(dep);
    }

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
        if( e instanceof Block && ((Block)e).getTarget() == dep.getOwner()){
            System.out.println(
                    "IMHEREEEEEEE"+ ((Block)e).getBlock()+" "+x);
            effectStack.pop();
            effectStack.push( new Block(((Block)e).getBlock() + x,dep.getOwner() ) );
        }
        return null;
    }
}
