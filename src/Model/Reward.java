package Model;

public class Reward {
	private int gold;
	private Relic relic;
	private Potion pot;
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public Relic getRelic() {
		return relic;
	}
	public void setRelic(Relic relic) {
		this.relic = relic;
	}
	public Potion getPot() {
		return pot;
	}
	public void setPot(Potion pot) {
		this.pot = pot;
	}
	@Override
	public String toString() {
		return "Reward [gold=" + gold + ", relic=" + relic + ", pot=" + pot + "]";
	}
	
}
