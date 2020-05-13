package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;
import java.util.Stack;

public class Metallicize extends Buff {

    int x;
    public Metallicize( int x) {
        super("Metallicize",1);
        this.x = x;
        stackProperty = INTENSITY;
    }

    /*
         	At the end of your/its turn, gain X Block.
     */

    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
        Block b = new Block( x , dep.getOwner() );
        ArrayList<Effect> effects = new ArrayList<Effect>();
        effects.add( b );
        x = 0;
        return effects;
    }

}
