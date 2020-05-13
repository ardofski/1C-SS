package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.ApplyBuff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;
import Model.Fightable;

import java.util.ArrayList;
import java.util.Stack;

public class Thorns extends Buff {

    int x;
    public Thorns(int x) {
        super("Thorns",x);
        this.x = x;
        stackProperty = INTENSITY;
        description = "When attacked, deals X damage back.";
    }

    /*
        When attacked, deals X damage back.
     */

    @Override
    public ArrayList<Effect> getTurnEffects(BuffDependencies dep) {
        Effect e = dep.getEffectStack().peek();
        Fightable owner = dep.getOwner();
        if( e instanceof Damage){
            Damage d = (Damage)e;
            if( d.getTarget() == owner ){
                Damage returnDamage = new Damage( d.getDamage(), d.getSource() , d.getTarget() );
                ArrayList<Effect> returnList = new ArrayList<Effect>();
                returnList.add(returnDamage);
                return returnList;
            }
        }
        return null;
    }

}
