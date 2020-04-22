package Model.Room;

import Model.Card;
import Model.Enemy;
import Model.Potion;
import Model.Relic;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

        allEnemies = new ArrayList<Enemy>();
        allPotions = new ArrayList<Potion>();
        allRelics = new ArrayList<Relic>();
        allCards = new ArrayList<Card>();

        initCards();
        initPotions();
        initRelics();
        initEnemies();
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
                if(type.equals("monster"))
                {
                    monsterRooms.add(toAdd);
                }
                else if(type.equals("elite"))
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
                MerchantRoom toAdd = new MerchantRoom(1);
                toAdd.set(merchant,allCards,allPotions,allRelics);
            }
            for(Object treRoom: treRooms1)
            {
                JSONObject tre = (JSONObject)  treRoom;
                TreasureRoom toAdd = new TreasureRoom(1);
                toAdd.set(tre,allRelics);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void initCards()
    {

    }
    private void initPotions()
    {

    }
    private void initRelics()
    {

    }
    private void initEnemies()
    {
        
    }
}
