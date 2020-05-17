package Model.Buffs;

import java.util.ArrayList;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.ApplyBuff;
import Model.Effects.Effect;

public class Ritual extends Buff{

	public Ritual( int x) {
		super("Ritual",x);
		stackProperty = INTENSITY;
		description  = "At the end of turn, gains X Strength.";
	}

	/*
	At the end of its turn, gains X Strength.
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
