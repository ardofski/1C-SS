package Model.Buffs;

import Model.Buff;
import Model.Effects.ApplyBuff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;
import java.util.Stack;

public class Thorns extends Buff {

    int x;
    public Thorns(String name,int x) {
        super(name);
        this.x = x;
    }

    /*
        When attacked, deals X damage back.
     */
    public ArrayList<Effect> run( Effect e, Enemy owner ){
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
