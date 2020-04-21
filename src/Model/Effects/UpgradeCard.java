package Model.Effects;

import Model.Card;

public class UpgradeCard implements Effect{
    Card card;

    public UpgradeCard( Card c){
        card = c;
    }

    public Card getCard( ){
        return card;
    }
}
