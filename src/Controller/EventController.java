package Controller;

import Model.Character;
import Model.Options.Option;
import Model.Room.EventRoom;
import Model.Room.Room;

import java.util.ArrayList;

/**
 * The type Event controller.
 */
public class EventController extends RoomController {
    /**
     * Instantiates a new Event controller.
     *
     * @param character the character
     * @param room      the room
     */
    public EventController(Character character, Room room) {
        super(character, room);
    }

    /**
     * Get event name string.
     *
     * @return the string
     */
    public String getEventName(){
        return ((EventRoom)room).getName();
    }

    /**
     * Get event description string.
     *
     * @return the string
     */
    public String getEventDescription(){
        return ((EventRoom)room).getDialogue();
    }

    /**
     * Get options array list.
     *
     * @return the array list
     */
    public ArrayList<Option> getOptions(){
        return ((EventRoom)room).getOptions();
    }

    /**
     * Apply option.
     *
     * @param index the index
     */
    public void applyOption(int index){
        ((EventRoom)room).getOptions().get(index).applyOption(character);
    }

    /**
     * Get character character.
     *
     * @return the character
     */
    public Character getCharacter(){ return character;}
}
