package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;
import Model.Fightable;

import java.util.ArrayList;

public class Weak extends Buff {


    public Weak(int x) {
        super("Weak",x);
        isDebuff = true;
        stackProperty = DURATION;
        description = "Deals 25% less attack damage.";
    }


    /*
    *  	Target deals 25% less attack damage.
     * */
    @Override
    public ArrayList<Effect> getTurnEffects(BuffDependencies dep) {
        Effect e = dep.getEffectStack().peek();
        Fightable owner = dep.getOwner();
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
