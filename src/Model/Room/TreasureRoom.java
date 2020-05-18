package Model.Room;

import Model.*;
import Model.Relics.Relic;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class TreasureRoom extends Room
{
    private Reward treasure;
    private JSONObject json;
    private ArrayList<Relic> allRelics;
    public TreasureRoom(int act)
    {
        this.act = act;
        treasure = new Reward();
    }
    public void set(JSONObject json, ArrayList<Relic> allRelics)
    {
        this.json = json;
        this.allRelics = allRelics;
    }

    /**
     * initializes the treasure room
     * it gets the information from a JSON object
     * which comes from database.
     */
    public void initialize()
    {
        long gold = (long) json.get("gold");
        treasure.setGold((int)gold);
        long loc =(long) json.get("relicLoc");
        treasure.setRelic(allRelics.get((int)loc));
    }
    public Reward getTreasure() {
        return treasure;
    }

    @Override
    public String toString() {
        return "TreasureRoom{" +
                "treasure=" + treasure +
                ", json=" + json +
                '}';
    }
}
