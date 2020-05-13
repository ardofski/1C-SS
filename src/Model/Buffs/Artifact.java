package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.ApplyBuff;
import Model.Effects.Effect;
import Model.Effects.EmptyEffect;

import java.util.ArrayList;
import java.util.Stack;

public class Artifact extends Buff {

    public Artifact(int x) {
        super("Artifact",x);
    }

    /*
        Negates X debuffs.
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

    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
        //TODO if this buff is active for one round, set x to 0
        return super.getNextTurnEffects(dep);
    }

}
