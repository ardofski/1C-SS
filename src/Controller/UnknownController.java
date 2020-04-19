package Controller;

import Model.Character;
import Model.Room.Room;

public class UnknownController extends RoomController {
    public UnknownController(Character character, Room room) {
        super(character, room);
    }

    public RoomController getRoomController(){
        return null;
    }

}
