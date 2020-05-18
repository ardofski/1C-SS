package Model.Effects;

import Model.Buff;

/**
 * The type Change energy.
 */
public class ChangeEnergy implements Effect {
    /**
     * The Energy.
     */
    int energy;

    /**
     * Instantiates a new Change energy.
     *
     * @param e the e
     */
    public ChangeEnergy( int e){
        energy = e;
    }

    /**
     * Get energy int.
     *
     * @return the int
     */
    public int getEnergy(){
        return energy;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString(){
        String str = new String();
        str +=  "ChangeEnergy Effect [\n"
                +   "amount = "   + energy +    "]\n";

        return str;
    }
}
