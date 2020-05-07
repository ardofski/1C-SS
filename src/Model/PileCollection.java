package Model;

import java.util.ArrayList;

public class PileCollection {
    private Pile handPile, drawPile, exhaustPile, discardPile;

    public Pile getDrawPile() {
        return drawPile;
    }

    public Pile getExhaustPile() {
        return exhaustPile;
    }

    public Pile getDiscardPile() {
        return discardPile;
    }

    public PileCollection(Pile hP, Pile drawP, Pile eP, Pile disP){
        handPile = hP;
        drawPile = drawP;
        exhaustPile = eP;
        discardPile = disP;
    }

    //gettters
    public Pile getHandPile(){
        return handPile;
    }

    public void handToDiscard(){
        ArrayList<Card> handToDiscard = handPile.takeAll();
        for( int i = 0 ; i < handToDiscard.size() ; i++){
            discardPile.addCard( handToDiscard.get(i) );
        }
    }


    private void discardToDraw(){
        discardPile.shuffle();
        ArrayList<Card> allCards = discardPile.takeAll();
        for( int i = 0 ; i < allCards.size(); i++){
            drawPile.addCard( allCards.get(i) );
        }
        drawPile.shuffle();
    }

    public void drawCard(){
        if( drawPile.isEmpty() ) discardToDraw();
        Card c = drawPile.takeTop();
        handPile.addCard(c);
    }

}
