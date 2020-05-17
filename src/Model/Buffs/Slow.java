package Model.Buffs;
import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.Damage;
import Model.Effects.Effect;

import java.util.ArrayList;


public class Slow extends Buff{
    public Slow(int x) {
        super("Slow",x);
        stackProperty=DURATION;
    }
    /*
    Whenever you play a card, the enemy receives 10% more damage from attacks this turn.
     */
    public ArrayList<Effect> getTurnEffects(BuffDependencies dep) {
        Effect e = dep.getEffectStack().peek();
        if(e instanceof Damage) {
            Damage d = (Damage)e;
           if(d.getSource()==dep.getOwner())  {
               // System.out.println("hereeee");
                Damage returnDamage = new Damage((int) (d.getDamage()*1.1), d.getTarget(), d.getSource());
                dep.getEffectStack().pop();
                dep.getEffectStack().push(returnDamage);
               /*ArrayList<Effect> toReturn = new ArrayList<Effect>();
                toReturn.add(returnDamage);
                return toReturn;*/
           }
        }
        return null;
    }
}
