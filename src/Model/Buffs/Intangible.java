package Model.Buffs;

import Model.Buff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;
import java.util.Stack;

public class Intangible extends Buff {

    public Intangible(String name, int x) {

        super(name,x);
    }

    /*
        Reduce ALL damage taken and HP loss to 1 this turn. (lasts X turns )
     */

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

    public ArrayList<Effect> runNextTurn(){
        remainingTurn--;
        return null;
    }


}
