package Model.Effects;

import Model.Card;

/**
 * The type Upgrade card.
 */
public class UpgradeCard implements Effect{
    /**
     * The Card.
     */
    Card card;

    /**
     * Instantiates a new Upgrade card.
     *
     * @param c the c
     */
    public UpgradeCard( Card c){
        card = c;
    }

    /**
     * Get card card.
     *
     * @return the card
     */
    public Card getCard( ){
        return card;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString(){
        String str = new String();
        str +=  "UpgradeCard Effect [\n"
                +   "Card = " + card +  "]\n";

        return str;
    }
}
