package DBConnection;

import java.io.*;
import java.util.ArrayList;

import Controller.*;
import Model.Card;
import Model.Character;
import Model.Map;
import Model.Player;
import Model.Relics.RelicFactory;
import Model.Reward;
import Model.Room.EnemyRoom;
import Model.Room.RestRoom;
import Model.Room.Room;
import Model.Room.TreasureRoom;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test {
    public static void main(String [] args){

        /*   
        for(int i = 0; i <6; i++){
            System.out.println(CardFactory.getRandomCard().getClass());
        }
cvn
         */
    /*
        ArrayList<Player> players = GameSaver.loadPlayers();
        System.out.println(players);
        MenuController menu = new MenuController();
        System.out.println(menu.getAllCards());
        System.out.println(menu.getAllRelics());
        menu.renamePlayer("duygu", "arda");
        System.out.println(menu.addNewPlayer("yavuz"));
        System.out.println(menu.addNewPlayer("eren"));
        System.out.println("active player is " + menu.getActivePlayer());
        System.out.println(menu.getPlayers());
        menu.setActivePlayer("cemal");
        System.out.println("active player is " + menu.getActivePlayer());
        menu.saveGame();
*/

        MenuController menu = new MenuController();
        ArrayList<String> s = menu.getSavedGamesNames();
        System.out.println(s);
        /*
        GameController gc = menu.createNewGame(1, "Ironclad");

        //gc.saveGame();

        Character ch = new Character();
        Map m2 = new Map();
        Map m1 = GameSaver.loadGame(m2, ch, "11.05.20 18.57");
        //System.out.println(ch);
        System.out.println(m1);
        System.out.println(m2);

        boolean[][][][] paths = m1.getPaths();
        for( int i1 = 0 ; i1 < Map.LENGTH ; i1++ ){
            for( int i2 = 0 ; i2 < Map.LENGTH ; i2++ ){
                for( int i3 = 0 ; i3 < Map.LENGTH ; i3++ ){
                    for( int i4 = 0 ; i4 < Map.LENGTH ; i4++ ){
                        if( paths[i1][i2][i3][i4] == true){
                            System.out.println("true = " + i1 + " " + i2 + " " + i3 + " " + i4);
                        }
                    }
                }
            }
        }
        Room[][] locations = m1.getLocations();
        for( int i1 = 0 ; i1 < Map.LENGTH ; i1++ ){
            for( int i2 = 0 ; i2 < Map.LENGTH ; i2++ ) {
                if(locations[i1][i2] != null){
                    System.out.println("location " + i1 + " " + i2 + " = " + locations[i1][i2].getClass());
                }
            }
        }
*/


        /*System.out.println(gc.getCharacter());
        Room room  = new RestRoom(1);
        //((TreasureRoom)room).initialize();
        RoomController rc = gc.createController(room);
        gc.getCharacter().setHp(50);
        ((RestSiteController)rc).rest();
        System.out.println(gc.getCharacter());
        ((RestSiteController)rc).rest();
        System.out.println(gc.getCharacter());
         */






        //ArrayList<Card> cards = CardFactory.getAllCards();
        //System.out.println(cards);
        //Card c = CardFactory.getCard("Armaments");
        //System.out.println(c);

    }

    public static void writeCards(){
        JSONObject card = new JSONObject();

        card.put("name", "Bash");
        card.put("rarity", "Starter");
        card.put("type", "Attack");
        card.put("description", "Deal 8(10) damage. Apply 2(3) Vulnerable.");
        card.put("energy", 2);

        JSONObject card2 = new JSONObject();

        card2.put("name", "Bash");
        card2.put("rarity", "Starter");
        card2.put("type", "Attack");
        card2.put("description", "Deal 8(10) damage. Apply 2(3) Vulnerable.");
        card2.put("energy", 2);

        JSONArray cards = new JSONArray();
        cards.add(card);
        cards.add(card2);

        try (FileWriter file = new FileWriter("cards.json")) {

            file.write(cards.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
