package DBConnection;

import java.io.*;
import java.util.ArrayList;

import Model.Card;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test {
    public static void main(String [] args){

        ArrayList<Card> cards = ItemListGetter.allCards();
        System.out.println(cards);

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
