package DBConnection;

import Model.Card;
import Model.Pile;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CardFactory {

    public static Card getCard(String cardName){

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        JSONObject obj;
        try (FileReader reader = new FileReader(""))
        {
            //Read JSON file
            obj = (JSONObject) jsonParser.parse(reader);
            //Get employee first name
            JSONObject card = (JSONObject) obj.get(cardName);
            return parseCard(cardName, card);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Pile getCards(ArrayList<String> names){
        //JSON parser object to parse read file
        Pile cards = new Pile();

        JSONParser jsonParser = new JSONParser();

        JSONObject obj;
        try (FileReader reader = new FileReader("data\\cards.json"))
        {
            //Read JSON file
            obj = (JSONObject) jsonParser.parse(reader);

            for ( String name:names ) {
                JSONObject card = (JSONObject) obj.get(name);
                cards.addCard(parseCard(name, card));
            }
            return cards;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Card parseCard(String name, JSONObject card){

        String rarity = (String) card.get("rarity");
        String type = (String) card.get("type");
        String color = (String) card.get("color");
        String description = (String) card.get("description");
        long energy = (long) card.get("energy");

        return new Card(name, rarity, type, color, description, (int)energy, false);
    }
}
