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
    public Barricade(String name) {

        super(name,1);
    }


    /*
        Block is not removed at the start of your/its turn.
    */

    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Fightable owner = dep.getOwner();
        x--;

        if( owner.getBlock() > 0 ) {
            Block b = new Block(owner.getBlock(), null);
            effects.add(b);
        }
        return effects;
    }

    public ArrayList<Effect> runNextTurn(Enemy owner, int block ){
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
