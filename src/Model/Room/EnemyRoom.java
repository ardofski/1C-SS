package Model.Room;

import Model.Enemy;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class EnemyRoom extends Room
{
    private String type;
    private ArrayList<Enemy> enemies;
    private JSONObject json;
    ArrayList<Enemy> allEnemies;
    public EnemyRoom(int act)
    {
        this.act = act;
        enemies = new ArrayList<Enemy>();
    }
    public void set(JSONObject json,ArrayList<Enemy> allEnemies)
    {
        this.json = json;
        this.allEnemies = allEnemies;
        this.type = (String) json.get("type");
    }
    public void initialize()
    {

       // Math.toIntExact((Long) loc
        JSONArray enemyArr = (JSONArray) json.get("enemyList");
        //initialize the enemyroom object from database
        for (Object loc : enemyArr)
        {
            Enemy copy = allEnemies.get( Math.toIntExact((Long) loc));
            Enemy toAdd = new Enemy(copy.getName());  // buff olayi problematic olabilir.
            toAdd .setBuffs(copy.getBuffs());
            toAdd .setMaxHp(copy.getMaxHp());
            toAdd.setEffects(copy.getEffects());
            toAdd .setHp(copy.getMaxHp());//basta full hp
            enemies.add(toAdd);
        }
    }
    public String getType() {
        return type;
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    @Override
    public String toString() {
        return "EnemyRoom{" +
                "type='" + type + '\'' +
                ", enemies=" + enemies +
                ", json=" + json +
                '}';
    }
}
