package Model.Buffs;

import java.util.ArrayList;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Cards.Defend;
import Model.Enemy;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;

public class PlatedArmor extends Buff {
	public PlatedArmor( int x) {
		super("PlatedArmor",x);
		stackProperty = INTENSITY;
		description =  "At the end of your turn, gain X Block. Receiving attack damage reduces Plated Armor by 1.";
	}

	/*
		At the end of your turn, gain X Block.
		Receiving attack damage reduces Plated Armor by 1.
	 */

	@Override
	public ArrayList<Effect> getTurnEffects(BuffDependencies dep) {
		Effect e = dep.getEffectStack().peek();
		if(e instanceof Damage){
			if(((Damage) e).getTarget() == dep.getOwner() ){
				decreaseRemainingTurn();
			}
		}
		return null;
	}

	@Override
	public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
		Block b = new Block(x, dep.getOwner() );
		ArrayList<Effect> toReturn = new ArrayList<Effect>();
		toReturn.add(b);
		setX(0);
		return toReturn;
	}

}
