package Model.Room;

import Model.Buff;
import Model.Buffs.*;
import Model.Effects.ApplyBuff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
        System.out.println( "INSIDE ENEMY ROOM INIT.");
       // Math.toIntExact((Long) loc
        JSONArray enemyArr = (JSONArray) json.get("enemyList");
        //initialize the enemyroom object from database
        for (Object loc : enemyArr)
        {
            Enemy copy = allEnemies.get( Math.toIntExact((Long) loc));
            Enemy toAdd = new Enemy(copy.getName());  // buff olayi problematic olabilir.
            toAdd.setBuffs(copy.getBuffs().getBuffs());
            toAdd.setMaxHp(copy.getMaxHp());
            //deep effect copy
            Queue<ArrayList<Effect>> addEffects = new LinkedList<>();

            for(int i=0; i<copy.getEffects().size(); i++)
            {
               ArrayList<Effect> effects =  copy.getEffects().remove();
               ArrayList<Effect> newEffects = new ArrayList<Effect>();
               addEffects.add(newEffects);
               for(int k=0; k<effects.size(); k++)
               {
                   if(effects.get(k) instanceof Damage)
                   {
                       Damage damage = new Damage(((Damage)effects.get(k)).getDamage(), null,toAdd);
                       newEffects.add(damage);
                   }
                   if(effects.get(k) instanceof Block)
                   {
                       Block block = new Block(((Block)effects.get(k)).getBlock(),toAdd);
                       newEffects.add(block);
                   }
                   if(effects.get(k) instanceof ApplyBuff)
                   {
                       Buff buff = ((ApplyBuff)effects.get(k)).getBuff();

                       if(buff instanceof Vulnerable || buff instanceof Weak || buff instanceof Artifact)
                       {
                           ApplyBuff applyBuff = new ApplyBuff(buff,null);
                           newEffects.add(applyBuff);
                       }
                       else
                       {
                           ApplyBuff applyBuff = new ApplyBuff(buff,toAdd);

                           newEffects.add(applyBuff);
                       }
                   }
               }
               copy.getEffects().add(effects);

            }
            toAdd.setEffects(addEffects);
            toAdd.setHp(copy.getMaxHp());//basta full hp
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
