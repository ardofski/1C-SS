package Model.Room;

import Model.Option;
import Model.OptionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;

public class EventRoom extends Room
{
    private JSONObject json;
    private String name;
    private String dialogue;
    private ArrayList<Option> options;
    public EventRoom(int act)
    {
        this.act = act;
        this.options = new ArrayList<Option>();
    }
    public void set(JSONObject json)
    {
        this.json = json;
        this.name = (String) json.get("name");
        this.dialogue = (String) json.get("dialogue");
    }
    public void initialize()
    {
        JSONArray optionArr = (JSONArray) json.get("options");
        for(Object option: optionArr)
        {
            String optionName = (String) (((JSONObject) option).get("name"));
            options.add(OptionFactory.getOption(optionName));
        }
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
}
