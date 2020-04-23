package Model.Effects;

import Model.*;

public class ApplyBuff implements Effect {
    Buff buff;
    Enemy target;

    public ApplyBuff(Buff b, Enemy t){
        this.buff = b;
        this.target = t;
    }

    public Buff getBuff() {
        return buff;
    }

    public Enemy getTarget() {
        return target;
    }

    public String toString(){
        String str = new String();
        str +=  "ApplBuff Effect [\n"
            +   "Target = " + target.getName() +  "\n"
            +   "Buff = "   + buff.getName() +    "\n";

        return str;
    }
}
