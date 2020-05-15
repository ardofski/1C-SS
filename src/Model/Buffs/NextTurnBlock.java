package Model.Buffs;

import java.util.ArrayList;

import Controller.Fight.BuffDependencies;
import Model.Buff;
import Model.Enemy;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
public class NextTurnBlock extends Buff{
	int x;
	public NextTurnBlock(int x ){
		super("NextTurnBlock",1);
		this.x=x;
		// TODO Auto-generated constructor stub
	}



	public ArrayList<Effect> runNextTurn(Enemy owner){
	      Block b = new Block(x, owner);
	      ArrayList<Effect> toReturn = new ArrayList<Effect>();
		  toReturn.add(b);
		  return toReturn;
	}
}
