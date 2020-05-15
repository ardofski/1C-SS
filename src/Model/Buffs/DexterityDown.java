package Model.Buffs;
import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.ApplyBuff;
import Model.Effects.Block;
import Model.Effects.Effect;
import Model.Fightable;

import java.util.ArrayList;
import java.util.Stack;

public class DexterityDown extends Buff{
    public DexterityDown(int x) {
        super(x);
    }
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
