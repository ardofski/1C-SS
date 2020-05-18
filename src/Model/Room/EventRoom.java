package Model.Room;

import Model.Options.Option;
import Model.Options.OptionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;

/**
 * The type Event room.
 */
public class EventRoom extends Room
{
    /**
     * The Json.
     */
    private JSONObject json;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Dialogue.
     */
    private String dialogue;
    /**
     * The Options.
     */
    private ArrayList<Option> options;
    /**
     * The Is ınitialized.
     */
    private Boolean isInitialized;

    /**
     * Instantiates a new Event room.
     *
     * @param act the act
     */
    public EventRoom(int act)
    {
        isInitialized = false;
        this.act = act;
        this.options = new ArrayList<Option>();
    }


    /**
     * Set.
     *
     * @param json the json
     */
    public void set(JSONObject json)
    {
        this.json = json;
        this.name = (String) json.get("name");
        this.dialogue = (String) json.get("dialogue");
    }

    /**
     * Initialize.
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

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "EventRoom{" +
                ", name='" + name + '\'' +
                ", options=" + options +
                '}';
    }

    /**
     * Gets dialogue.
     *
     * @return the dialogue
     */
    public String getDialogue() {
        return dialogue;

    }

    /**
     * Gets options.
     *
     * @return the options
     */
    public ArrayList<Option> getOptions() {
        return options;
    }
}
