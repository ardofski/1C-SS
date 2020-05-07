package Model;

import Controller.Fight.CardDependencies;
import DBConnection.CardFactory;
import Model.Effects.Effect;

import java.util.ArrayList;

public class Card {
    protected String name, rarity, type, color, description;
    protected int energy;
    protected boolean upgrade;
    protected boolean hasTarget;

    //Constructors

    public Card(String name, String rarity, String type, String color, String description, int energy,boolean upgrade,boolean hasTarget) {
        this.name = name;
        this.rarity = rarity;
        this.type = type;
        this.color = color;
        this.description = description;
        this.energy = energy;
        this.upgrade = upgrade;
        this.hasTarget = hasTarget;
    }
    public Card(String name, String rarity, String type, String color, String description, int energy,boolean upgrade) {
        this.name = name;
        this.rarity = rarity;
        this.type = type;
        this.color = color;
        this.description = description;
        this.energy = energy;
        this.upgrade = upgrade;
        this.hasTarget = false;
    }

    public ArrayList<Effect> play(CardDependencies dependencies){
        return null;
    };

    //
    public Card getClone(){
        return CardFactory.getCard(name);
    }

    public boolean isHasTarget(){
        return hasTarget;
    }

    public void upgrade(){
	this.upgrade=true;
    }
    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
	@Override
	public String toString() {
		return "Card [name=" + name + ", description=" + description
                + ", energy=" + energy + ", type=" + type + "]";
	}
}