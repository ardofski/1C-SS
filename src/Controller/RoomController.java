package Controller;

import Model.Character;
import Model.Room.Room;

public class RoomController {
    private Character character;
    private Room room;

    public RoomController(Character character, Room room){
        this.character = character;
        this.room = room;
    }
}
