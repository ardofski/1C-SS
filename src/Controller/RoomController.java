package Controller;

import Model.Card;
import Model.Character;
import Model.Pile;
import Model.Reward;
import Model.Room.Room;
import Model.Room.TreasureRoom;

public class RoomController {
    protected Character character;
    protected Room room;

    public RoomController(Character character, Room room){
        this.character = character;
        this.room = room;
    }

    public Character getCharacter(){
        return character;
    }
    public Reward getRewards(){return null;}

    public boolean takeGoldReward(){return false;}

    public boolean takeCardReward(int i){return false;}

    public boolean takePotionReward(){return false;}

    public boolean takeRelicReward(){return false;}
}
