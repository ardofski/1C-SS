package Model.Buffs;

import Model.Buff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class Vulnerable extends Buff {


    public Vulnerable(String name,int duration) {
        super(name);
        this.setRemainingTurn( duration );
    }

    /*
     * Target takes 50% more damage from attacks. 	Duration
     * */
    public ArrayList<Effect> run(Effect e, Enemy owner){
        if(e instanceof Damage) {
            Damage d = (Damage)e;
            if(d.getTarget()==owner) {
                int amount = d.getDamage();
                amount *= 1.5;
                d.setDamage( amount );
            }
        }
        return null;
    }
}
