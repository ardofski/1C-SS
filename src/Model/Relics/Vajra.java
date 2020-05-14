package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Buff;
import Model.Buffs.Strength;
import Model.Effects.ApplyBuff;
import Model.Effects.Effect;

import java.util.ArrayList;

public class Vajra extends Relic {

    public Vajra(){
        name = "Vajra";
        description = "At the start of each combat, gain 1 Strength.";
        type = "Common";
        price = 0;
    }

    @Override
    public ArrayList<Effect> getBeginingOfFightEffects(RelicDependencies dep) {
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Buff str = new Strength(1);
        effects.add(new ApplyBuff(str, dep.getCharacter()));
        return effects;
    }
}
