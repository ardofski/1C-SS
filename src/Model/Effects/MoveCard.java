package Model.Effects;

import Model.Card;
import Model.Pile;

/**
 * The type Move card.
 */
public class MoveCard implements Effect {
    /**
     * The Source pile.
     */
    Pile sourcePile;
    /**
     * The Dest pile.
     */
    Pile destPile;
    /**
     * The Card.
     */
    Card card;

    /**
     * Instantiates a new Move card.
     *
     * @param s the s
     * @param d the d
     * @param c the c
     */
    public MoveCard(Pile s,Pile d,Card c){
        this.sourcePile = s;
        this.destPile = d;
        this.card = c;
    }

    /**
     * Get source pile pile.
     *
     * @return the pile
     */
    public Pile getSourcePile(){
        return sourcePile;
    }

    /**
     * Get dest pile pile.
     *
     * @return the pile
     */
    public Pile getDestPile(){
        return destPile;
    }

    /**
     * Get card card.
     *
     * @return the card
     */
    public Card getCard(){
        return card;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    public String toString(){
        String str = new String();
        str +=  "MoveCard Effect [\n"
                +   "Source Pile = " + sourcePile +  "\n"
                +   "Dest Pile = "   + destPile +    "\n"
                +   "Card = "        + card + "]\n";

        return str;
    }
}
