package Model.Effects;

import Model.*;

/**
 * The type Apply buff.
 */
public class ApplyBuff implements Effect {
    /**
     * The Buff.
     */
    Buff buff;
    /**
     * The Target.
     */
    Fightable target;

    /**
     * Instantiates a new Apply buff.
     *
     * @param b the b
     * @param t the t
     */
    public ApplyBuff(Buff b, Fightable t){
        this.buff = b;
        this.target = t;
    }

    /**
     * Gets buff.
     *
     * @return the buff
     */
    public Buff getBuff() {
        return buff;
    }

    /**
     * Gets target.
     *
     * @return the target
     */
    public Fightable getTarget() {
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
        str +=  "ApplBuff Effect [\n";
        str += "Target = ";
        if( target == null ){
            str += " Character ";
        }
        else{
            str += target.getName() + "\n";
        }

        str += "Buff = ";
        str += buff.getName() + "\n";

        return str;
    }
}
