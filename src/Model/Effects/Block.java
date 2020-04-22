package Model.Effects;

import Model.Effects.Effect;
import Model.Enemy;

public class Block implements Effect {
    private int block;
    private Enemy target;

    public Block(int block, Enemy target){
        this.block = block;
        this.target = target;
    }

    public int getBlock() {
        return block;
    }

    public Enemy getTarget(){
        return target;
    }

    public String toString(){
        String str = new String();
        str +=  "Block Effect [\n"
                +   "Target = " + target +  "\n"
                +   "amount = "   + block +    "]\n";

        return str;
    }
}

