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

    public boolean buyPotion(Potion potion){
        //TODO
        return true;
    }

    public boolean buyRelic(Relic relic){
        //TODO
        return  false;
    }

    public boolean buyCard(Card card){
        //TODO
        return false;
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
