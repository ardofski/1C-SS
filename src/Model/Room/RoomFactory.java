package Model.Room;

import Model.Cards.Card;
import Model.Cards.CardFactory;
import Model.*;
import Model.Buffs.*;
import Model.Effects.ApplyBuff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Relics.Relic;
import Model.Relics.RelicFactory;
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

/**
 * The type Room factory.
 */
public class RoomFactory
{
    /**
     * The Monster rooms.
     */
//Currently it is only for ACT1.
    private ArrayList<EnemyRoom> monsterRooms;
    /**
     * The Elite rooms.
     */
    private ArrayList<EnemyRoom> eliteRooms;
    /**
     * The Boss rooms.
     */
    private ArrayList<EnemyRoom> bossRooms;

    /**
     * The Merchant rooms.
     */
    private ArrayList<MerchantRoom> merchantRooms;
    /**
     * The Treasure rooms.
     */
    private ArrayList<TreasureRoom> treasureRooms;
    /**
     * The Event rooms.
     */
    private ArrayList<EventRoom> eventRooms;

    /**
     * The Unknown.
     */
    private UnknownRoom unknown;

    /**
     * The All enemies.
     */
    private ArrayList<Enemy> allEnemies;

    /**
     * The All potions.
     */
    private ArrayList<Potion> allPotions;

    /**
     * The All relics.
     */
    private ArrayList<Relic> allRelics;

    /**
     * The All cards.
     */
    private ArrayList<Card> allCards;

    /**
     * Instantiates a new Room factory.
     */
    public RoomFactory()
    {
        monsterRooms = new ArrayList<EnemyRoom>();
        eliteRooms = new ArrayList<EnemyRoom>();
        bossRooms = new ArrayList<EnemyRoom>();
        eventRooms = new ArrayList<EventRoom>();
        merchantRooms = new ArrayList<MerchantRoom>();
        treasureRooms = new ArrayList<TreasureRoom>();

        allEnemies = getAllEnemies();
        PotionFactory potFactor = new PotionFactory();
        allPotions = potFactor.getAllPotions();
        allRelics = RelicFactory.getAllRelics();
        allCards = CardFactory.getAllCards();

        //Create the rooms and fill ArrayLists
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("data/rooms.json"))
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
        try (FileReader reader = new FileReader("data/eventRooms.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray events = (JSONArray) obj;

            for(Object event: events)
            {
                JSONObject json = (JSONObject) event;
                EventRoom toAdd = new EventRoom(1);
                toAdd.set(json);
                eventRooms.add(toAdd);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        unknown = new UnknownRoom(1);
        unknown.setRooms(monsterRooms,merchantRooms,treasureRooms);
    }

    /**
     * Gets all enemies.
     *
     * @return the all enemies
     */
    public static ArrayList<Enemy> getAllEnemies()
    {
        ArrayList<Enemy> result = new ArrayList<Enemy>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("data/enemies.json"))
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
                toAdd.setMaxHp((int)hp);
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
                        Damage damage = new Damage((int) attack,null,toAdd);
                        oneTurn.add(damage);
                    }
                    if(defense > 0)
                    {
                        Block block = new Block((int) defense,toAdd);
                        oneTurn.add(block);
                    }
                    if(buff > 0)
                    {
                        //create a buff according to type
                        String type = (String) ((JSONArray)toSet.get("buffs")).get(0);
                        if(type.equals("strength"))
                        {
                            Strength strength = new Strength((int) buff);
                            ApplyBuff apply = new ApplyBuff(strength,toAdd);
                            oneTurn.add(apply);
                        }
                        if(type.equals("weak"))
                        {
                            Weak weak = new Weak((int) buff);
                            ApplyBuff apply = new ApplyBuff(weak,null);
                            oneTurn.add(apply);
                        }
                        if(type.equals("vulnerable"))
                        {
                            Vulnerable vulnerable = new Vulnerable((int) buff);
                            ApplyBuff apply = new ApplyBuff(vulnerable,null);
                            oneTurn.add(apply);
                        }
                        if(type.equals("artifact"))
                        {
                            Artifact artifact = new Artifact( (int) buff);
                            ApplyBuff apply = new ApplyBuff(artifact,null);
                            oneTurn.add(apply);
                        }

                        if(type.equals("buffer"))
                        {
                            Buffer buffer = new Buffer((int) buff);
                            ApplyBuff apply = new ApplyBuff(buffer,toAdd);
                            oneTurn.add(apply);
                        }
                        if(type.equals("thorns"))
                        {
                            Thorns thorns = new Thorns((int) buff);
                            ApplyBuff apply = new ApplyBuff(thorns,null);
                            oneTurn.add(apply);
                        }
                        if(type.equals("ritual"))
                        {
                            Ritual ritual = new Ritual((int) buff);
                            ApplyBuff apply = new ApplyBuff(ritual,toAdd);
                            oneTurn.add(apply);
                        }
                        if(type.equals("intangible"))
                        {
                            Intangible intangible = new Intangible((int) buff);
                            ApplyBuff apply = new ApplyBuff(intangible,toAdd);
                            oneTurn.add(apply);
                        }
                        if(type.equals("metallicize"))
                        {
                            Metallicize metallicize = new Metallicize((int) buff);
                            ApplyBuff apply = new ApplyBuff(metallicize,toAdd);
                            oneTurn.add(apply);
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

    /**
     * Gets all potions.
     *
     * @return the all potions
     */
    public static ArrayList<Potion> getAllPotions()
    {
        ArrayList<Potion> result = new ArrayList<Potion>();
        return result;
    }

    /**
     * Gets random event room.
     *
     * @return the random event room
     */
    public Room getRandomEventRoom()
    {
        int loc = (int) (Math.random() * eventRooms.size());
        return eventRooms.get(loc);

    }

    /**
     * Gets random room.
     *
     * @return the random room
     */
    public Room getRandomRoom()
    {
        int num = (int) (Math.random()*25);
        if( 0<=num && num <= 11)
        {
            //enemy
            int newrand = (int)(Math.random()*14);
            if(0<=newrand && newrand <= 12)
            {
                int loc = (int) (Math.random()*monsterRooms.size());
                return monsterRooms.get(loc);
            }
            if(13 <= newrand)
            {
                int loc = (int) (Math.random() * eliteRooms.size());
                return eliteRooms.get(loc);
            }

        }
        if( 12<=num && num <= 15)
        {
            //merchant
            int loc = (int) (Math.random()*merchantRooms.size());
            return merchantRooms.get(loc);
        }
        if( 16<=num && num <= 17)
        {
            return new RestRoom(1);
        }
        if( num==18)
        {
            // treasure
            int loc = (int) (Math.random() * treasureRooms.size());
            return treasureRooms.get(loc);
        }
        /*
        if( num==19)
        {
            return unknown;
        }

         */
        if(num>=19)//TODO
        {
            int loc = (int) (Math.random() * eventRooms.size());
            return eventRooms.get(loc);
        }
        return null;
    }

    /**
     * Gets boss room.
     *
     * @return the boss room
     */
    public EnemyRoom getBossRoom()
    {
        int loc = (int) (bossRooms.size()*Math.random());
        return bossRooms.get(loc);
    }


    /**
     * Gets monster rooms.
     *
     * @return the monster rooms
     */
    public ArrayList<EnemyRoom> getMonsterRooms() {
        return monsterRooms;
    }

    /**
     * Gets elite rooms.
     *
     * @return the elite rooms
     */
    public ArrayList<EnemyRoom> getEliteRooms() {
        return eliteRooms;
    }

    /**
     * Gets boss rooms.
     *
     * @return the boss rooms
     */
    public ArrayList<EnemyRoom> getBossRooms() {
        return bossRooms;
    }

    /**
     * Gets merchant rooms.
     *
     * @return the merchant rooms
     */
    public ArrayList<MerchantRoom> getMerchantRooms() {
        return merchantRooms;
    }

    /**
     * Gets treasure rooms.
     *
     * @return the treasure rooms
     */
    public ArrayList<TreasureRoom> getTreasureRooms() {
        return treasureRooms;
    }

    /**
     * Gets event rooms.
     *
     * @return the event rooms
     */
    public ArrayList<EventRoom> getEventRooms() {
        return eventRooms;
    }
}
