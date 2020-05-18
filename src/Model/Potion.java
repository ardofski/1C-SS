package Model;

import Model.Effects.Effect;

/**
 * The type Potion.
 */
public class Potion {
    /**
     * The Effect.
     */
    Effect effect;

    /**
     * Gets effect.
     *
     * @return the effect
     */
    public Effect getEffect() {
		return effect;
	}

    /**
     * Sets effect.
     *
     * @param effect the effect
     */
    public void setEffect(Effect effect) {
		this.effect = effect;
	}

    /**
     * The Name.
     */
    private String name;
    /**
     * The Description.
     */
    private String description;
    /**
     * The Price.
     */
    private int price;
    /**
     * The Has target.
     */
    private boolean hasTarget;

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
     * Is has target boolean.
     *
     * @return the boolean
     */
    public boolean isHasTarget(){return hasTarget;}

    /**
     * Set has target.
     *
     * @param b the b
     */
    public void setHasTarget(boolean b){hasTarget=b;}

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
	public String toString() {
		return "Potion [name=" + name + ", description=" + description + ", price=" + price + "]";
	}

    /**
     * Instantiates a new Potion.
     *
     * @param name        the name
     * @param description the description
     * @param price       the price
     * @param effect      the effect
     */
    public Potion(String name,String description, int price, Effect effect) {
		this.name = name;
		this.description=description;
		this.effect=effect;
		this.price=price;
		hasTarget = false;
	}
	
}
