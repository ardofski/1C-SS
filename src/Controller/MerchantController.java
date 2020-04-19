package Controller;

import Model.*;
import Model.Character;
import Model.Room.Room;

import java.util.ArrayList;

public class MerchantController extends RoomController {
    public MerchantController(Character character, Room room) {
        super(character, room);
    }

    public boolean buyPotion(Potion potion){
        return true;
    }

    public boolean buyRelic(Relic relic){
        return  false;
    }

    public boolean buyCard(Card card){
        return false;
    }

    public void deleteCard(Card card){

    }

    public Pile getCards(){
        return null;
    }

    public ArrayList<Potion> getPotions(){
        return null;
    }

    public ArrayList<Relic> getRelics(){
        return null;
    }
}
