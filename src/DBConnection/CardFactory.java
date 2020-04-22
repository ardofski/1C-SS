package DBConnection;

import Model.Card;
import Model.Cards.*;
import Model.Pile;
import org.json.simple.JSONArray;
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

    public static Card getRandomCard(){
        return getCard(getRandomCardName());
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
        return getCardObject(name, rarity, type, color, description, (int)energy, false);
    }

    private static Card getCardObject(String name, String rarity, String type, String color, String description, int energy, boolean upgrade){
        switch (name){
            case "Anger": return new Anger(name, rarity, type, color, description, energy, upgrade);
            case "Armaments": return new Armaments(name, rarity, type, color, description, energy, upgrade);
            case "Bash": return new Bash(name, rarity, type, color, description, energy, upgrade);
            case "BodySlam": return new BodySlam(name, rarity, type, color, description, energy, upgrade);
            case "Clash": return new Clash(name, rarity, type, color, description, energy, upgrade);
            case "Cleave": return new Cleave(name, rarity, type, color, description, energy, upgrade);
            case "Clothesline": return new Clothesline(name, rarity, type, color, description, energy, upgrade);
            case "Defend": return new Defend(name, rarity, type, color, description, energy, upgrade);
            case "Havoc": return new Havoc(name, rarity, type, color, description, energy, upgrade);
            case "Headbutt": return new Headbutt(name, rarity, type, color, description, energy, upgrade);
            case "HeavyBlade": return new HeavyBlade(name, rarity, type, color, description, energy, upgrade);
            case "IronWave": return new IronWave(name, rarity, type, color, description, energy, upgrade);
            case "PerfectedStrike": return new PerfectedStrike(name, rarity, type, color, description, energy, upgrade);
            case "PommelStrike": return new PommelStrike(name, rarity, type, color, description, energy, upgrade);
            case "ShrugItOff": return new ShrugItOff(name, rarity, type, color, description, energy, upgrade);
            case "Strike": return new Strike(name, rarity, type, color, description, energy, upgrade);
            case "SwordBoomerang": return new SwordBoomerang(name, rarity, type, color, description, energy, upgrade);
            case "Thunderclap": return new Thunderclap(name, rarity, type, color, description, energy, upgrade);
            case "TrueGrit": return new TrueGrit(name, rarity, type, color, description, energy, upgrade);
        }
        return new Card(name, rarity, type, color, description, energy, upgrade);
    }

    private static ArrayList<String> getAllCardNames(){
        ArrayList<String> toReturn = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();

        //TODO filename yazilacak
        try (FileReader reader = new FileReader(""))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray cards = (JSONArray) obj;

            //Iterate over employee array
            cards.forEach( card -> toReturn.add( (String) card ));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    private static String getRandomCardName(){
        ArrayList<String> names = getAllCardNames();
        return names.get((int)(names.size() * Math.random()));
    }


}
