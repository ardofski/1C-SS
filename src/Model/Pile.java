package Model;
import java.util.ArrayList;
import java.util.Collections;

public class Pile {
	private ArrayList<Card> cards;

	public Pile(ArrayList<Card> cardList){
		this.cards=cardList;
	}
	public Pile(){
		cards = new ArrayList<>();
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	@Override
	public String toString() {
		String cardsStr="";
		for(Card card: this.cards) {
			cardsStr +=card.toString()+" ";
		}
		return "Pile [cards=" +cardsStr + "]";
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
