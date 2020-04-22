package DBConnection;

import java.io.*;
import java.util.ArrayList;

import Controller.MenuController;
import Model.Card;
import Model.Player;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test {
    public static void main(String [] args){

        /*
        for(int i = 0; i <6; i++){
            System.out.println(CardFactory.getRandomCard().getClass());
        }

         */

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
