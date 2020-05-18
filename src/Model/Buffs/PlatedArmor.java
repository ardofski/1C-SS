package Model.Buffs;

import java.util.ArrayList;

import Controller.Fight.BuffDependencies;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;

/**
 * The type Plated armor.
 */
public class PlatedArmor extends Buff {
    /**
     * Instantiates a new Plated armor.
     *
     * @param x the x
     */
    public PlatedArmor( int x) {
		super("PlatedArmor",x);
		stackProperty = INTENSITY;
		description =  "At the end of your turn, gain X Block. Receiving attack damage reduces Plated Armor by 1.";
	}

	/*
		At the end of your turn, gain X Block.
		Receiving attack damage reduces Plated Armor by 1.
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
		if(e instanceof Damage){
			if(((Damage) e).getTarget() == dep.getOwner() ){
				decreaseRemainingTurn();
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
		//System.out.println("hereeeeee"+x+" "+ dep.getOwner().getName());
		Block b = new Block(x, dep.getOwner() );
		ArrayList<Effect> toReturn = new ArrayList<Effect>();
		toReturn.add(b);
		//setX(0);
		return toReturn;
	}

}
