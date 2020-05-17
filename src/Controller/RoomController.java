package Controller;

import Model.Character;
import Model.Room.Room;

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
}
