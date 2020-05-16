package Model;

import Model.Relics.Relic;

import java.util.ArrayList;

public class Reward {
	private int gold;
	private Relic relic;
	private Potion pot;
	private ArrayList<Card> cards;

	public Reward()
	{
		cards = new ArrayList<Card>();
	}
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

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
