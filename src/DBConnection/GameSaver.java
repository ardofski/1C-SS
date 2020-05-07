package DBConnection;

import Model.*;

import java.io.*;
import java.util.ArrayList;

import Model.Character;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GameSaver {

    public GameSaver(){}

    public static void saveGame(Map map, Character character, String fileName){

        fileName = "data\\savedGames\\" + fileName;
        // creating JSONObject that holds char information
        JSONObject joChar = new JSONObject();

        joChar.put("name", character.getName());
        joChar.put("color", character.getColor());
        joChar.put("hp", character.getHp());
        joChar.put("maxHP", character.getMaxHp());
        joChar.put("gold", character.getGold());
        joChar.put("relics", character.getRelicNames());
        joChar.put("potions", character.getPotionNames());
        joChar.put("cards", character.getCardNames());
        joChar.put("pets", character.getPetNames());
        joChar.put("activePet", character.getActivePet().getName());


        //create json object that holds map information
        JSONObject joMap = new JSONObject();
        //TODO

        //create json array that holds char and map information
        JSONObject info = new JSONObject();
        info.put("character", joChar);
        info.put("map", joMap);

        //write json file
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(info.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void loadGame(Map map, Character character, String fileName){
        fileName = "data\\savedGames\\" + fileName;

                //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        JSONObject info;
        try (FileReader reader = new FileReader(fileName))
        {
            //Read JSON file
            info = (JSONObject) jsonParser.parse(reader);

            JSONObject charObj = (JSONObject) info.get("character");
            character.setName((String) charObj.get("name"));
            character.setColor((String) charObj.get("color"));
            character.setHp((int) charObj.get("hp"));
            character.setMaxHp((int) charObj.get("maxHP"));
            character.setGold((int) charObj.get("gold"));
            character.setActivePet((String) charObj.get("activePet"));

            //TODO
            ArrayList<String> relics = ((ArrayList<String>) charObj.get("relics"));
            ArrayList<String> potions = ((ArrayList<String>) charObj.get("potions"));
            ArrayList<String> cards = ((ArrayList<String>) charObj.get("cards"));
            character.setDeck(new Pile(CardFactory.getCards(cards)));
            ArrayList<String> pets = ((ArrayList<String>) charObj.get("pets"));

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

    public static void savePlayer(ArrayList<Player> players){

        JSONArray list = new JSONArray();

        for (Player player: players) {
            JSONObject plyrObj = new JSONObject();
            plyrObj.put("name", player.getName());
            plyrObj.put("score", player.getScore());

            list.add(plyrObj);
        }
        try (FileWriter file = new FileWriter("data\\players\\players.json")) {

            file.write(list.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Player> loadPlayers(){
        ArrayList<Player> players = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("data/players/players.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray cards = (JSONArray) obj;

            //Iterate over employee array
            cards.forEach( card -> players.add(parsePlayerObject( (JSONObject) card) ));
            return players;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static Player parsePlayerObject(JSONObject player){
        //Get employee first name
        String name = (String) player.get("name");
        long score = (long) player.get("score");
        return new Player(name, (int) score);

    }
}
