package Model.Room;

import Model.*;
import Model.Relics.Relic;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * The type Treasure room.
 */
public class TreasureRoom extends Room
{
    /**
     * The Treasure.
     */
    private Reward treasure;
    /**
     * The Json.
     */
    private JSONObject json;
    /**
     * The All relics.
     */
    private ArrayList<Relic> allRelics;

    /**
     * Instantiates a new Treasure room.
     *
     * @param act the act
     */
    public TreasureRoom(int act)
    {
        this.act = act;
        treasure = new Reward();
    }

    /**
     * Set.
     *
     * @param json      the json
     * @param allRelics the all relics
     */
    public void set(JSONObject json, ArrayList<Relic> allRelics)
    {
        this.json = json;
        this.allRelics = allRelics;
    }

    /**
     * Initialize.
     */
    public void initialize()
    {
        long gold = (long) json.get("gold");
        treasure.setGold((int)gold);
        long loc =(long) json.get("relicLoc");
        treasure.setRelic(allRelics.get((int)loc));
    }

    /**
     * Gets treasure.
     *
     * @return the treasure
     */
    public Reward getTreasure() {
        return treasure;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "TreasureRoom{" +
                "treasure=" + treasure +
                ", json=" + json +
                '}';
    }
}
