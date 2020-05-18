package Model.Buffs;

import java.util.ArrayList;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Enemy;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Fightable;

/**
 * The type Vigor.
 */
public class Vigor extends Buff{
    /**
     * The X.
     */
    int x;

    /**
     * Instantiates a new Vigor.
     *
     * @param x the x
     */
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

    /**
     * Gets turn effects.
     *
     * @param dep the dep
     * @return the turn effects
     */
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

    /**
     * Run array list.
     *
     * @param e     the e
     * @param owner the owner
     * @return the array list
     */
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

    /**
     * Gets next turn effects.
     *
     * @param dep the dep
     * @return the next turn effects
     */
    @Override
	public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
		return super.getNextTurnEffects(dep);
	}
}
