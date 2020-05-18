package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Buffs.Buff;
import Model.Buffs.Strength;
import Model.Effects.ApplyBuff;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Red skull.
 */
public class RedSkull extends Relic {
    /**
     * The Applied.
     */
    boolean applied;

    /**
     * Instantiates a new Red skull.
     */
    public RedSkull(){
        name = "RedSkull";
        description = "While your HP is at or below 50%, you have 3 additional Strength.";
        type = "Common";
        price = 0;
        applied=false;
    }

    /**
     * Gets turn effects.
     *
     * @param dep the dep
     * @return the turn effects
     */
    @Override
    public ArrayList<Effect> getTurnEffects(RelicDependencies dep) {
        double per = (double)dep.getCharacter().getHp()/dep.getCharacter().getMaxHp();

        if(per<0.5&&!applied){

            ArrayList<Effect> effects = new ArrayList<Effect>();
            Buff str = new Strength(3);
            effects.add(new ApplyBuff(str, dep.getCharacter()));
            applied = true;
            return effects;

        }else if(applied && per>=0.5){

            ArrayList<Effect> effects = new ArrayList<Effect>();
            Buff str = new Strength(-3);
            effects.add(new ApplyBuff(str, dep.getCharacter()));
            applied = false;
            return effects;
        }

        return null;
    }

    /**
     * Gets begining of fight effects.
     *
     * @param dep the dep
     * @return the begining of fight effects
     */
    @Override
    public ArrayList<Effect> getBeginingOfFightEffects(RelicDependencies dep) {
        applied = false;
        return null;
    }
}
