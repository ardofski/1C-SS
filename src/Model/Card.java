package Model;

public class Card {
    String name, rarity, type, color, description;
    int energy;

    public Card(String name, String rarity, String type, String color, String description, int energy) {
        this.name = name;
        this.rarity = rarity;
        this.type = type;
        this.color = color;
        this.description = description;
        this.energy = energy;
    }

    @Override
    public String toString() {
        return "name = " + name + "\n";
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
}
