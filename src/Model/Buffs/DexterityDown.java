package Model.Buffs;
import Controller.Fight.BuffDependencies;
import Model.Effects.ApplyBuff;
import Model.Effects.Effect;
import Model.Fightable;

import java.util.ArrayList;

/**
 * The type Dexterity down.
 */
public class DexterityDown extends Buff{
    /**
     * Instantiates a new Dexterity down.
     *
     * @param x the x
     */
    public DexterityDown(int x) {
        super("DexterityDown",x);
    }

    /**
     * Gets next turn effects.
     *
     * @param dep the dep
     * @return the next turn effects
     */
/*
    At the end of your turn, lose X Dexterity.
     */
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
       Fightable owner = dep.getOwner();
       ApplyBuff buff = new ApplyBuff(new Dexterity(-x),owner);

       ArrayList ddEffect = new ArrayList<Effect>();
        ddEffect.add(buff);
       return ddEffect;
    }
}
