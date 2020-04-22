package Model.Effects;

import Model.Buff;

public class ChangeEnergy implements Effect {
    int energy;
    public ChangeEnergy( int e){
        energy = e;
    }

    public int getEnergy(){
        return energy;
    }

    public String toString(){
        String str = new String();
        str +=  "ChangeEnergy Effect [\n"
                +   "amount = "   + energy +    "]\n";

        return str;
    }
}
