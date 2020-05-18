package Model.Cards;

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
import java.util.Arrays;

/**
 * The type Card factory.
 */
public class CardFactory {

    /**
     * The constant cardNames.
     */
    private static final ArrayList<String> cardNames = new ArrayList<>(Arrays.asList("Anger", "Armaments", "Bash","BodySlam",
            "Clash","Cleave","Clothesline","Defend","Havoc","Headbutt","Headbutt", "HeavyBlade","HeavyBlade","IronWave",
            "PerfectedStrike","PerfectedStrike","PommelStrike","ShrugItOff","Strike","SwordBoomerang","Thunderclap","TrueGrit"));

    /**
     * Get card card.
     *
     * @param cardName the card name
     * @param upgrade  the upgrade
     * @return the card
     */
    public static Card getCard(String cardName, boolean upgrade){
        switch (cardName){
            case "Anger": return new Anger(upgrade);
            case "Armaments": return new Armaments(upgrade);
            case "Bash": return new Bash( upgrade);
            case "BodySlam": return new BodySlam(upgrade);
            case "Clash": return new Clash( upgrade);
            case "Cleave": return new Cleave(upgrade);
            case "Clothesline": return new Clothesline(upgrade);
            case "Defend": return new Defend( upgrade);
            case "Havoc": return new Havoc(upgrade);
            case "Headbutt": return new Headbutt(upgrade);
            case "HeavyBlade": return new HeavyBlade(upgrade);
            case "IronWave": return new IronWave( upgrade);
            case "PerfectedStrike": return new PerfectedStrike(upgrade);
            case "PommelStrike": return new PommelStrike( upgrade);
            case "ShrugItOff": return new ShrugItOff( upgrade);
            case "Strike": return new Strike(upgrade);
            case "SwordBoomerang": return new SwordBoomerang( upgrade);
            case "Thunderclap": return new Thunderclap(upgrade);
            case "TrueGrit": return new TrueGrit(upgrade);
        }
        return null;
    }

    /**
     * Get random card card.
     *
     * @return the card
     */
    public static Card getRandomCard(){
        int index = (int) Math.random() * cardNames.size();
        return getCard(cardNames.get(index),false);
    }

    /**
     * Get all cards array list.
     *
     * @return the array list
     */
    public static ArrayList<Card> getAllCards(){
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Anger(false));
        cards.add(new Armaments(false));
        cards.add(new Bash( false));
        cards.add(new BodySlam(false));
        cards.add(new Clash( false));
        cards.add(new Cleave(false));
        cards.add(new Clothesline(false));
        cards.add(new Defend( false));
        cards.add(new Havoc(false));
        cards.add(new Headbutt(false));
        cards.add(new HeavyBlade(false));
        cards.add(new IronWave( false));
        cards.add(new PerfectedStrike(false));
        cards.add(new PommelStrike( false));
        cards.add(new ShrugItOff( false));
        cards.add(new Strike(false));
        cards.add(new SwordBoomerang( false));
        cards.add(new Thunderclap(false));
        cards.add(new TrueGrit(false));

        return cards;
    }

    /**
     * Get cards array list.
     *
     * @param names the names
     * @return the array list
     */
    public static ArrayList<Card> getCards(ArrayList<String> names){
        ArrayList<Card> cards = new ArrayList<>();
        names.forEach(name -> cards.add(getCard(name,false)));
        return cards;
    }


}
