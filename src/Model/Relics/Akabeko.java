package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Effects.Damage;
import Model.Effects.DrawCard;
import Model.Effects.Effect;

import java.util.ArrayList;

public class Akabeko extends Relic {
    boolean firstAttack;
    public Akabeko(){
        name = "Akabeko";
        description = "Your first attack each combat deals 8 additional damage.";
        type = "Common";
        price = 0;
        firstAttack = true;
    }
    @Override
    public ArrayList<Effect> getTurnEffects(RelicDependencies dep) {
        Effect e = dep.getEffectStack().peek();
        if(firstAttack && e instanceof Damage && ((Damage)e).getTarget()!=dep.getCharacter()){//yani damagıntargetı karakterdeğilse
            ((Damage) e).increaseDamage(8);
            firstAttack=false;
            dep.getEffectStack().pop();
            dep.getEffectStack().push(e);
        }
        return null;
    }
    @Override
    public ArrayList<Effect> getBeginingOfFightEffects(RelicDependencies dep) {
        System.out.println( "Begining effects of Akabeko is called.");
        firstAttack=true;
        return null;
    }
}
