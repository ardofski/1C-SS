package Model.Room;

import Model.*;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class TreasureRoom extends Room
{
    private Reward treasure;

    public TreasureRoom(int act)
    {
        this.act = act;
        treasure = new Reward();
    }

    public void initialize(JSONObject json, ArrayList<Relic> allRelics)
    {
        treasure.setGold((int) json.get("gold"));
        treasure.setRelic(allRelics.get((int) json.get("relic") ));
    }
    public Reward getTreasure() {
        return treasure;
    }
}
