package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Effects.Effect;
import Model.Effects.Heal;

import java.util.ArrayList;

public class BurningBlood extends Relic {
    //
    public BurningBlood(){
        name = "BurningBlood";
        description = "At the end of combat, heal 6 HP.";
        type = "Starter";
        price = 0;
    }

    @Override
    public ArrayList<Effect> getEndOfFightEffects(RelicDependencies dep) {
        ArrayList<Effect> effects = new ArrayList<Effect>();
        effects.add(new Heal(6));
        return effects;
    }
}
