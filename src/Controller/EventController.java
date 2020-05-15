package Controller;

import Model.Character;
import Model.Options.Option;
import Model.Room.EventRoom;
import Model.Room.Room;

import java.util.ArrayList;

public class EventController extends RoomController {
    public EventController(Character character, Room room) {
        super(character, room);
    }

    public String getEventName(){
        return ((EventRoom)room).getName();
    }

    public String getEventDescription(){
        return ((EventRoom)room).getDialogue();
    }

    public ArrayList<Option> getOptions(){
        return ((EventRoom)room).getOptions();
    }

    public void applyOption(Option option){
        option.applyOption(character);
    }
}
