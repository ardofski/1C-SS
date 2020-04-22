package Model.Buffs;

import Model.Buff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;

import java.util.ArrayList;
import java.util.Stack;

public class Dexterity extends Buff {
    int x;
    public Dexterity(String name,int x) {
        super(name,1);
        this.x = x;
    }

    public void run(Stack<Effect> s){
        Effect e = s.peek();
        if( e instanceof Block && ((Block)e).getTarget() == null){
            s.pop();
            s.push( new Block(((Block)e).getBlock() + x,null) );

        }
    }

    public ArrayList<Effect> runNextTurn(){
        remainingTurn--;
        return null;
    }
}
