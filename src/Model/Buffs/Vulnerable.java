package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;
import Model.Fightable;

import java.util.ArrayList;

public class Vulnerable extends Buff {


    public Vulnerable(String name,int duration) {
        super(name,1);
        this.setRemainingTurn( duration );
        isDebuff = true;
    }

    /*
     * Target takes 50% more damage from attacks. 	Duration
     * */

    @Override
    public ArrayList<Effect> getTurnEffects(BuffDependencies dep) {
        Effect e = dep.getEffectStack().peek();
        Fightable owner = dep.getOwner();
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

    public ArrayList<Effect> runNextTurn(){
        remainingTurn--;
        return null;
    }
}
