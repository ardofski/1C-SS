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

    public Intangible(String name, int x) {

        super(name,x);
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
                Fightable source = d.getSource();
                d = new Damage( 1 , source , dep.getOwner() );
                s.push( d );
                return null;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
        x--;
        return null;
    }

    /*
    public ArrayList<Effect> run(Stack<Effect> s, Enemy owner ){
        Effect e = s.peek();
        if( e instanceof Damage){
            Damage d = (Damage)e;
            if( d.getTarget() == owner ){
                s.pop();
                Enemy source = d.getSource();
                d = new Damage( 1 , source , owner );
                s.push( d );
                return null;
            }
        }
        return null;
    }
    */


    public ArrayList<Effect> runNextTurn(){
        remainingTurn--;
        return null;
    }


}
