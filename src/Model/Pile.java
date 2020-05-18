package Model;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The type Pile.
 */
public class Pile {
    /**
     * The Cards.
     */
    private ArrayList<Card> cards;

    /**
     * Instantiates a new Pile.
     *
     * @param cardList the card list
     */
    public Pile(ArrayList<Card> cardList) {
		this.cards = cardList;
	}

    /**
     * Instantiates a new Pile.
     */
    public Pile() {
		cards = new ArrayList<Card>();
	}

    /**
     * Gets cards.
     *
     * @return the cards
     */
    public ArrayList<Card> getCards() {
		return cards;
	}

    /**
     * Sets cards.
     *
     * @param cards the cards
     */
    public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
	public String toString() {
		String cardsStr = "";
		for (Card card : this.cards) {
			cardsStr += card.toString() + " ";
		}
		return "Pile [cards=" + cardsStr + "]";
	}


    /**
     * Shuffle.
     */
    public void shuffle() {
		Collections.shuffle(cards);
	}

    /**
     * Add card boolean.
     *
     * @param toAdd the to add
     * @return the boolean
     */
    public boolean addCard(Card toAdd) {

		if( toAdd == null ) return false;

		cards.add(toAdd);
		return true;
	}

    /**
     * Remove card boolean.
     *
     * @param toDelete the to delete
     * @return the boolean
     */
    public boolean removeCard(Card toDelete) {
		return cards.remove(toDelete);
	}

    /**
     * Take card card.
     *
     * @param cardNum the card num
     * @return the card
     */
    public Card takeCard(int cardNum) {
		return cards.get(cardNum);
	}

    /**
     * Take top card.
     *
     * @return the card
     */
    public Card takeTop() {
		if( cards.size() == 0 )return null;
		Card toReturn = cards.get(cards.size()-1);
		cards.remove(cards.size()-1);
		return toReturn;
	}

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty(){
		if( cards.size() == 0 )return true;
		return false;
	}

    /**
     * Take all array list.
     *
     * @return the array list
     */
    public ArrayList<Card> takeAll(){
		ArrayList<Card> allCards = new ArrayList<Card>();
		for( int i = 0 ; i < cards.size() ; i++){
			allCards.add( cards.get(i) );
		}

		while( cards.size() > 0){
			cards.remove(0);
		}

		return allCards;
	}


    /**
     * Delete boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean delete(String name){
		for (int i = 0; i < cards.size(); i++){
			if(cards.get(i).getName().equals( name)){
				cards.remove(i);
				return true;
			}
		}
		return false;
	}


    /**
     * Gets top.
     *
     * @return the top
     */
    public Card getTop() {
		return cards.get(cards.size() - 1);
	}

    /**
     * Get clone pile.
     *
     * @return the pile
     */
    public Pile getClone(){
		ArrayList<Card> newCards = new ArrayList<>();
		for( int i = 0 ; i < cards.size() ; i++){
			newCards.add( cards.get(i).getClone() );
		}
		Pile newPile = new Pile(newCards);
		return newPile;
	}
}
