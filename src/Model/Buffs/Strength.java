package Model.Buffs;

import java.util.ArrayList;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Enemy;
import Model.Effects.Damage;
import Model.Effects.Effect;

/**
 * The type Strength.
 */
public class Strength extends Buff{
    /**
     * Instantiates a new Strength.
     *
     * @param x the x
     */
    public Strength(int x) {
		super("Strength",x);
		stackProperty = INTENSITY;
		description = "Increases attack damage by X.";
	}

	/*
		Increases attack damage by X.
 	*/

    /**
     * Gets turn effects.
     *
     * @param dep the dep
     * @return the turn effects
     */
    @Override
	public ArrayList<Effect> getTurnEffects(BuffDependencies dep) {
		Effect e = dep.getEffectStack().peek();
		if(e instanceof Damage) {
			Damage d = (Damage)e;
			System.out.println( "IN STRENGTH METHOD, owner is " + dep.getOwner().getName() + " source is " + d.getSource() );
			if(d.getSource() == dep.getOwner())  {

				Damage returnDamage = new Damage(x, d.getTarget(), d.getSource());
				ArrayList<Effect> toReturn = new ArrayList<Effect>();
				toReturn.add(returnDamage);
				return toReturn;
			}
		}
		return null;
	}


}
