package Model;
import java.util.ArrayList;
import java.util.Collections;

public class Pile {
	private ArrayList<Card> cards;

	public Pile(ArrayList<Card> cardList){
		this.cards=cardList;
	}

	public Pile() {
		cards = new ArrayList<Card>();
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
	public boolean delete(String name){
		for (int i = 0; i < cards.size(); i++){
			if(cards.get(i).getName().equals( name)){
				cards.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public Card takeCard(int cardNum) {
		return cards.get(cardNum);
	}
	public Card takeTop() {
		Card toReturn = cards.get(cards.size()-1);
		cards.remove(cards.size()-1);
		return toReturn;
	}

	public Card getTop(){
		return cards.get(cards.size()-1);
	}
}
