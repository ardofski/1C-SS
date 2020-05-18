package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Buffs.Buff;
import Model.Buffs.Strength;
import Model.Effects.ApplyBuff;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Vajra.
 */
public class Vajra extends Relic {

    /**
     * Instantiates a new Vajra.
     */
    public Vajra(){
        name = "Vajra";
        description = "At the start of each combat, gain 1 Strength.";
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
        Buff str = new Strength(1);
        effects.add(new ApplyBuff(str, dep.getCharacter()));
        return effects;
    }
}
