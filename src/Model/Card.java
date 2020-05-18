package Model;

import Controller.Fight.CardDependencies;
import Model.Cards.CardFactory;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Card.
 */
public class Card {
    /**
     * The Name.
     */
    protected String name, /**
     * The Rarity.
     */
    rarity, /**
     * The Type.
     */
    type, /**
     * The Color.
     */
    color, /**
     * The Description.
     */
    description;
    /**
     * The Energy.
     */
    protected int energy;
    /**
     * The Upgrade.
     */
    protected boolean upgrade;
    /**
     * The Has target.
     */
    protected boolean hasTarget;

    /**
     * Instantiates a new Card.
     *
     * @param upgrade   the upgrade
     * @param hasTarget the has target
     */
    public Card(boolean upgrade,boolean hasTarget) {
        this.name = null;
        this.rarity = null;
        this.type = null;
        this.color = null;
        this.description = null;
        this.energy = 0;
        this.upgrade = upgrade;
        this.hasTarget = hasTarget;
    }

    /**
     * Instantiates a new Card.
     *
     * @param upgrade the upgrade
     */
    public Card(boolean upgrade) {
        this.name = null;
        this.rarity = null;
        this.type = null;
        this.color = null;
        this.description = null;
        this.energy = 0;
        this.upgrade = upgrade;
        this.hasTarget = false;
    }

    /**
     * Is playable boolean.
     *
     * @param dep the dep
     * @return the boolean
     */
    public boolean isPlayable(CardDependencies dep){
        if( energy <= dep.getCharacter().getEnergy() ) return true;
        return false;
    }

    /**
     * Play array list.
     *
     * @param dependencies the dependencies
     * @return the array list
     */
    public ArrayList<Effect> play(CardDependencies dependencies){
        return null;
    };

    /**
     * Get clone card.
     *
     * @return the card
     */
//
    public Card getClone(){
        return CardFactory.getCard(name, upgrade);
    }

    /**
     * Is has target boolean.
     *
     * @return the boolean
     */
    public boolean isHasTarget(){
        return hasTarget;
    }

    /**
     * Upgrade.
     */
    public void upgrade(){
	this.upgrade=true;
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
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
        return color;
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
     * Gets rarity.
     *
     * @return the rarity
     */
    public String getRarity() {
        return rarity;
    }

    /**
     * Sets rarity.
     *
     * @param rarity the rarity
     */
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(String color) {
        this.color = color;
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
     * Gets energy.
     *
     * @return the energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Sets energy.
     *
     * @param energy the energy
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * Get upgrade boolean.
     *
     * @return the boolean
     */
    public boolean getUpgrade(){ return upgrade;}


    /**
     * To string string.
     *
     * @return the string
     */
    @Override
	public String toString() {
		return "Card [name=" + name + ", description=" + description
                + ", energy=" + energy + ", type=" + type + "]";
	}
}