package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.Damage;
import Model.Effects.Effect;

import java.util.ArrayList;

public class Poison extends Buff {

    public Poison(int x){
        super(x);
        description  = "At the beginning of its turn, the target loses X HP and 1 stack of Poison.";
    }
    /*
    At the beginning of its turn, the target loses X HP and 1 stack of Poison.
     */

    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
        Damage damage = new Damage( x,dep.getOwner(),dep.getOwner() );
        x--;
        ArrayList<Effect> effects = new ArrayList<>();
        effects.add(damage);
        return effects;
    }
}
