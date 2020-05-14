package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Buff;
import Model.Buffs.PlatedArmor;
import Model.Effects.ApplyBuff;
import Model.Effects.Effect;

import java.util.ArrayList;

public class ThreadAndNeedle extends Relic{
    public ThreadAndNeedle(){
       name ="ThreadAndNeedle";
       price=0;
       description="At the start of each combat, gain 4 Plated Armor.";
       type ="Rare";
    }

    @Override
    public ArrayList<Effect> getBeginingOfFightEffects(RelicDependencies dep) {
        Buff plt = new PlatedArmor(4);
        ArrayList<Effect> effects = new ArrayList<Effect>();
        effects.add(new ApplyBuff(plt, dep.getCharacter()));
        return effects;
    }
}
