package Model.Buffs;

import java.util.ArrayList;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Enemy;
import Model.Effects.Damage;
import Model.Effects.Effect;

public class Strength extends Buff{
	int x;
	public Strength(int x) {
		super(x);//TODO
		name = "Strength";
		this.x=x;
		stackProperty = INTENSITY;
	}


    public ArrayList<Effect> run(Effect e, Enemy owner){
		if(e instanceof Damage) {
			Damage d = (Damage)e;
			if(d.getSource()==owner) {
				Damage returnDamage = new Damage(x, d.getSource(), d.getTarget());
				ArrayList<Effect> toReturn = new ArrayList<Effect>();
				toReturn.add(returnDamage);
				return toReturn;
			}
		}
		return null;
	}

	public ArrayList<Effect> runNextTurn(){
		remainingTurn--;
		return null;
	}
}
