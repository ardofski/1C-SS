package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Effects.Damage;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Bronze scales.
 */
public class BronzeScales extends Relic {

    /**
     * Instantiates a new Bronze scales.
     */
    public BronzeScales(){
        name = "BronzeScales";
        description = "Whenever you take damage, deal 3 damage back.";
        type = "Common";
        price = 0;
    }

    /**
     * Gets turn effects.
     *
     * @param dep the dep
     * @return the turn effects
     */
    @Override
    public ArrayList<Effect> getTurnEffects(RelicDependencies dep) {
        Effect e = dep.getEffectStack().peek();
        if(e instanceof Damage && ((Damage)e).getTarget() == dep.getCharacter() ){
            ArrayList<Effect> effects = new ArrayList<Effect>();
            effects.add(new Damage(3,((Damage) e).getSource(),dep.getCharacter()));
            return effects;
        }
        return null;
    }
}
