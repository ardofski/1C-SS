package Model.Effects;

import Model.Effects.Effect;
import Model.Enemy;
import Model.Fightable;

/**
 * The type Damage.
 */
public class Damage implements Effect {
    /**
     * The Damage.
     */
    private int damage;
    /**
     * The Target.
     */
    private Fightable target;
    /**
     * The Source.
     */
    private Fightable source;

    /**
     * Instantiates a new Damage.
     *
     * @param damage the damage
     * @param target the target
     * @param source the source
     */
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

    /**
     * Get damage int.
     *
     * @return the int
     */
    public int getDamage(){
        return damage;
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
     * Get source fightable.
     *
     * @return the fightable
     */
    public Fightable getSource(){ return source;}

    /**
     * Set damage.
     *
     * @param d the d
     */
    public void setDamage( int d ){ damage = d;}

    /**
     * Set target.
     *
     * @param t the t
     */
    public void setTarget( Fightable t){target=t;}

    /**
     * Set source.
     *
     * @param s the s
     */
    public void setSource( Fightable s){source=s;}

    /**
     * Increase damage.
     *
     * @param d the d
     */
    public void increaseDamage(int d){damage +=d;}

    /**
     * To string string.
     *
     * @return the string
     */
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
