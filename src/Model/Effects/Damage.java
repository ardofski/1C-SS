package Model.Effects;

import Model.Effects.Effect;
import Model.Enemy;
import Model.Fightable;

public class Damage implements Effect {
    private int damage;
    private Fightable target;
    private Fightable source;
    public Damage(int damage, Fightable target, Fightable source){
        this.damage = damage;
        this.target = target;
        this.source = source;
    }

    /*
    public Damage( int damage,Fightable target ){
        new Damage(damage,target,null);
    }
     */

    public int getDamage(){
        return damage;
    }
    public Fightable getTarget(){
        return target;
    }
    public Fightable getSource(){ return source;}

    public void setDamage( int d ){ damage = d;}
    public void setTarget( Fightable t){target=t;}
    public void setSource( Fightable s){source=s;}

    public String toString(){
        String str = new String();
        str +=  "Damage Effect [\n"
                +   "Source = " + source +  "\n"
                +   "Target = " + target +  "\n"
                +   "amount = "   + damage +    "]\n";

        return str;
    }

}
