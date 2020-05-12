package Model.Relics;

public class Relic {
	protected String name;
	protected String description;
	protected int price;
	protected String type;

	public Relic() {
		this.name = null;
		this.description = null;
		this.type = null;
		price = 0;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Relic [name=" + name + ", description=" + description + ", price=" + price + "]";
	}

	
}
