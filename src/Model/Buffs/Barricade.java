package Model.Buffs;

import Model.Buff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class Barricade extends Buff {
    public Barricade(String name) {

        super(name,1);
    }


    /*
        Block is not removed at the start of your/its turn.
    */

    public ArrayList<Effect> runNextTurn(Enemy owner,int block ){
        this.remainingTurn--;
        ArrayList<Effect> effects = new ArrayList<Effect>();
        if( block > 0 && owner == null ){
            Block b = new Block( block , null);
            effects.add( b );
        }
        else{
            // read given enemy(owner) block and assign it to enemyBLock
            int enemyBlock = owner.getBlock();
            Block b = new Block( enemyBlock, owner );
            effects.add(b);
        }
        return effects;
    }
}
