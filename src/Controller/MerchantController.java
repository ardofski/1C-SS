package Controller;

import Model.*;
import Model.Character;
import Model.Relics.Relic;
import Model.Room.MerchantRoom;
import Model.Room.Room;

import java.util.ArrayList;

public class MerchantController extends RoomController {
    public MerchantController(Character character, Room room) {
        super(character, room);
    }

    public boolean buyPotion(int index, int price){
        if( character.getGold() < price) return false;
        character.getPotions().add(((MerchantRoom)room).sellPotion(index));
        character.setGold(character.getGold() - price);
        return true;
    }

    public boolean buyRelic(int index, int price){
        if( character.getGold() < price) return false;
        character.getRelics().add(((MerchantRoom)room).sellRelic(index));
        character.setGold(character.getGold() - price);
        return true;
    }

    public boolean buyCard(int index, int price){
        if( character.getGold() < price) return false;
        character.getDeck().getCards().add(((MerchantRoom)room).sellCard(index));
        character.setGold(character.getGold() - price);
        return true;
    }

    public void deleteCard(String cardName){ character.deleteCard(cardName); }

    public ArrayList<Card> getCards(){
        return ((MerchantRoom)room).getCards();
    }

    public ArrayList<Potion> getPotions(){ return ((MerchantRoom)room).getPotions(); }

    public ArrayList<Relic> getRelics(){
        return ((MerchantRoom)room).getRelics();
    }
}
