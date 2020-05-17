package Model.Buffs;

import java.util.ArrayList;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Enemy;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Fightable;

public class Vigor extends Buff{
	int x;
	public Vigor( int x) {
		super("Vigor",1);
		this.x =x;
		stackProperty = INTENSITY;
		description = "Your next Attack deals X additional damage.";
		// TODO Auto-generated constructor stub
	}
	/*
	 * Your next Attack deals X additional damage.
	 * Intensity
	 * */

	@Override
	public ArrayList<Effect> getTurnEffects(BuffDependencies dep) {
		Effect e = dep.getEffectStack().peek();
		Fightable owner = dep.getOwner();
		if(e instanceof Damage) {
			Damage d = (Damage)e;
			if(d.getSource()==owner) {
				Damage returnDamage = new Damage(x, d.getTarget(), d.getSource());
				ArrayList<Effect> toReturn = new ArrayList<Effect>();
				toReturn.add(returnDamage);
				this.setRemainingTurn(0);
				return toReturn;
			}
		}
		return null;
	}

	public ArrayList<Effect> run(Effect e, Enemy owner){
		if(e instanceof Damage) {
			Damage d = (Damage)e;
			if(d.getSource()==owner) {
				Damage returnDamage = new Damage(x, d.getSource(), d.getTarget());
				ArrayList<Effect> toReturn = new ArrayList<Effect>();
				toReturn.add(returnDamage);
				this.setRemainingTurn(0);
				return toReturn;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
		return super.getNextTurnEffects(dep);
	}
}
