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
