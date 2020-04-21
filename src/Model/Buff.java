package Model;

public class Buff {
	private String name;
	private String description;
	private int remainingTurn;
	public Buff(String name) {
		this.name = name;
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
	public int getRemainingTurn() {
		return remainingTurn;
	}
	public void setRemainingTurn(int remainingTurn) {
		this.remainingTurn = remainingTurn;
	}
	@Override
	public String toString() {
		return "Buff [name=" + name + ", description=" + description + ", remainingTurn=" + remainingTurn + "]";
	}
	
	
}
