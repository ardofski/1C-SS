package Model;

public class Card {
	private String name;
	private String description;
	private int energy;
	private boolean exhaust;
	private boolean eternal;
	private String type;
	private int price;
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
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public boolean isExhaust() {
		return exhaust;
	}
	public void setExhaust(boolean exhaust) {
		this.exhaust = exhaust;
	}
	public boolean isEternal() {
		return eternal;
	}
	public void setEternal(boolean eternal) {
		this.eternal = eternal;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Card [name=" + name + ", description=" + description + ", energy=" + energy + ", exhaust=" + exhaust
				+ ", eternal=" + eternal + ", type=" + type + ", price=" + price + "]";
	}
	public Card(String name) {
		this.name = name;
	}
	
}
