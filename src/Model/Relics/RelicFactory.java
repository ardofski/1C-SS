package Model.Relics;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Relic factory.
 */
public class RelicFactory {


    /**
     * The constant relicNames.
     */
//this is the list of all names of the relics in the game
    private static final ArrayList<String> relicNames = new ArrayList<>(Arrays.asList("Akabeko", "Anchor",
            "BagOfPreperation", "BronzeScales",
            "BurningBlood","LizardTail","MercuryHourGlass", "OddlySmoothStone", "ThreadAndNeedle",
            "RedSkull", "RingOfTheSnake", "Vajra"));

    /**
     * Get relic relic.
     *
     * @param name the name
     * @return the relic
     */
    public static Relic getRelic(String name){

        switch(name){
            case "Akabeko": return new Akabeko();
            case "Anchor": return new Anchor();
            case "BagOfPreperation": return new BagOfPreperation();
            case "BronzeScales": return new BronzeScales();
            case "BurningBlood": return new BurningBlood();
            case "RedSkull": return new RedSkull();
            case "RingOfTheSnake": return new RingOfTheSnake();
            case "Vajra": return new Vajra();
            case "LizardTail": return new LizardTail();
            case "MercuryHourGlass": return new MercuryHourglass();
            case "OddlySmoothStone": return new OddlySmoothStone();
            case "ThreadAndNeedle": return new ThreadAndNeedle();
        }
        return null;
    }


    /**
     * Get random relic relic.
     *
     * @return the relic
     */
    public static Relic getRandomRelic(){
        int index = (int) Math.random() * relicNames.size();
        return getRelic(relicNames.get(index));
    }

    /**
     * Get all relics array list.
     *
     * @return the array list
     */
    public static ArrayList<Relic> getAllRelics(){

        ArrayList<Relic> relics = new ArrayList<>();
        relics.add( new Akabeko());
        relics.add( new Anchor());
        relics.add( new BagOfPreperation());
        relics.add( new BronzeScales());
        relics.add( new BurningBlood());
        relics.add( new RedSkull());
        relics.add( new RingOfTheSnake());
        relics.add( new Vajra());
        relics.add( new LizardTail());
        relics.add(new MercuryHourglass());
        relics.add(new OddlySmoothStone());
        relics.add((new ThreadAndNeedle()));

        return relics;
    }

    /**
     * Get relics array list.
     *
     * @param names the names
     * @return the array list
     */
    public static ArrayList<Relic> getRelics(ArrayList<String> names){
        ArrayList<Relic> relics = new ArrayList<>();
        names.forEach(name -> relics.add(getRelic(name)));
        return relics;
    }
}
