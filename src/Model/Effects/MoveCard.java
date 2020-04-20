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
}
