package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Relic.
 */
public class Relic {
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Description.
     */
    protected String description;
    /**
     * The Price.
     */
    protected int price;
    /**
     * The Type.
     */
    protected String type;

    /**
     * Instantiates a new Relic.
     */
    public Relic() {
		this.name = null;
		this.description = null;
		this.type = null;
		price = 0;
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
     * Gets price.
     *
     * @return the price
     */
    public int getPrice() {
		return price;
	}

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(int price) {
		this.price = price;
	}


    /**
     * Get begining of fight effects array list.
     *
     * @param dep the dep
     * @return the array list
     */
//beginning of fight relics
	public ArrayList<Effect> getBeginingOfFightEffects(RelicDependencies dep){
		return null;
	}

    /**
     * Apply room effects.
     *
     * @param dep the dep
     */
//entering room relics
	public void applyRoomEffects(RelicDependencies dep){ }

    /**
     * Get next turn effects array list.
     *
     * @param dep the dep
     * @return the array list
     */
//nnext turn relics
	public ArrayList<Effect> getNextTurnEffects(RelicDependencies dep){
		return null;
	}

    /**
     * Get turn effects array list.
     *
     * @param dep the dep
     * @return the array list
     */
//turn effects
	public ArrayList<Effect> getTurnEffects(RelicDependencies dep){
		return null;
	}

    /**
     * Get end of fight effects array list.
     *
     * @param dep the dep
     * @return the array list
     */
    public ArrayList<Effect> getEndOfFightEffects(RelicDependencies dep){ return null;}

    /**
     * Get start of turn effects array list.
     *
     * @param dep the dep
     * @return the array list
     */
    public ArrayList<Effect> getStartOfTurnEffects(RelicDependencies dep){return null;}


    /**
     * To string string.
     *
     * @return the string
     */
    @Override
	public String toString() {
		return "Relic [name=" + name + ", description=" + description + ", price=" + price + "]";
	}

	
}
