package Model;

import Model.Effects.Effect;

import java.util.ArrayList;
import java.util.Queue;

/**
 * The type Pet.
 */
public class Pet {
    /**
     * The Name.
     */
    private String name;
    /**
     * The Description.
     */
    private String description;
    /**
     * The Effects.
     */
    private Queue<ArrayList<Effect>> effects;
    /**
     * The Targets.
     */
    private Queue<ArrayList<Integer>> targets;//0 is for enemy,1 is for

    /**
     * Gets targets.
     *
     * @return the targets
     */
    public Queue<ArrayList<Integer>> getTargets() {
		return targets;
	}

    /**
     * Sets targets.
     *
     * @param targets the targets
     */
    public void setTargets(Queue<ArrayList<Integer>> targets) {
		this.targets = targets;
	}

    /**
     * Gets effects.
     *
     * @return the effects
     */
    public Queue<ArrayList<Effect>> getEffects() {
		return effects;
	}


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
		return name;
	}

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
		this.name = name;
	}

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
		return description;
	}

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
		this.description = description;
	}

    /**
     * Instantiates a new Pet.
     *
     * @param name the name
     */
    public Pet(String name) {
		this.name = name;
	}

    /**
     * Sets effects.
     *
     * @param effects the effects
     */
    public void setEffects(Queue<ArrayList<Effect>> effects) {
		this.effects = effects;
	}

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
	public String toString() {
		return "Pet{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", effects=" + effects +
				'}';
	}
}
