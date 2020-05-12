package Model;

public class Card {
    protected String name, rarity, type, color, description;
    protected int energy;
    protected boolean upgrade;
    protected boolean hasTarget;

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