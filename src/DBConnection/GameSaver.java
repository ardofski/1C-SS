package DBConnection;



import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import Model.*;
import Model.Cards.CardFactory;
import Model.Character;
import Model.Relics.Relic;
import Model.Relics.RelicFactory;
import Model.Room.*;
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
        if(character.getActivePet() != null )
            joChar.put("activePet", character.getActivePet().getName() );
        else
            joChar.put("activePet", null );


        //create json object that holds map information
        JSONObject joMap = new JSONObject();

        JSONArray paths = new JSONArray();
        boolean[][][][] mapPaths = map.getPaths();
        for( int i1 = 0 ; i1 < mapPaths.length ; i1++ ){
            for( int i2 = 0 ; i2 < mapPaths.length ; i2++ ){
                for( int i3 = 0 ; i3 < mapPaths.length ; i3++ ){
                    for( int i4 = 0 ; i4 < mapPaths.length ; i4++ ){
                        if(mapPaths[i1][i2][i3][i4]){
                            paths.add(new ArrayList<Integer>(Arrays.asList(i1,i2,i3,i4)));
                        }
                    }
                }
            }
        }

        JSONArray locations = new JSONArray();
        Room[][] mapLocations = map.getLocations();
        for(int i = 0; i < mapLocations.length; i++){
            for( int j = 0; j < mapLocations.length; j++){
                if(mapLocations[i][j] != null){
                    locations.add(getRoom(mapLocations[i][j], i, j));
                }
            }
        }

        joMap.put("paths", paths);
        joMap.put("locations", locations);
        joMap.put("currenti", map.getCurrentLocation()[0]);
        joMap.put("currentj", map.getCurrentLocation()[1]);

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

    private static JSONObject getRoom(Room r, int i, int j){
        JSONObject o = new JSONObject();
        if(r instanceof EnemyRoom){
            o.put("roomType", "enemyRoom");
        }
        else if(r instanceof MerchantRoom){
            o.put("roomType", "merchantRoom");
        }
        else if( r instanceof RestRoom){
            o.put("roomType", "restRoom");
        }
        else if(r instanceof TreasureRoom){
            o.put("roomType", "treasureRoom");
        }
        else if(r instanceof UnknownRoom){
            o.put("roomType", "unknownRoom");
        }
        o.put("location", new ArrayList<Integer>(Arrays.asList(i, j)));
        return o;
    }

    public static Map loadGame(Map map, Character character, String fileName){
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
            character.setHp(((Long)charObj.get("hp")).intValue());
            character.setMaxHp(((Long) charObj.get("maxHP")).intValue());
            character.setGold(((Long) charObj.get("gold")).intValue());
            character.setActivePet((String) charObj.get("activePet"));


            ArrayList<String> cards = ((ArrayList<String>) charObj.get("cards"));
            character.setDeck(new Pile(CardFactory.getCards(cards)));

            ArrayList<String> relics = ((ArrayList<String>) charObj.get("relics"));
            character.setRelics(RelicFactory.getRelics(relics));

            ArrayList<String> potions = ((ArrayList<String>) charObj.get("potions"));
            ArrayList<String> pets = ((ArrayList<String>) charObj.get("pets"));
            //TODO simdilik bos array list olarak set ediliyor.
            character.setPotions(new ArrayList<Potion>());
            character.setPets(new ArrayList<Pet>());

            JSONObject mapObj = (JSONObject) info.get("map");
            int currenti = ((Long) mapObj.get("currenti")).intValue();
            int currentj = ((Long) mapObj.get("currentj")).intValue();
            JSONArray jPaths = (JSONArray) mapObj.get("paths");
            boolean[][][][] paths = new boolean[Map.LENGTH][Map.LENGTH][Map.LENGTH][Map.LENGTH];
            for( int i1 = 0 ; i1 < Map.LENGTH ; i1++ ){
                for( int i2 = 0 ; i2 < Map.LENGTH ; i2++ ){
                    for( int i3 = 0 ; i3 < Map.LENGTH ; i3++ ){
                        for( int i4 = 0 ; i4 < Map.LENGTH ; i4++ ){
                            paths[i1][i2][i3][i4] = false;
                        }
                    }
                }
            }
            for ( Object arr:jPaths) {
                paths[((ArrayList<Long>)arr).get(0).intValue()][((ArrayList<Long>)arr).get(1).intValue()]
                        [((ArrayList<Long>)arr).get(2).intValue()][((ArrayList<Long>)arr).get(3).intValue()] = true;
            }
            JSONArray jLocations = (JSONArray) mapObj.get("locations");
            Room[][] locations = new Room[Map.LENGTH][Map.LENGTH];
            for( int i1 = 0 ; i1 < Map.LENGTH ; i1++ ){
                for( int i2 = 0 ; i2 < Map.LENGTH ; i2++ ) {
                    locations[i1][i2] = null;
                }
            }
            jLocations.forEach( location -> parseLocation((JSONObject) location, locations));
            map.setCurrentLocation(currenti, currentj);
            map.setLocations(locations);
            map.setPaths(paths);
            return new Map(locations, paths, currenti, currentj);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("HELLOOOO");
            e.printStackTrace();
        }
        return null;
    }

    //TODO
    private static void parseLocation(JSONObject jo, Room[][] rooms){
        String roomType = (String) jo.get("roomType");
        ArrayList<Long> location = (ArrayList<Long>) jo.get("location");
        Room room;
        switch (roomType){
            case "enemyRoom": room = new EnemyRoom(1);
            case "merchantRoom": room = new MerchantRoom(1);
            case "restRoom": room = new RestRoom(1);
            case "unknownRoom": room = new UnknownRoom(1);
            case "treasureRoom": room = new TreasureRoom(1);
            default: room = new Room();
        }
        rooms[location.get(0).intValue()][location.get(1).intValue()] = room;
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
