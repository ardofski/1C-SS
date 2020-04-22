package Model.Buffs;

import Model.Buff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class Weak extends Buff {


    public Weak(String name,int duration) {
        super(name);
        this.setRemainingTurn(duration);
    }


    /*
    *  	Target deals 25% less attack damage.
     * */
    public ArrayList<Effect> run(Effect e, Enemy owner){
        if(e instanceof Damage) {
            Damage d = (Damage)e;
            if(d.getSource() == owner) {
                int amount = d.getDamage();
                amount *= 0.75;
                d.setDamage( amount );
            }
        }
        return null;
    }
}
