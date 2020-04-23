package Model.Room;

import java.io.*;
import java.util.ArrayList;

import Model.Card;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class testRooms
{
    public static void main(String [] args)
    {
        System.out.println("cards");
        //writeRooms();
        //writeEnemies();
        //readRooms();
        //writeBuffs();
        RoomFactory factory = new RoomFactory();
        //System.out.println(factory.getAllEnemies());
        //System.out.println(factory.getAllRelics());
        System.out.println(factory.getMonsterRooms());
        EnemyRoom room1 = factory.getMonsterRooms().get(0);
        room1.initialize();
        System.out.println(room1);

        System.out.println(factory.getMerchantRooms());
        MerchantRoom room2 = factory.getMerchantRooms().get(0);
        room2.initialize();
        System.out.println(room2);
    }
    public static void writeBuffs()
    {
        //All buffs
        JSONObject buffs = new JSONObject();

        JSONObject strength = new JSONObject();
        strength.put("descprition", "Strength is a buff that increases damage dealt by Attacks.");

        JSONObject weak = new JSONObject();
        weak.put("descprition", "Weak creatures deal 25% less damage with Attacks.");

        JSONObject vulnerable = new JSONObject();
        vulnerable.put("description","Vulnerable creatures take 50% more damage from Attacks.");

        JSONObject artifact = new JSONObject();
        artifact.put("description", "Artifact is a buff that will negate the next debuff on that unit.\n" +
                "Each stack of Artifact can block 1 application of: Weak, Vulnerable,  Poison,Frail, Strength Loss, etc");

        JSONObject buffer = new JSONObject();
        buffer.put("description","Prevent the next X times you would lose HP.");

        buffs.put("strength",strength);
        buffs.put("weak",weak);
        buffs.put("vulnerable",vulnerable);
        buffs.put("artifact",artifact);
        buffs.put("buffer",buffer);



        try (FileWriter file = new FileWriter("buffs.json")) {

            file.write(buffs.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void writeEnemies()
    {
        //create 3 category arrays
        JSONArray enemies = new JSONArray();
        //JSONArray monsters = new JSONArray();
        //JSONArray elites = new JSONArray();
        //JSONArray bosses = new JSONArray();
        //types.add(monsters);
        //types.add(elites);
        //types.add(bosses);


        //create monsters

        //monster 1
        JSONObject monster1 = new JSONObject();
        monster1.put("name", "Cultist");
        monster1.put("hp", 50);
        JSONArray monster1Buffs = new JSONArray();
        monster1Buffs.add("strength"); //location of the buff in db
        monster1.put("buffs", monster1Buffs);
        JSONArray monster1Pattern = new JSONArray();

        JSONArray monster11 = new JSONArray();
        monster11.add(0);
        monster11.add(0);
        monster11.add(3);
        JSONArray monster12 = new JSONArray();
        monster12.add(6);
        monster12.add(0);
        monster12.add(0);
        monster1Pattern.add(monster11);
        monster1Pattern.add(monster12);
        monster1.put("pattern", monster1Pattern);

        //monster 2
        JSONObject monster2 = new JSONObject();
        monster2.put("name", "Jaw Worm");
        monster2.put("hp", 42);
        JSONArray monster2Buffs = new JSONArray();
        monster2.put("buffs", monster2Buffs);
        JSONArray monster2Pattern = new JSONArray();
        JSONArray monster21 = new JSONArray();
        monster21.add(11);
        monster21.add(0);
        monster21.add(0);
        JSONArray monster22 = new JSONArray();
        monster22.add(7);
        monster22.add(5);
        monster22.add(0);
        JSONArray monster23 = new JSONArray();
        monster23.add(3);
        monster23.add(6);
        monster23.add(0);
        JSONArray monster24 = new JSONArray();
        monster24.add(7);
        monster24.add(5);
        monster24.add(0);
        monster2Pattern.add(monster21);
        monster2Pattern.add(monster22);
        monster2Pattern.add(monster23);
        monster2Pattern.add(monster24);
        monster2.put("pattern", monster2Pattern);

        //monster 3
        JSONObject monster3 = new JSONObject();
        monster3.put("name", "Blue Slaver");
        monster3.put("hp", 48);
        JSONArray monster3Buffs = new JSONArray();
        monster3Buffs.add("weak"); //It is an actually  a debuff
        monster3.put("buffs", monster3Buffs);
        JSONArray monster3Pattern = new JSONArray();

        JSONArray monster31 = new JSONArray();
        monster31.add(12);
        monster31.add(0);
        monster31.add(0);
        JSONArray monster32 = new JSONArray();
        monster32.add(7);
        monster32.add(0);
        monster32.add(1);
        monster3Pattern.add(monster31);
        monster3Pattern.add(monster32);
        monster3.put("pattern", monster3Pattern);

        //add monsters to monster array
        //monsters.add(monster1);
        //monsters.add(monster2);
        //monsters.add(monster3);

        enemies.add(monster1);
        enemies.add(monster2);
        enemies.add(monster3);
        //create elites

        //elite 1
        JSONObject elite1 = new JSONObject();
        elite1.put("name", "Gremlin Nob");
        elite1.put("hp", 85);
        JSONArray elite1Buffs = new JSONArray();
        elite1Buffs.add("vulnerable"); //location of the buff in db
        elite1.put("buffs", elite1Buffs);
        JSONArray elite1Pattern = new JSONArray();

        JSONArray elite11 = new JSONArray();
        elite11.add(14);
        elite11.add(0);
        elite11.add(0);
        JSONArray elite12 = new JSONArray();
        elite12.add(6);
        elite12.add(0);
        elite12.add(2);
        JSONArray elite13 = new JSONArray();
        elite13.add(6);
        elite13.add(8);
        elite13.add(0);
        elite1Pattern.add(elite11);
        elite1Pattern.add(elite12);
        elite1Pattern.add(elite13);
        elite1.put("pattern", elite1Pattern);

        //elites.add(elite1);
        enemies.add(elite1);
        //create boss
        JSONObject boss1 = new JSONObject();
        boss1.put("name", "Slime Boss");
        boss1.put("hp", 140);
        JSONArray boss1Buffs = new JSONArray();
        boss1Buffs.add("vulnerable"); //location of the buff in db
        boss1.put("buffs", boss1Buffs);
        JSONArray boss1Pattern = new JSONArray();

        JSONArray boss11 = new JSONArray();
        boss11.add(0);
        boss11.add(0);
        boss11.add(0);
        JSONArray boss12 = new JSONArray();
        boss12.add(35);
        boss12.add(0);
        boss12.add(0);
        JSONArray boss13 = new JSONArray();
        boss13.add(0);
        boss13.add(10);
        boss13.add(4);

        boss1Pattern.add(boss11);
        boss1Pattern.add( boss12);
        boss1Pattern.add( boss13);
        boss1.put("pattern",  boss1Pattern);

        //bosses.add(boss1);
        enemies.add(boss1);

        try (FileWriter file = new FileWriter("enemies.json")) {

            file.write(enemies.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public static void readRooms()
    {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("rooms.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray acts = (JSONArray) obj;

            JSONObject act1 = (JSONObject) acts.get(0);
            JSONArray enemyRooms1 = (JSONArray) act1.get("enemyRooms");
            JSONArray merchantRooms1 = (JSONArray) act1.get("merchantRooms");
            JSONArray treRooms1 = (JSONArray) act1.get("treasureRooms");
            System.out.println(enemyRooms1);
            System.out.println(merchantRooms1);
            System.out.println(treRooms1);
            JSONObject enemy0 = (JSONObject) enemyRooms1.get(0);
            JSONArray list1 = (JSONArray) enemy0.get("enemyList");
            for (Object loc:list1 )
            {
                System.out.println(enemyRooms1.get(Math.toIntExact((Long) loc)));

            }
            //Iterate over employee array
            //cards.forEach( card -> allCards.add(parseCardObject( (JSONObject) card, "red" ) ));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public static void writeRooms()
    {
        JSONArray acts = new JSONArray();
        //Create act1
        JSONObject act1 = new JSONObject();
        //create room categories as arrays
        JSONArray enemyRooms1 = new JSONArray();
        JSONArray merchantRooms1 = new JSONArray();
        JSONArray treRooms1 = new JSONArray();

        //put the category arrays
        act1.put("enemyRooms", enemyRooms1);
        act1.put("merchantRooms", merchantRooms1);
        act1.put("treasureRooms", treRooms1);

        //Create Enemy Rooms Act1
        JSONObject enemyRoom11 = new JSONObject();
        enemyRoom11.put("type","Monster");
        JSONArray arr11 = new JSONArray();
        arr11.add(0);
        enemyRoom11.put("enemyList",arr11);

        JSONArray arr12 = new JSONArray();
        arr12.add(0);
        arr12.add(0);
        JSONObject enemyRoom12 = new JSONObject();
        enemyRoom12.put("type","Monster");
        enemyRoom12.put("enemyList",arr12);

        JSONArray arr13 = new JSONArray();
        arr13.add(1);
        arr13.add(2);
        JSONObject enemyRoom13 = new JSONObject();
        enemyRoom13.put("type","Monster");
        enemyRoom13.put("enemyList",arr13);

        JSONArray arr14 = new JSONArray();
        arr14.add(3);
        JSONObject enemyRoom14 = new JSONObject();
        enemyRoom14.put("type","Elite");
        enemyRoom14.put("enemyList",arr14);

        JSONArray arr15 = new JSONArray();
        arr15.add(4);
        JSONObject enemyRoom15 = new JSONObject();
        enemyRoom15.put("type","Boss");
        enemyRoom15.put("enemyList",arr15);

        enemyRooms1.add(enemyRoom11);
        enemyRooms1.add(enemyRoom12);
        enemyRooms1.add(enemyRoom13);
        enemyRooms1.add(enemyRoom14);
        enemyRooms1.add(enemyRoom15);


        //Create merchantRooms act1
        JSONObject merchantRoom11 = new JSONObject();
        JSONArray card11 = new JSONArray();
        JSONArray potion11 = new JSONArray();
        JSONArray relic11 = new JSONArray();
        card11.add(0);
        card11.add(1);
        card11.add(2);
        card11.add(3);
        card11.add(4);
        potion11.add(0);
        potion11.add(1);
        potion11.add(2);
        relic11.add(0);
        relic11.add(1);
        relic11.add(2);
        merchantRoom11.put("cardList", card11);
        merchantRoom11.put("potionList", potion11);
        merchantRoom11.put("relicList", relic11);


        JSONObject merchantRoom12 = new JSONObject();
        JSONArray card12 = new JSONArray();
        JSONArray potion12 = new JSONArray();
        JSONArray relic12 = new JSONArray();
        card12.add(1);
        card12.add(2);
        card12.add(3);
        card12.add(4);
        card12.add(5);
        potion12.add(1);
        potion12.add(2);
        potion12.add(3);
        relic12.add(1);
        relic12.add(2);
        relic12.add(3);
        merchantRoom12.put("cardList", card12);
        merchantRoom12.put("potionList", potion12);
        merchantRoom12.put("relicList", relic12);

        JSONObject merchantRoom13 = new JSONObject();
        JSONArray card13 = new JSONArray();
        JSONArray potion13 = new JSONArray();
        JSONArray relic13 = new JSONArray();
        card13.add(6);
        card13.add(2);
        card13.add(3);
        card13.add(4);
        card13.add(5);
        potion13.add(4);
        potion13.add(2);
        potion13.add(3);
        relic13.add(4);
        relic13.add(2);
        relic13.add(3);
        merchantRoom13.put("cardList", card13);
        merchantRoom13.put("potionList", potion13);
        merchantRoom13.put("relicList", relic13);

        merchantRooms1.add(merchantRoom11);
        merchantRooms1.add(merchantRoom12);
        merchantRooms1.add(merchantRoom13);

        //Create treasureRooms act1
        JSONObject treRoom11 = new JSONObject();
        treRoom11 .put("gold", 50);
        treRoom11 .put("relicLoc", 1);

        JSONObject treRoom12 = new JSONObject();
        treRoom12 .put("gold", 100);
        treRoom12 .put("relicLoc", 0);

        treRooms1.add(treRoom11);
        treRooms1.add(treRoom12);
        //Create act2
        JSONObject act2 = new JSONObject();
        //Create act3
        JSONObject act3 = new JSONObject();

        //add act1 act2 and act3 to acts array
        acts.add(act1);
        acts.add(act2);
        acts.add(act3);
        try (FileWriter file = new FileWriter("rooms.json")) {

            file.write(acts.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


