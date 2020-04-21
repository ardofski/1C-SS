package Model.Buffs;

import Model.Buff;
import Model.Effects.Effect;

import java.util.Stack;

public class Artifact extends Buff {

    public Artifact(String name) {
        super(name);
    }


    public void run(Stack<Effect> s){
        Effect e = s.peek();
    }
}
