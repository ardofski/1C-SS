package Model.Room;

import Model.Options.Option;
import Model.Options.OptionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;

public class EventRoom extends Room
{
    private JSONObject json;
    private String name;
    private String dialogue;
    private ArrayList<Option> options;
    private Boolean isInitialized;
    public EventRoom(int act)
    {
        isInitialized = false;
        this.act = act;
        this.options = new ArrayList<Option>();
    }


    /**
     * sets the event name and description from the json object
     * @param json a json object  that contains the information of
     *             this room
     */
    public void set(JSONObject json)
    {
        this.json = json;
        this.name = (String) json.get("name");
        this.dialogue = (String) json.get("dialogue");
    }

    /**
     * initializes the room
     * reads the event from database and sets the options
     */
    public void initialize()
    {
        if( isInitialized )return;
        JSONArray optionArr = (JSONArray) json.get("options");
        for(Object option: optionArr)
        {
            String optionName = (String) (((JSONObject) option).get("name"));
            options.add(OptionFactory.getOption(optionName));
        }
        isInitialized = true;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "EventRoom{" +
                ", name='" + name + '\'' +
                ", options=" + options +
                '}';
    }

    public String getDialogue() {
        return dialogue;

    }

    public ArrayList<Option> getOptions() {
        return options;
    }
}
