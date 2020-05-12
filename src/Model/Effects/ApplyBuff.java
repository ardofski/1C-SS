package Model.Effects;

import Model.*;

public class ApplyBuff implements Effect {
    Buff buff;
    Fightable target;

    public ApplyBuff(Buff b, Fightable t){
        this.buff = b;
        this.target = t;
    }

    public Buff getBuff() {
        return buff;
    }
    public Fightable getTarget() {
        return target;
    }

    public void setTarget(Fightable t){target = t;}

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
        if( target == null ){
            str += " Character ";
        }
        else{
            str += target.getName() + "\n";
        }


        return str;
    }
}
