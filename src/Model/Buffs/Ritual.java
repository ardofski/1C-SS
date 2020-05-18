package Model.Buffs;

import java.util.ArrayList;

import Controller.Fight.BuffDependencies;
import Model.Effects.ApplyBuff;
import Model.Effects.Effect;

/**
 * The type Ritual.
 */
public class Ritual extends Buff{

    /**
     * Instantiates a new Ritual.
     *
     * @param x the x
     */
    public Ritual( int x) {
		super("Ritual",x);
		stackProperty = INTENSITY;
		description  = "At the end of turn, gains X Strength.";
	}

	/*
	At the end of its turn, gains X Strength.
	 */

    /**
     * Gets next turn effects.
     *
     * @param dep the dep
     * @return the next turn effects
     */
    @Override
	public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
		//System.out.println("hereeeeeeeeeeeeeeeeeeee"+dep.getOwner().getName()+ "xaxsas"+x);
		ApplyBuff strengthBuff = new ApplyBuff(new Strength(x) ,dep.getOwner() );
		ArrayList<Effect> effects = new ArrayList<Effect>();
		effects.add(strengthBuff);
		//setX(0);

		return effects;
	}

	
	
}
