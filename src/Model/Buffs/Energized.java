package Model.Buffs;

import Model.Buff;
import Model.Effects.ChangeEnergy;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class Energized extends Buff {

    int x;
    public Energized(String name, int x) {
        super(name,1);
        this.x = x;
    }

    /*
     	Gain X additional Energy next turn.
    */
    public ArrayList<Effect> runNextTurn( ){
        remainingTurn--;
        ArrayList<Effect> effects = new ArrayList<Effect>();
        ChangeEnergy ce = new ChangeEnergy( x );
        effects.add( ce );
        return effects;
    }
}
