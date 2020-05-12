package Model.Buffs;

import java.util.ArrayList;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Effects.ApplyBuff;
import Model.Effects.Effect;

public class Ritual extends Buff{
	int x;
	public Ritual(String name, int x) {
		super(name,1);//TODO
		this.x=x;
		// TODO Auto-generated constructor stub
	}

	/*
	At the end of its turn, gains X Strength.
	 */

	@Override
	public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
		ApplyBuff strengthBuff = new ApplyBuff(new Strength(x) ,dep.getOwner() );
		ArrayList<Effect> effects = new ArrayList<Effect>();
		effects.add(strengthBuff);
		return effects;
	}
	//strength eklicek karaktere. strength de buff. effects'e de mi eklemek laz�m strengthi?
	
	
}
