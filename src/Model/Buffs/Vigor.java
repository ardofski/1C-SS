package Model.Buffs;

import java.util.ArrayList;

import Model.Buff;
import Model.Enemy;
import Model.Effects.Damage;
import Model.Effects.Effect;

public class Vigor extends Buff{
	int x;
	public Vigor(String name, int x) {
		super(name);
		this.x =x;
		// TODO Auto-generated constructor stub
	}
	/*
	 * Your next Attack deals X additional damage.
	 * */
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


}
