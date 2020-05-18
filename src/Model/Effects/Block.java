package Model.Effects;

import Model.Effects.Effect;
import Model.Enemy;
import Model.Fightable;

/**
 * The type Block.
 */
public class Block implements Effect {
    /**
     * The Block.
     */
    private int block;
    /**
     * The Target.
     */
    private Fightable target;

    /**
     * Instantiates a new Block.
     *
     * @param block  the block
     * @param target the target
     */
    public Block(int block, Fightable target){
        this.block = block;
        this.target = target;
    }

    /**
     * Gets block.
     *
     * @return the block
     */
    public int getBlock() {
        return block;
    }

    /**
     * Get target fightable.
     *
     * @return the fightable
     */
    public Fightable getTarget(){
        return target;
    }

    /**
     * Set target.
     *
     * @param t the t
     */
    public void setTarget(Fightable t){target = t;}

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString(){
        String str = new String();
        if(target != null)
        {
            str +=  "Block Effect [\n"
                    +   "Target = " + target.getName() +  "\n"
                    +   "amount = "   + block +    "]\n";
        }
        else
        {
            str +=  "Block Effect [\n"
                    +   "Target = " + "null" +  "\n"
                    +   "amount = "   + block +    "]\n";
        }


        return str;
    }
}

