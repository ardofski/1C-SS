package Model.Effects;

import Model.Effects.Effect;
import Model.Enemy;

public class Damage implements Effect {
    private int damage;
    private Enemy target;
    private Enemy source;
    public Damage(int damage, Enemy target, Enemy source){
        this.damage = damage;
        this.target = target;
        this.source = source;
    }

    public Damage( int damage,Enemy target ){
        new Damage(damage,target,null);
    }

    public int getDamage(){
        return damage;
    }
    public Enemy getTarget(){
        return target;
    }
    public Enemy getSource(){ return source;}
    public void setDamage( int d ){ damage = d;}

    public String toString(){
        String str = new String();
        if(source != null && target != null)
        {
            str +=  "Damage Effect [\n"
                    +   "Source = " + source.getName() +  "\n"
                    +   "Target = " + target.getName() +  "\n"
                    +   "amount = "   + damage +    "]\n";
        }
        else if (source == null && target != null)
        {
            str +=  "Damage Effect [\n"
                    +   "Source = " + "null" +  "\n"
                    +   "Target = " + target.getName() +  "\n"
                    +   "amount = "   + damage +    "]\n";
        }
        else if(source != null && target == null)
        {
            str +=  "Damage Effect [\n"
                    +   "Source = " + source.getName() +  "\n"
                    +   "Target = " + "null" +  "\n"
                    +   "amount = "   + damage +    "]\n";
        }
        else
        {
            str +=  "Damage Effect [\n"
                    +   "Source = " + "null" +  "\n"
                    +   "Target = " + "null" +  "\n"
                    +   "amount = "   + damage +    "]\n";
        }


        return str;
    }

}
