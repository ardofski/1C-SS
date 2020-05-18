package DBConnection;



import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import Model.*;
import Model.Cards.CardFactory;
import Model.Character;
import Model.Relics.RelicFactory;
import Model.Room.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GameSaver {

    //Please uncomment the following path names according to your operating system.
    // And leave the other one commented.

    //For Windows OS
    public static final String SAVED_GAME_PATH = "data\\savedGames\\";
    public static final String SAVED_GAME_FOLDER_PATH = "data\\savedGames";
    public static final String SAVED_PLAYER_PATH = "data\\players\\players.json";
    //end of windows os related part

    //For Linux and Mac OS
    /*
    public static final String SAVED_GAME_PATH = "data/savedGames/";
    public static final String SAVED_GAME_FOLDER_PATH = "data/savedGames";
    public static final String SAVED_PLAYER_PATH = "data/players/players.json";
     */
    //end of Linux and Mac OS related part

    public GameSaver(){}

    /**
     * this function saves the information of map, character objects
     * to a json file with given name
     * @param map map object to save
     * @param character character object to save
     * @param fileName name of the json file to save
     */
    public static void saveGame(Map map, Character character, String fileName){
        fileName = SAVED_GAME_PATH + fileName;
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

        JSONArray roomVisited = new JSONArray();
        boolean[][] mapRoomVisited = map.getRoomVisited();
        for( int i1 = 0 ; i1 < mapRoomVisited.length ; i1++ ){
            for( int i2 = 0 ; i2 < mapRoomVisited.length ; i2++ ){
                        if(mapRoomVisited[i1][i2]){
                            roomVisited.add(new ArrayList<Integer>(Arrays.asList(i1,i2)));
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
        joMap.put("roomVisited", roomVisited);
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

    /**
     * this is a helper funtion for saving the game
     * @param r
     * @param i
     * @param j
     * @return returns the json object of room r with its location information
     * and type
     */
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
        else if(r instanceof EventRoom){
            o.put("roomType", "eventRoom");
        }
        o.put("location", new ArrayList<Integer>(Arrays.asList(i, j)));
        return o;
    }

    /**
     * reads the map, character information from a json file with given name
     * @param map coming information is set to this map object
     * @param character coming information is set to this character object
     * @param fileName name of the saved game file
     */
    public static void loadGame(Map map, Character character, String fileName){
        fileName = SAVED_GAME_PATH + fileName;

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
            character.setMaxHp(((Long) charObj.get("maxHP")).intValue());
            character.setHp(((Long)charObj.get("hp")).intValue());
            character.setGold(((Long) charObj.get("gold")).intValue());
            character.setActivePet((String) charObj.get("activePet"));


            ArrayList<String> cards = ((ArrayList<String>) charObj.get("cards"));
            character.setDeck(new Pile(CardFactory.getCards(cards)));

            ArrayList<String> relics = ((ArrayList<String>) charObj.get("relics"));

            character.setRelics(RelicFactory.getRelics(relics));
            ArrayList<String> potions = ((ArrayList<String>) charObj.get("potions"));
            ArrayList<String> pets = ((ArrayList<String>) charObj.get("pets"));
            PotionFactory pf = new PotionFactory();
            character.setPotions(pf.getPotions(potions));

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

            JSONArray jRoomVisited = (JSONArray) mapObj.get("roomVisited");
            boolean[][] roomVisited = new boolean[Map.LENGTH][Map.LENGTH];
            for( int i1 = 0 ; i1 < Map.LENGTH ; i1++ ){
                for( int i2 = 0 ; i2 < Map.LENGTH ; i2++ ){
                        roomVisited[i1][i2] = false;
                }
            }
            for ( Object arr:jRoomVisited) {
                roomVisited[((ArrayList<Long>)arr).get(0).intValue()][((ArrayList<Long>)arr).get(1).intValue()] = true;
            }

            JSONArray jLocations = (JSONArray) mapObj.get("locations");
            Room[][] locations = new Room[Map.LENGTH][Map.LENGTH];
            for( int i1 = 0 ; i1 < Map.LENGTH ; i1++ ){
                for( int i2 = 0 ; i2 < Map.LENGTH ; i2++ ) {
                    locations[i1][i2] = null;
                }
            }
            RoomFactory rf = new RoomFactory();
            jLocations.forEach( location -> parseLocation(rf, (JSONObject) location, locations));
            map.setCurrentLocation(currenti, currentj);
            System.out.println("Current location i = " + currenti + " j = " + currentj);
            map.setLocations(locations);
            map.setPaths(paths);
            map.setRoomVisited(roomVisited);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("HELLOOOO");
            e.printStackTrace();
        }
    }


    /**
     * helper method for loading the game
     * parses the json object and puts the information in it to
     * the rooms
     * @param rf
     * @param jo
     * @param rooms
     */
    private static void parseLocation(RoomFactory rf, JSONObject jo, Room[][] rooms){
        String roomType = (String) jo.get("roomType");
        ArrayList<Long> location = (ArrayList<Long>) jo.get("location");
        Room room;
        switch (roomType){
            case "enemyRoom": room = rf.getMonsterRooms().get((int)(rf.getMonsterRooms().size() * Math.random())); break;
            case "merchantRoom": room = rf.getMerchantRooms().get((int)(rf.getMerchantRooms().size() * Math.random()));break;
            case "restRoom": room = new RestRoom(1); break;
            case "unknownRoom": room = new UnknownRoom(1); break;
            case "treasureRoom": room = rf.getTreasureRooms().get((int)(rf.getTreasureRooms().size() * Math.random())); break;
            case "eventRoom": room = rf.getEventRooms().get((int)(rf.getEventRooms().size() * Math.random())); break;
            default: room = new Room();
        }
        System.out.println("=========" + room.getClass());
        rooms[location.get(0).intValue()][location.get(1).intValue()] = room;
    }

    /**
     * writes the information of the players to a json file
     * @param players
     */
    public static void savePlayer(ArrayList<Player> players){

        JSONArray list = new JSONArray();

        for (Player player: players) {
            JSONObject plyrObj = new JSONObject();
            plyrObj.put("name", player.getName());
            plyrObj.put("score", player.getScore());

            list.add(plyrObj);
        }
        try (FileWriter file = new FileWriter(SAVED_PLAYER_PATH)) {

            file.write(list.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return returns the players that are in the database
     */
    public static ArrayList<Player> loadPlayers(){
        ArrayList<Player> players = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(SAVED_PLAYER_PATH))
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
