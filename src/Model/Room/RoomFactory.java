package Model.Room;

import DBConnection.CardFactory;
import Model.*;
import Model.Buffs.*;
import Model.Effects.ApplyBuff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import org.json.simple.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class RoomFactory
{
    //Currently it is only for ACT1.
    private ArrayList<EnemyRoom> monsterRooms;
    private ArrayList<EnemyRoom> eliteRooms;
    private ArrayList<EnemyRoom> bossRooms;

    private ArrayList<MerchantRoom> merchantRooms;
    private ArrayList<TreasureRoom> treasureRooms;

    private ArrayList<Enemy> allEnemies;

    private ArrayList<Potion> allPotions;

    private ArrayList<Relic> allRelics;

    private ArrayList<Card> allCards;


    public RoomFactory()
    {
        monsterRooms = new ArrayList<EnemyRoom>();
        eliteRooms = new ArrayList<EnemyRoom>();
        bossRooms = new ArrayList<EnemyRoom>();

        merchantRooms = new ArrayList<MerchantRoom>();
        treasureRooms = new ArrayList<TreasureRoom>();

        allEnemies = getAllEnemies();
        allPotions = getAllPotions();
        allRelics = getAllRelics();
        allCards = CardFactory.getAllCards();


        //Create the rooms and fill ArrayLists
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("rooms.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            //all acts
            JSONArray acts = (JSONArray) obj;
            //get first act
            JSONObject act1 = (JSONObject) acts.get(0);
            //get corresponding rooms
            JSONArray enemyRooms1 = (JSONArray) act1.get("enemyRooms");
            JSONArray merchantRooms1 = (JSONArray) act1.get("merchantRooms");
            JSONArray treRooms1 = (JSONArray) act1.get("treasureRooms");

            for( Object enemyRoom: enemyRooms1)
            {
                JSONObject enemy = (JSONObject) enemyRoom;
                String type = (String) enemy.get("type");
                EnemyRoom toAdd = new EnemyRoom(1);
                toAdd.set(enemy,allEnemies);
                if(type.equals("Monster"))
                {
                    monsterRooms.add(toAdd);
                }
                else if(type.equals("Elite"))
                {
                    eliteRooms.add(toAdd);
                }
                else
                {
                    bossRooms.add(toAdd);
                }

            }

            for(Object merchantRoom: merchantRooms1)
            {
                JSONObject merchant = (JSONObject)  merchantRoom;
                System.out.println(merchant);
                MerchantRoom toAdd = new MerchantRoom(1);
                toAdd.set(merchant,allCards,allPotions,allRelics);
                 merchantRooms.add(toAdd);
            }
            for(Object treRoom: treRooms1)
            {
                JSONObject tre = (JSONObject)  treRoom;
                TreasureRoom toAdd = new TreasureRoom(1);
                toAdd.set(tre,allRelics);
                treasureRooms.add(toAdd);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Enemy> getAllEnemies()
    {
        ArrayList<Enemy> result = new ArrayList<Enemy>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("enemies.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray enemies = (JSONArray) obj;
            int count = 0;
            for (Object enemy:enemies)
            {
                count++;
                JSONObject toSet = (JSONObject) enemy;
                //created an enemy
                Enemy toAdd = new Enemy((String) toSet.get("name"));
                long hp = (long)toSet.get("hp");
                toAdd.setMaxHp((int)hp);
                toAdd.setHp((int)hp);
                ArrayList<Buff> buffs = new ArrayList<Buff>();
                toAdd.setBuffs(buffs);
                //Attack pattern
                Queue<ArrayList<Effect>> effects = new LinkedList<>();
                toAdd.setEffects(effects);
                JSONArray pattern2d = (JSONArray) toSet.get("pattern");
                for( Object row: pattern2d)
                {
                    ArrayList<Effect> oneTurn = new ArrayList<Effect>();
                    effects.add(oneTurn);
                    JSONArray line = (JSONArray) row;
                    //it will become more generalized
                    long attack = (long) line.get(0);
                    long defense = (long) line.get(1);
                    long buff = (long) line.get(2);
                    if(attack > 0)
                    {
                        //create an attack effect
                        Damage damage = new Damage((int) attack,null,null);
                        oneTurn.add(damage);
                    }
                    if(defense > 0)
                    {
                        Block block = new Block((int) defense,null);
                        oneTurn.add(block);
                    }
                    if(buff > 0)
                    {
                        //create a buff according to type
                        String type = (String) ((JSONArray)toSet.get("buffs")).get(0);
                        if(type.equals("strength"))
                        {
                            Strength strength = new Strength((int) buff);
                            ApplyBuff apply = new ApplyBuff(strength,null);
                            oneTurn.add(apply);
                            if(buffs.size() == 0)
                            {
                                buffs.add(strength);
                            }

                        }
                        if(type.equals("weak"))
                        {
                            Weak weak = new Weak("weak",(int) buff);
                            ApplyBuff apply = new ApplyBuff(weak,null);
                            oneTurn.add(apply);
                            if(buffs.size() == 0)
                            {
                                buffs.add(weak);
                            }
                        }
                        if(type.equals("vulnerable"))
                        {
                            Vulnerable vulnerable = new Vulnerable("vulnerable",(int) buff);
                            ApplyBuff apply = new ApplyBuff(vulnerable,null);
                            oneTurn.add(apply);
                            if(buffs.size() == 0)
                            {
                                buffs.add(vulnerable);
                            }
                        }
                        if(type.equals("artifact"))
                        {
                            Artifact artifact = new Artifact("artifact", (int) buff);
                            ApplyBuff apply = new ApplyBuff(artifact,null);
                            oneTurn.add(apply);
                            if(buffs.size() == 0)
                            {
                                buffs.add(artifact);
                            }
                        }

                        if(type.equals("buffer"))
                        {
                            Buffer buffer = new Buffer("buffer", (int) buff);
                            ApplyBuff apply = new ApplyBuff(buffer,null);
                            oneTurn.add(apply);
                            if(buffs.size() == 0)
                            {
                                buffs.add(buffer);
                            }
                        }

                    }

                }
                result.add(toAdd);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<Potion> getAllPotions()
    {
        ArrayList<Potion> result = new ArrayList<Potion>();
        return result;
    }
    public static ArrayList<Relic> getAllRelics()
    {
        ArrayList<Relic> result = new ArrayList<Relic>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("data/relics.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray relics = (JSONArray) obj;

            for(Object relic : relics )
            {
                JSONObject toSet = (JSONObject) relic;
                String name= (String) toSet.get("name");
                String type = (String) toSet.get("type");
                String description = (String) toSet.get("description");
                Relic toAdd = new Relic(name,type,description);
                result.add(toAdd);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<EnemyRoom> getMonsterRooms() {
        return monsterRooms;
    }

    public ArrayList<EnemyRoom> getEliteRooms() {
        return eliteRooms;
    }

    public ArrayList<EnemyRoom> getBossRooms() {
        return bossRooms;
    }

    public ArrayList<MerchantRoom> getMerchantRooms() {
        return merchantRooms;
    }

    public ArrayList<TreasureRoom> getTreasureRooms() {
        return treasureRooms;
    }
}
