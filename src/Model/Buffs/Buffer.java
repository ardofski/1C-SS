package Model.Buffs;

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
    public void run(Stack<Effect> s, Enemy owner){
        Effect e = s.peek();
        if( e instanceof Damage && ((Damage)e).getTarget() == owner ){
            if( x > 0){
                s.pop();
                s.push(new EmptyEffect() );
                x--;
            }

        }
    }

    public ArrayList<Effect> runNextTurn(){
        this.remainingTurn--;
        return null;
    }
}
