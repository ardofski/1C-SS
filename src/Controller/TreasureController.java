package Controller;

import Model.Character;
import Model.Reward;
import Model.Room.Room;

public class TreasureController extends RoomController {
    public TreasureController(Character character, Room room) {
        super(character, room);
    }

    public Reward getTreasure(){
        return null;
    }
}
