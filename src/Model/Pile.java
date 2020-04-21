package Model;
import java.util.ArrayList;
import java.util.Collections;

public class Pile {
	private ArrayList<Card> cards;

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {
		return "Pile [cards=" + cards.toString() + "]";
	}
	public void shuffle() {
		Collections.shuffle(cards);
	}
	public boolean addCard(Card toAdd) {
		return cards.add(toAdd);
	}
	public boolean removeCard(Card toDelete) {
		return cards.remove(toDelete);
	}
	
}
