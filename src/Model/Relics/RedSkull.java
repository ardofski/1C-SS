package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Buff;
import Model.Buffs.Strength;
import Model.Effects.ApplyBuff;
import Model.Effects.Effect;

import java.util.ArrayList;

public class RedSkull extends Relic {
    boolean applied;
    public RedSkull(){
        name = "RedSkull";
        description = "While your HP is at or below 50%, you have 3 additional Strength.";
        type = "Common";
        price = 0;
        applied=false;
    }

    @Override
    public ArrayList<Effect> getTurnEffects(RelicDependencies dep) {
        double per = (double)dep.getCharacter().getHp()/dep.getCharacter().getMaxHp();
       // System.out.println("heeeeeeeeeeeeeeeeeee"+ per+" "+applied);
        if((per<0.5)&& !applied){
           // System.out.println("burdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            ArrayList<Effect> effects = new ArrayList<Effect>();
            Buff str = new Strength(3);
            effects.add(new ApplyBuff(str, dep.getCharacter()));
            applied = true;
            return effects;
        }else if(applied && (per>=0.5)){
            ArrayList<Effect> effects = new ArrayList<Effect>();
            Buff str = new Strength(-3);
            effects.add(new ApplyBuff(str, dep.getCharacter()));
            applied = false;
            return effects;
        }

        return null;
    }

    @Override
    public ArrayList<Effect> getBeginingOfFightEffects(RelicDependencies dep) {
        applied = false;
        return null;
    }
}
