package Model;

import Model.Cards.Card;

import java.util.ArrayList;

/**
 * The type Pile collection.
 */
public class PileCollection {
    /**
     * The Hand pile.
     */
    private Pile handPile, /**
     * The Draw pile.
     */
    drawPile, /**
     * The Exhaust pile.
     */
    exhaustPile, /**
     * The Discard pile.
     */
    discardPile;

    /**
     * Gets draw pile.
     *
     * @return the draw pile
     */
    public Pile getDrawPile() {
        return drawPile;
    }

    /**
     * Gets exhaust pile.
     *
     * @return the exhaust pile
     */
    public Pile getExhaustPile() {
        return exhaustPile;
    }

    /**
     * Gets discard pile.
     *
     * @return the discard pile
     */
    public Pile getDiscardPile() {
        return discardPile;
    }

    /**
     * Instantiates a new Pile collection.
     *
     * @param hP    the h p
     * @param drawP the draw p
     * @param eP    the e p
     * @param disP  the dis p
     */
    public PileCollection(Pile hP, Pile drawP, Pile eP, Pile disP){
        handPile = hP;
        drawPile = drawP;
        exhaustPile = eP;
        discardPile = disP;
    }

    /**
     * Get hand pile pile.
     *
     * @return the pile
     */
//gettters
    public Pile getHandPile(){
        return handPile;
    }


    /**
     * Hand to discard.
     */
    public void handToDiscard(){
        ArrayList<Card> handToDiscard = handPile.takeAll();
        for( int i = 0 ; i < handToDiscard.size() ; i++){
            discardPile.addCard( handToDiscard.get(i) );
        }
    }


    /**
     * Discard to draw.
     */
    private void discardToDraw(){
        discardPile.shuffle();
        ArrayList<Card> allCards = discardPile.takeAll();
        for( int i = 0 ; i < allCards.size(); i++){
            drawPile.addCard( allCards.get(i) );
        }
        drawPile.shuffle();
    }

    /**
     * Draw card.
     */
    public void drawCard(){
        if( drawPile.isEmpty() ) discardToDraw();
        Card c = drawPile.takeTop();
        handPile.addCard(c);
    }

}
