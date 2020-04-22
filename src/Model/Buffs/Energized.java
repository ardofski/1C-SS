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
        super(name);
        this.x = x;
    }

    /*
     	Gain X additional Energy next turn.
    */
    public ArrayList<Effect> runNextTurn( ){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        ChangeEnergy ce = new ChangeEnergy( x );
        effects.add( ce );
        return effects;
    }
}
