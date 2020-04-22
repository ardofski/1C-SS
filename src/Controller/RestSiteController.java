package Controller;

import Model.Card;
import Model.Character;
import Model.Pile;
import Model.Room.Room;

public class RestSiteController extends RoomController {
    public RestSiteController(Character character, Room room) {
        super(character, room);
    }

    public boolean upgradeCard(Card c){
        //TODO
        return false;
    }

    public boolean smith( Card card1, Card card2, Card card3){
        //TODO
        return false;
    }
    public void rest(){
        character.setHp(character.getHp()+ (character.getMaxHp()*3/10));
    }

    public Pile getCardList(){
        return character.getDeck();
    }
}
