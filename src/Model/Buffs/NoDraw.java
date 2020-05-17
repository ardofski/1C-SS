package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Effects.EmptyEffect;

import java.util.ArrayList;

public class NoDraw extends Buff {

    public NoDraw(int x){
        super("NoDraw",x);
    }
    /*
    You can not draw any more cards this turn.
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
