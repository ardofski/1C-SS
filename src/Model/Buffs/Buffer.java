package Model.Buffs;

import Model.Buff;
import Model.Effects.ApplyBuff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.Stack;

public class Buffer extends Buff {

    int x;
    public Buffer(String name,int x) {
        super(name);
        this.x = x;
    }

    /*
        Prevent the next X times you would lose HP.
     */
    public void run(Stack<Effect> s, Enemy owner){
        Effect e = s.peek();
        if( e instanceof Damage && ((Damage)e).getTarget() == null ){
            if( x > 0){
                s.pop();
                x--;
            }

        }
    }
}
