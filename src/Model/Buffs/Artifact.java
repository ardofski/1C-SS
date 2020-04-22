package Model.Buffs;

import Model.Buff;
import Model.Effects.ApplyBuff;
import Model.Effects.Effect;
import Model.Effects.EmptyEffect;

import java.util.ArrayList;
import java.util.Stack;

public class Artifact extends Buff {

    int x;
    public Artifact(String name, int x) {
        super(name,1);
        this.x = x;
    }

    /*
        Negates X debuffs.
    */
    public void run(Stack<Effect> s){
        Effect e = s.peek();
        //check if e is debuff
        if( e instanceof ApplyBuff  && ((ApplyBuff) e).getBuff().isDebuff() ){
            if( x > 0){
                s.pop();
                s.push(new EmptyEffect() );
                x--;
            }
        }
    }

    public ArrayList<Effect> runNextTurn( ){
        this.remainingTurn--;
        return null;
    }
}
