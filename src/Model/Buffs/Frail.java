package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Effects.EmptyEffect;

import java.util.ArrayList;
import java.util.Stack;

public class Frail extends Buff {

    public Frail(int x) {
        super("Frail",x);
    }
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
