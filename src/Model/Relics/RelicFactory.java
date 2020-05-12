package Model.Relics;

import Model.Card;

import java.util.ArrayList;
import java.util.Arrays;

public class RelicFactory {

    private static final ArrayList<String> relicNames = new ArrayList<>(Arrays.asList("Akabeko", "Anchor", "BagOfPreperation", "BronzeScales",
            "BurningBlood", "CrackedCore", "RedSkull", "RingOfTheSnake", "Vajra"));

    public static Relic getRelic(String name){

        switch(name){
            case "Akabeko": return new Akabeko();
            case "Anchor": return new Anchor();
            case "BagOfPreperation": return new BagOfPreperation();
            case "BronzeScales": return new BronzeScales();
            case "BurningBlood": return new BurningBlood();
            case "CrackedCore": return new CrackedCore();
            case "RedSkull": return new RedSkull();
            case "RingOfTheSnake": return new RingOfTheSnake();
            case "Vajra": return new Vajra();
        }
        return null;
    }


    public static Relic getRandomRelic(){
        int index = (int) Math.random() * relicNames.size();
        return getRelic(relicNames.get(index));
    }

    public static ArrayList<Relic> getAllRelics(){

        ArrayList<Relic> relics = new ArrayList<>();
        relics.add( new Akabeko());
        relics.add( new Anchor());
        relics.add( new BagOfPreperation());
        relics.add( new BronzeScales());
        relics.add( new BurningBlood());
        relics.add( new CrackedCore());
        relics.add( new RedSkull());
        relics.add( new RingOfTheSnake());
        relics.add( new Vajra());

        return relics;
    }

    public static ArrayList<Relic> getRelics(ArrayList<String> names){
        ArrayList<Relic> relics = new ArrayList<>();
        names.forEach(name -> relics.add(getRelic(name)));
        return relics;
    }
}