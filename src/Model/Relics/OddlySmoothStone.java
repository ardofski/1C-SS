package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Buff;
import Model.Buffs.Dexterity;
import Model.Buffs.Strength;
import Model.Effects.ApplyBuff;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Oddly smooth stone.
 */
public class OddlySmoothStone extends Relic {
    /**
     * Instantiates a new Oddly smooth stone.
     */
    public OddlySmoothStone(){
        name = "OddlySmoothStone";
        description = "At the start of each combat, gain 1 Dexterity.";
        type = "Common";
        price = 0;
    }

    /**
     * Gets begining of fight effects.
     *
     * @param dep the dep
     * @return the begining of fight effects
     */
    @Override
    public ArrayList<Effect> getBeginingOfFightEffects(RelicDependencies dep) {
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Buff dxt = new Dexterity(1);
        effects.add(new ApplyBuff(dxt, dep.getCharacter()));
        return effects;
    }
}
