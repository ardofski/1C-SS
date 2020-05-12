package Model.Effects;

import Model.Effects.Effect;
import Model.Enemy;
import Model.Fightable;

public class Block implements Effect {
    private int block;
    private Fightable target;

    public Block(int block, Fightable target){
        this.block = block;
        this.target = target;
    }

    public int getBlock() {
        return block;
    }

    public Fightable getTarget(){
        return target;
    }

    public void setTarget(Fightable t){target = t;}

    public String toString(){
        String str = new String();
        str +=  "Block Effect [\n"
                +   "Target = " + target +  "\n"
                +   "amount = "   + block +    "]\n";

        return str;
    }
}

