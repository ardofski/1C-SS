package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Effects.Damage;
import Model.Effects.Effect;

import java.util.ArrayList;

public class MercuryHourglass extends Relic{
    public MercuryHourglass(){
        name = "MercuryHourGlass";
        price=0;
        description="At the start of your turn, deal 3 damage to ALL enemies.";
        type ="Uncommon";
    }

    @Override
    public ArrayList<Effect> getStartOfTurnEffects(RelicDependencies dep) {
        ArrayList<Effect> effects = new ArrayList<Effect>();
        for(int i=0; i<dep.getEnemies().size(); i++){
            effects.add(new Damage(3,dep.getEnemies().get(i),dep.getCharacter()));
        }
        return effects;
    }
}
