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

    public void buyPotion(int index){
        character.getPotions().add(((MerchantRoom)room).sellPotion(index));
    }

    public void buyRelic(int index){
        character.getRelics().add(((MerchantRoom)room).sellRelic(index));
    }

    public void buyCard(int index){
        character.getDeck().getCards().add(((MerchantRoom)room).sellCard(index));
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
