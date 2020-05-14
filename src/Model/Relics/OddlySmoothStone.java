package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Buff;
import Model.Buffs.Dexterity;
import Model.Buffs.Strength;
import Model.Effects.ApplyBuff;
import Model.Effects.Effect;

import java.util.ArrayList;

public class OddlySmoothStone extends Relic {
    public OddlySmoothStone(){
        name = "OddlySmoothStone";
        description = "At the start of each combat, gain 1 Dexterity.";
        type = "Common";
        price = 0;
    }

    @Override
    public ArrayList<Effect> getBeginingOfFightEffects(RelicDependencies dep) {
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Buff dxt = new Dexterity(1);
        effects.add(new ApplyBuff(dxt, dep.getCharacter()));
        return effects;
    }
}
