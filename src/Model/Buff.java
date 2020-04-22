package Model;

public class Buff {
	private String name;
	private String description;
	private int remainingTurn;
	private boolean isDebuff;
	public Buff(String name, int remainingTurn, boolean isDebuff, String description) {
		this.name = name;
		this.remainingTurn=remainingTurn;
		this.isDebuff=isDebuff;
		this.description=description;
	}
	public boolean isDebuff(){
		return isDebuff;
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
	public void decreaseRemainingTurn(){
		remainingTurn--;
	}
	public void increaseRemainingTurn(){
		remainingTurn++;
	}
	@Override
	public String toString() {
		return "Buff [name=" + name + ", description=" + description + ", remainingTurn=" + remainingTurn + "]";
	}
	
	
}
