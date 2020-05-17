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

    public Metallicize( int x) {
        super("Metallicize",x);

        stackProperty = INTENSITY;
        description = "At the end of turn, gain X Block.";
    }

    /*
         	At the end of your/its turn, gain X Block.
     */

    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
       // System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxx"+x);
        Block b = new Block( x , dep.getOwner() );
        ArrayList<Effect> effects = new ArrayList<Effect>();
        effects.add( b );
        return effects;
    }

}
