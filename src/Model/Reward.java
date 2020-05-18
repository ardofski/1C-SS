package Model;

import Model.Cards.Card;
import Model.Relics.Relic;

import java.util.ArrayList;

/**
 * The type Reward.
 */
public class Reward {
    /**
     * The Gold.
     */
    private int gold;
    /**
     * The Relic.
     */
    private Relic relic;
    /**
     * The Pot.
     */
    private Potion pot;
    /**
     * The Cards.
     */
    private ArrayList<Card> cards;

    /**
     * Instantiates a new Reward.
     */
    public Reward()
	{
		cards = new ArrayList<Card>();
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
     * Gets cards.
     *
     * @return the cards
     */
    public ArrayList<Card> getCards() {
		return cards;
	}

    /**
     * Gets gold.
     *
     * @return the gold
     */
    public int getGold() {
		return gold;
	}

    /**
     * Sets gold.
     *
     * @param gold the gold
     */
    public void setGold(int gold) {
		this.gold = gold;
	}

    /**
     * Gets relic.
     *
     * @return the relic
     */
    public Relic getRelic() {
		return relic;
	}

    /**
     * Sets relic.
     *
     * @param relic the relic
     */
    public void setRelic(Relic relic) {
		this.relic = relic;
	}

    /**
     * Gets pot.
     *
     * @return the pot
     */
    public Potion getPot() {
		return pot;
	}

    /**
     * Sets pot.
     *
     * @param pot the pot
     */
    public void setPot(Potion pot) {
		this.pot = pot;
	}

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
	public String toString() {
		return "Reward [gold=" + gold + ", relic=" + relic + ", pot=" + pot + "]";
	}
	
}
