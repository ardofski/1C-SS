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


}

