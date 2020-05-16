package Model;

import Model.Effects.Effect;

import java.util.ArrayList;
import java.util.Queue;

public class Pet {
	private String name;
	private String description;
	private Queue<ArrayList<Effect>> effects;
	public Queue<ArrayList<Effect>> getEffects() {
		return effects;
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
	public Pet(String name) {
		this.name = name;
	}

	public void setEffects(Queue<ArrayList<Effect>> effects) {
		this.effects = effects;
	}

	@Override
	public String toString() {
		return "Pet{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", effects=" + effects +
				'}';
	}
}
