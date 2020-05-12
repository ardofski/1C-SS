package Model.Buffs;

import Controller.Fight.BuffDependencies;
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

    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
        ArrayList<Effect> effects = new ArrayList<Effect>();
        ChangeEnergy ce = new ChangeEnergy( x );
        effects.add( ce );
        x = 0;
        return effects;
    }

    public ArrayList<Effect> runNextTurn( ){
        remainingTurn--;
        ArrayList<Effect> effects = new ArrayList<Effect>();
        ChangeEnergy ce = new ChangeEnergy( x );
        effects.add( ce );
        return effects;
    }
}
