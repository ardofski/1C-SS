package Controller;

import Model.Character;
import Model.Option;
import Model.Room.Room;

import java.util.ArrayList;

public class EventController extends RoomController {
    public EventController(Character character, Room room) {
        super(character, room);
    }

    public String getEventName(){
        return null;
    }

    public String getEventDescription(){
        return null;
    }

    public ArrayList<Option> getOptions(){
        return null;
    }

    public void applyOption(Option option){

    }
}
