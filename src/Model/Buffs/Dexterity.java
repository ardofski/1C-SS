package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;

import java.util.ArrayList;
import java.util.Stack;

public class Dexterity extends Buff {
    public Dexterity(int x) {
        super("Dexterity",x);
        stackProperty = INTENSITY;
        description = "Increases Icon Block Block gained from cards by X.";
    }

    /*
        Increases Icon Block Block gained from cards by X.
     */
    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
        Stack<Effect> effectStack = dep.getEffectStack();
        Effect e = effectStack.peek();
        if( e instanceof Block && ((Block)e).getTarget() == dep.getOwner()){
            effectStack.pop();
            effectStack.push( new Block(((Block)e).getBlock() + x,dep.getOwner() ) );
        }
        return null;
    }

    @Override
    public ArrayList<Effect> getTurnEffects(BuffDependencies dep) {
        return super.getTurnEffects(dep);
    }
}
