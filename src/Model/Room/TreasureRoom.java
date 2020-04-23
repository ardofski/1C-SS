package Model.Room;

import Model.*;
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
    public void initialize()
    {
        this.json = json;
        treasure.setGold((int) json.get("gold"));
        treasure.setRelic(allRelics.get((int) json.get("relic") ));
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
