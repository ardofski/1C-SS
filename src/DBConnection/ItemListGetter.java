package DBConnection;

import Model.Card;
import Model.Pile;
import Model.Relic;

import java.io.*;
import java.util.ArrayList;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;

public class ItemListGetter {

    public static ArrayList<Relic> allRelics(){

        ArrayList<Relic> allRelics = new ArrayList<>();
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("relics.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray relics = (JSONArray) obj;

            //Iterate over employee array
            relics.forEach( relic -> allRelics.add(parseRelicObject( (JSONObject) relic) ));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return allRelics;
    }

    public static ArrayList<Card> allCards(){

        ArrayList<Card> allCards = new ArrayList<>();

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("redCards.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray cards = (JSONArray) obj;

            //Iterate over employee array
            cards.forEach( card -> allCards.add(parseCardObject( (JSONObject) card, "red" ) ));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return allCards;
    }

    private static Card parseCardObject(JSONObject card, String color)
    {
        //Get employee first name
        String name = (String) card.get("name");
        String rarity = (String) card.get("rarity");
        String type = (String) card.get("type");
        String description = (String) card.get("description");
        long energy = (long) card.get("energy");

        return new Card(name, rarity, type, color, description, (int)energy);

    }

    private static Relic parseRelicObject(JSONObject relic){
        String name = (String) relic.get("name");
        String description = (String) relic.get("description");
        String type = (String) relic.get("type");
        return new Relic(name, description, type);
    }
}
