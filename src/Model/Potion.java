package Model;

import Model.Effects.Effect;

public class Potion {
	Effect effect;
	public Effect getEffect() {
		return effect;
	}
	public void setEffect(Effect effect) {
		this.effect = effect;
	}
	private String name;
	private String description;
	private int price;
	private boolean hasTarget;
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

	public boolean isHasTarget(){return hasTarget;}
	public void setHasTarget(boolean b){hasTarget=b;}
	@Override
	public String toString() {
		return "Potion [name=" + name + ", description=" + description + ", price=" + price + "]";
	}
	public Potion(String name,String description, int price, Effect effect) {
		this.name = name;
		this.description=description;
		this.effect=effect;
		this.price=price;
		hasTarget = false;
	}
	
}
