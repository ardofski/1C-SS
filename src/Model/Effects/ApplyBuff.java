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
        if( target != null)
        {     str +=  "ApplyBuff Effect [\n"
                +   "Target = " + target.getName() +  "\n"
                +   "Buff = "   + buff +    "\n";
        }
        else
        {
            str +=  "ApplyBuff Effect [\n"
                    +   "Target = " + "null" +  "\n"
                    +   "Buff = "   + buff +    "\n";
        }


        return str;
    }
}
