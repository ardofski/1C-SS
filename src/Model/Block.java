package Model;

public class Block implements Effect {
    private int block;
    private Enemy target;

    public Block(int block, Enemy target){
        this.block = block;
        this.target = target;
    }


}

