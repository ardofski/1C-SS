package Model.Room;

import Model.Enemy;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class EnemyRoom extends Room
{
    private String type;
    private ArrayList<Enemy> enemies;

    public EnemyRoom(String type,int act)
    {
        this.act = act;
        //this.type = type;
        enemies = new ArrayList<Enemy>();
    }

    public void init(JSONObject json, ArrayList<Enemy> allEnemies)
    {
        this.type = (String) json.get("type");
       // Math.toIntExact((Long) loc
        JSONArray enemyArr = (JSONArray) json.get("enemyList");
        //initialize the enemyroom object from database
        for (Object loc : enemyArr)
        {
            //TODO Enemy constructor i ve comment attigim son iki satir hata veriyordu
            //TODO nasil duzeltecegimi bilmedigim icin commentledim
            Enemy copy = allEnemies.get( Math.toIntExact((Long) loc));
            Enemy toAdd = new Enemy("");
            toAdd .setName(copy.getName());
            toAdd .setMaxHp(copy.getMaxHp());
            toAdd .setBuffs(copy.getBuffs()); // buff olayi problematic olabilir.
            //toAdd .setCurrentHp(copy.getMaxHp());//basta full hp
            //enemies.add(add);
        }
    }
}
