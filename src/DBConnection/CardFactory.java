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
import java.util.Arrays;

public class CardFactory {

    private static final ArrayList<String> cardNames = new ArrayList<>(Arrays.asList("Anger", "Armaments", "Bash","BodySlam",
            "Clash","Cleave","Clothesline","Defend","Havoc","Headbutt","Headbutt", "HeavyBlade","HeavyBlade","IronWave",
            "PerfectedStrike","PerfectedStrike","PommelStrike","ShrugItOff","Strike","SwordBoomerang","Thunderclap","TrueGrit"));

    public static Card getCard(String cardName){
        boolean upgrade = false;
        switch (cardName){
            case "Anger": return new Anger(false);
            case "Armaments": return new Armaments(false);
            case "Bash": return new Bash( false);
            case "BodySlam": return new BodySlam(false);
            case "Clash": return new Clash( false);
            case "Cleave": return new Cleave(false);
            case "Clothesline": return new Clothesline(false);
            case "Defend": return new Defend( false);
            case "Havoc": return new Havoc(false);
            case "Headbutt": return new Headbutt(false);
            case "HeavyBlade": return new HeavyBlade(false);
            case "IronWave": return new IronWave( false);
            case "PerfectedStrike": return new PerfectedStrike(false);
            case "PommelStrike": return new PommelStrike( false);
            case "ShrugItOff": return new ShrugItOff( false);
            case "Strike": return new Strike(false);
            case "SwordBoomerang": return new SwordBoomerang( false);
            case "Thunderclap": return new Thunderclap(false);
            case "TrueGrit": return new TrueGrit(false);
        }
        return null;
    }

    public static Card getRandomCard(){
        int index = (int) Math.random() * cardNames.size();
        return getCard(cardNames.get(index));
    }

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

    public static ArrayList<Card> getCards(ArrayList<String> names){
        ArrayList<Card> cards = new ArrayList<>();
        names.forEach(name -> cards.add(getCard(name)));
        return cards;
    }


}
