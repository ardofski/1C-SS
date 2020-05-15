package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.Block;
import Model.Character;
import Model.Effects.Effect;
import Model.Enemy;
import Model.Fightable;

import java.util.ArrayList;

public class Barricade extends Buff {
    public Barricade() {
        super("Barricade",1);
        stackProperty = NO;
        description = "Block is not removed at the start of turn.";

    }
    /*
        Block is not removed at the start of your/its turn.
    */

    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
        super.getNextTurnEffects(dep);
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Fightable owner = dep.getOwner();
        x--;

        if( owner.getBlock() > 0 ) {
            Block b = new Block(owner.getBlock(), null);
            effects.add(b);
        }
        return effects;
    }

}
