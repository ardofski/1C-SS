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
        c.upgrade();
        return false;
    }
    public boolean upgradeCard(String name){
        for(int i = 0 ; i < character.getDeck().getCards().size(); i++) {
            if (name == character.getDeck().getCards().get(i).getName()){
                character.getDeck().getCards().get(i).upgrade();
                return true;
            }
        }
        return false;
    }

    public boolean smith( Card card1, Card card2, Card card3){
        //TODO
        return false;
    }
    public void rest(){
        int maxHP = character.getMaxHp();
        int newValue = character.getHp()+ (maxHP*3/10);
        character.setHp(newValue<=maxHP ? newValue : maxHP);
    }

    public Pile getCardList(){
        return character.getDeck();
    }
}
