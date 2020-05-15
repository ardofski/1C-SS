package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Effects.Effect;

import java.util.ArrayList;

public class Relic {
	protected String name;
	protected String description;
	protected int price;
	protected String type;

	public Relic() {
		this.name = null;
		this.description = null;
		this.type = null;
		price = 0;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}


	//beginning of fight relics
	public ArrayList<Effect> getBeginingOfFightEffects(RelicDependencies dep){
		return null;
	}
	//entering room relics
	public void applyRoomEffects(RelicDependencies dep){ }

	//nnext turn relics
	public ArrayList<Effect> getNextTurnEffects(RelicDependencies dep){
		return null;
	}

	//turn effects
	public ArrayList<Effect> getTurnEffects(RelicDependencies dep){
		return null;
	}
	public ArrayList<Effect> getEndOfFightEffects(RelicDependencies dep){ return null;}
	public ArrayList<Effect> getStartOfTurnEffects(RelicDependencies dep){return null;}




	@Override
	public String toString() {
		return "Relic [name=" + name + ", description=" + description + ", price=" + price + "]";
	}

	
}
