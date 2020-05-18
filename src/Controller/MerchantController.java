package Controller;

import Model.*;
import Model.Cards.Card;
import Model.Character;
import Model.Relics.Relic;
import Model.Room.MerchantRoom;
import Model.Room.Room;

import java.util.ArrayList;

/**
 * The type Merchant controller.
 */
public class MerchantController extends RoomController {
    /**
     * The constant DELETE_CARD_PRICE.
     */
    public final static int DELETE_CARD_PRICE = 15;

    /**
     * Instantiates a new Merchant controller.
     *
     * @param character the character
     * @param room      the room
     */
    public MerchantController(Character character, Room room) {
        super(character, room);
    }

    /**
     * Buy potion boolean.
     *
     * @param index the index
     * @param price the price
     * @return the boolean
     */
    public boolean buyPotion(int index, int price){
        if( character.getGold() < price || character.getPotions().size() >=3 ) return false;
        character.addPotion(((MerchantRoom)room).sellPotion(index));
        character.setGold(character.getGold() - price);
        return true;
    }

    /**
     * Buy relic boolean.
     *
     * @param index the index
     * @param price the price
     * @return the boolean
     */
    public boolean buyRelic(int index, int price){
        if( character.getGold() < price) return false;
        if (!character.addRelic(((MerchantRoom)room).sellRelic(index))) return false;
        character.setGold(character.getGold() - price);
        return true;
    }

    /**
     * Buy card boolean.
     *
     * @param index the index
     * @param price the price
     * @return the boolean
     */
    public boolean buyCard(int index, int price){
        if( character.getGold() < price) return false;
        character.getDeck().getCards().add(((MerchantRoom)room).sellCard(index));
        character.setGold(character.getGold() - price);
        return true;
    }

    /**
     * Delete card boolean.
     *
     * @param cardName the card name
     * @return the boolean
     */
    public boolean deleteCard(String cardName){
        int cGold = character.getGold();
        if( cGold < DELETE_CARD_PRICE) return false;
        character.setGold( cGold - DELETE_CARD_PRICE );
        character.deleteCard(cardName);
        return true;
    }

    /**
     * Get cards array list.
     *
     * @return the array list
     */
    public ArrayList<Card> getCards(){
        return ((MerchantRoom)room).getCards();
    }

    /**
     * Get all cards array list.
     *
     * @return the array list
     */
    public ArrayList<Card> getAllCards(){
        return character.getDeck().getCards();
    }

    /**
     * Get potions array list.
     *
     * @return the array list
     */
    public ArrayList<Potion> getPotions(){ return ((MerchantRoom)room).getPotions(); }

    /**
     * Get relics array list.
     *
     * @return the array list
     */
    public ArrayList<Relic> getRelics(){
        return ((MerchantRoom)room).getRelics();
    }

    /**
     * Get card prices array list.
     *
     * @return the array list
     */
    public ArrayList<Integer> getCardPrices(){
        return ((MerchantRoom)room).getCardPrices();
    }

    /**
     * Get relic prices array list.
     *
     * @return the array list
     */
    public ArrayList<Integer> getRelicPrices(){
        return ((MerchantRoom)room).getRelicPrices();
    }

    /**
     * Get potion prices array list.
     *
     * @return the array list
     */
    public ArrayList<Integer> getPotionPrices(){
        return ((MerchantRoom)room).getPotionPrices();
    }

    /**
     * Get character character.
     *
     * @return the character
     */
    public Character getCharacter(){
        return character;
    }
}
