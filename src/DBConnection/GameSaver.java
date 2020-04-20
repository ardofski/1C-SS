package DBConnection;

import Model.Character;
import Model.Map;

import java.io.*;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GameSaver {

    public GameSaver(){}

    public static void saveGame(Map map, Character character, String fileName){

        // creating JSONObject that holds char information
        JSONObject joChar = new JSONObject();

        joChar.put("name", character.getName());
        joChar.put("color", character.getColor());
        joChar.put("hp", character.getHp());
        joChar.put("maxHP", character.getMaxHP());
        joChar.put("gold", character.getGold());
        joChar.put("relics", character.getRelicNames());
        joChar.put("potions", character.getPotionNames());
        joChar.put("cards", character.getCardNames());
        joChar.put("pets", character.getPetNames());
        joChar.put("activePet", character.getActivePet().getName());

        JSONObject charObject = new JSONObject();
        charObject.put("character", joChar);

        //create json object that holds map information
        JSONObject joMap = new JSONObject();
        //TODO
        JSONObject mapObject = new JSONObject();
        mapObject.put("map", joMap);

        //create json array that holds char and map information
        JSONObject info = new JSONObject();
        info.put("character", charObject);
        info.put("map", mapObject);

        //write json file
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(info.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void loadGame(Map map, Character character, String fileName){
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        JSONObject info;
        try (FileReader reader = new FileReader(fileName))
        {
            //Read JSON file
            info = (JSONObject) jsonParser.parse(reader);

            JSONObject charObj = (JSONObject) info.get("char");
            //Get employee first name
            String name = (String) charObj.get("name");
            String color = (String) charObj.get("color");
            long hp = (long) charObj.get("hp");
            long maxHP = (long) charObj.get("maxHP");
            long gold = (long) charObj.get("gold");
            ArrayList<String> relics = (ArrayList<String>) charObj.get("relics");
            ArrayList<String> potions = (ArrayList<String>) charObj.get("potions");
            ArrayList<String> cards = (ArrayList<String>) charObj.get("cards");
            ArrayList<String> pets = (ArrayList<String>) charObj.get("pets");
            String activePet = (String) charObj.get("activePet");

            //TODO karakterin set methodlari cagirilacak.

            JSONObject mapObj = (JSONObject) info.get("map");
            //TODO

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
