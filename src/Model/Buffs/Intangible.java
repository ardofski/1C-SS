package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;
import Model.Fightable;

import java.util.ArrayList;
import java.util.Stack;

public class Intangible extends Buff {

    public Intangible(int x) {
        super("Intangible",x);
        stackProperty = DURATION;
        description = "Reduce ALL damage taken and HP loss to 1 this turn. (lasts X turns ).";
    }

    /*
        Reduce ALL damage taken and HP loss to 1 this turn. (lasts X turns )
     */

    @Override
    public ArrayList<Effect> getTurnEffects(BuffDependencies dep) {
        Stack<Effect> s = dep.getEffectStack();
        Effect e = s.peek();
        if( e instanceof Damage){
            Damage d = (Damage)e;
            if( d.getTarget() == dep.getOwner() ){
                s.pop();
                d = new Damage( 1 , d.getTarget() , d.getSource() );
                s.push( d );
                return null;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
        return super.getNextTurnEffects(dep);
    }



}
