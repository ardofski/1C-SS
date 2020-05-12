package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.ApplyBuff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Effects.EmptyEffect;
import Model.Enemy;

import java.util.ArrayList;
import java.util.Stack;

public class Buffer extends Buff {

    int x;
    public Buffer(String name,int x) {
        super(name,1);
        this.x = x;
    }

    /*
        Prevent the next X times you would lose HP.
    */

    @Override
    public ArrayList<Effect> getTurnEffects(BuffDependencies dep) {
        Stack<Effect> effectStack = dep.getEffectStack();
        Effect e = effectStack.peek();
        if( e instanceof Damage && ((Damage)e).getTarget() == dep.getOwner() ){
            if( x > 0){
                effectStack.pop();
                effectStack.push(new EmptyEffect() );
                x--;
            }

        }
        return null;
    }

}
