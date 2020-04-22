package Model.Buffs;

import Model.Buff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;
import java.util.Stack;

public class Metallicize extends Buff {

    int x;
    public Metallicize(String name, int x) {
        super(name,1);
        this.x = x;
    }

    /*
         	At the end of your/its turn, gain X Block.
     */

    public ArrayList<Effect> runNextTurn(Enemy owner ){
        remainingTurn--;
        Block b = new Block( x , owner);
        ArrayList<Effect> effects = new ArrayList<Effect>();
        effects.add( b );
        return effects;
    }
}
