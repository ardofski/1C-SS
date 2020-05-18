package Controller;

import Model.*;
import Model.Character;
import Model.Relics.Relic;
import Model.Room.MerchantRoom;
import Model.Room.Room;

import java.util.ArrayList;

public class MerchantController extends RoomController {
    public final static int DELETE_CARD_PRICE = 15;
    public MerchantController(Character character, Room room) {
        super(character, room);
    }

    /**
     * checks if the golds of the character is enough to buy the item, and the
     * maximum potion limit of the character
     * @param index index of the sold potion the list
     * @param price price of the item
     * @return returns true if the operation is done successfully, false if not
     */
    public boolean buyPotion(int index, int price){
        if( character.getGold() < price || character.getPotions().size() >=3 ) return false;
        character.addPotion(((MerchantRoom)room).sellPotion(index));
        character.setGold(character.getGold() - price);
        return true;
    }

    /**
     * checks if the golds of the character is enough to buy the item, and if
     * the selected relic is present in the character
     * @param index index of the sold relic the list
     * @param price price of the item
     * @return returns true if the operation is done successfully, false if not
     */
    public boolean buyRelic(int index, int price){
        if( character.getGold() < price) return false;
        if (!character.addRelic(((MerchantRoom)room).sellRelic(index))) return false;
        character.setGold(character.getGold() - price);
        return true;
    }

    /**
     * checks if the golds of the character is enough to buy the item.
     * @param index index of the sold card the list
     * @param price price of the item
     * @return returns true if the operation is done successfully, false if not
     */
    public boolean buyCard(int index, int price){
        if( character.getGold() < price) return false;
        character.getDeck().getCards().add(((MerchantRoom)room).sellCard(index));
        character.setGold(character.getGold() - price);
        return true;
    }

    /**
     * deletes the card with given name from the character's deck
     * @param cardName name of the card to be deleted
     * @return returns true if the operation is done successfully, false if not
     */
    public boolean deleteCard(String cardName){
        int cGold = character.getGold();
        if( cGold < DELETE_CARD_PRICE) return false;
        character.setGold( cGold - DELETE_CARD_PRICE );
        character.deleteCard(cardName);
        return true;
    }

    public ArrayList<Card> getCards(){
        return ((MerchantRoom)room).getCards();
    }
    public ArrayList<Card> getAllCards(){
        return character.getDeck().getCards();
    }

    public ArrayList<Potion> getPotions(){ return ((MerchantRoom)room).getPotions(); }

    public ArrayList<Relic> getRelics(){
        return ((MerchantRoom)room).getRelics();
    }

    public ArrayList<Integer> getCardPrices(){
        return ((MerchantRoom)room).getCardPrices();
    }
    public ArrayList<Integer> getRelicPrices(){
        return ((MerchantRoom)room).getRelicPrices();
    }
    public ArrayList<Integer> getPotionPrices(){
        return ((MerchantRoom)room).getPotionPrices();
    }
    public Character getCharacter(){
        return character;
    }
}
