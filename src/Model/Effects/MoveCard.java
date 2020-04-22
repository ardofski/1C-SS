package Model.Effects;

import Model.Card;
import Model.Pile;

public class MoveCard implements Effect {
    Pile sourcePile;
    Pile destPile;
    Card card;

    public MoveCard(Pile s,Pile d,Card c){
        this.sourcePile = s;
        this.destPile = d;
        this.card = c;
    }

    public Pile getSourcePile(){
        return sourcePile;
    }
    public Pile getDestPile(){
        return destPile;
    }

    public Card getCard(){
        return card;
    }

    public String toString(){
        String str = new String();
        str +=  "MoveCard Effect [\n"
                +   "Source Pile = " + sourcePile +  "\n"
                +   "Dest Pile = "   + destPile +    "\n"
                +   "Card = "        + card + "]\n";

        return str;
    }
}
