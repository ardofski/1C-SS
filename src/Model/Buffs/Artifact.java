package Model.Buffs;

import Model.Buff;
import Model.Effects.ApplyBuff;
import Model.Effects.Effect;
import Model.Effects.EmptyEffect;

import java.util.Stack;

public class Artifact extends Buff {

    int x;
    public Artifact(String name, int x) {
        super(name);
        this.x = x;
    }

    /*
        Negates X debuffs.
    */
    public void run(Stack<Effect> s){
        Effect e = s.peek();
        if( e instanceof ApplyBuff  && true){ //TODO check if e is debuff
            if( x > 0){
                s.pop();
                s.push(new EmptyEffect() );
                x--;
            }

        }
    }
}
