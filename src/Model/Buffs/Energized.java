package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.ChangeEnergy;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class Energized extends Buff {

    public Energized(int x) {
        super("Energized",x);
        stackProperty = INTENSITY;
        description = "Gain X additional Energy next turn.";
    }

    /*
     	Gain X additional Energy next turn.
    */

    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
        System.out.println("hereeeeeeeeeeee"+ x);
        ArrayList<Effect> effects = new ArrayList<Effect>();
        ChangeEnergy ce = new ChangeEnergy( x );
        effects.add( ce );
        //setX(0);
        return effects;
    }

}
