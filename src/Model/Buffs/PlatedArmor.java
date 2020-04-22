package Model.Buffs;

import java.util.ArrayList;

import Model.Buff;
import Model.Enemy;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;

public class PlatedArmor extends Buff {
	int x;
	public PlatedArmor(String name) {
		super(name,1);
		this.x=x;
	}
	
	public void run(Effect e){
		if(e instanceof Damage) {
			x--;
		}
	}
	/*
	 * At the end of your turn, 
	 * gain X Icon Block Block. Receiving unblocked attack damage reduces Plated Armor by 1.
	 */
	 public ArrayList<Effect> runNextTurn(Enemy target){
	 	remainingTurn--;
	      Block b = new Block(x, target);
	      ArrayList<Effect> toReturn = new ArrayList<Effect>();
		  toReturn.add(b);
		  return toReturn;
	}

}
