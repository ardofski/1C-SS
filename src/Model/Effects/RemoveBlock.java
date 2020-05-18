package Model.Effects;

import Model.Fightable;

/**
 * The type Remove block.
 */
public class RemoveBlock implements Effect {
    /**
     * The Target.
     */
    private Fightable target;

    /**
     * Instantiates a new Remove block.
     *
     * @param target the target
     */
    public RemoveBlock( Fightable target){
        this.target = target;
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
        String str = new String("Remove block effect");
        return str;
    }
}

