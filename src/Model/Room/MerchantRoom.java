package Model.Room;

import Model.*;
import Model.Relics.Relic;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class MerchantRoom extends Room
{
    private ArrayList<Card> cards;
    private ArrayList<Potion> potions;
    private ArrayList<Relic> relics;

    private ArrayList<Card> allCards;
    private ArrayList<Potion> allPotions;
    private ArrayList<Relic> allRelics;
    private ArrayList<Integer> cardPrices;
    private ArrayList<Integer> relicPrices;
    private ArrayList<Integer> potionPrices;
    private JSONObject json;
    public MerchantRoom(int act)
    {
        this.act = act;
        cards = new ArrayList<Card>();
        potions = new ArrayList<Potion>();
        relics = new ArrayList<Relic>();
        cardPrices = new ArrayList<>();
        relicPrices = new ArrayList<>();
        potionPrices = new ArrayList<>();
    }

    public void set(JSONObject json, ArrayList<Card> allCards, ArrayList<Potion> allPotions, ArrayList<Relic> allRelics)
    {
        this.json = json;
        this.allCards = allCards;
        this.allPotions = allPotions;
        this.allRelics = allRelics;
    }


    /**
     * initializes the merchant room object,
     * gets information from database, and sets
     * card, potion and relic lists that merchant sells
     */
    public void initialize()
    {
        JSONArray cardArr = (JSONArray) json.get("cardList");
        JSONArray potionArr = (JSONArray) json.get("potionList");
        JSONArray relicArr = (JSONArray) json.get("cardList");
        for(Object loc: cardArr)
        {
            cards.add(allCards.get(Math.toIntExact((Long) loc)));
        }
        for(Object loc: potionArr)
        {
            potions.add(allPotions.get(Math.toIntExact((Long) loc)));
        }
        for(Object loc: relicArr)
        {
            relics.add(allRelics.get(Math.toIntExact((Long) loc)));
        }
        for(int i = 0; i< cards.size(); i++)
        {
            int random = 5 + (int) (Math.random()*30);
            cardPrices.add(random);
        }
        for(int i = 0; i< potions.size(); i++)
        {
            int random = 20 + (int) (Math.random()*30);
            potionPrices.add(random);
        }
        for(int i = 0; i< relics.size(); i++)
        {
            int random = 25 + (int) (Math.random()*30);
            relicPrices.add(random);
        }
    }

    public ArrayList<Integer> getCardPrices() {
        return cardPrices;
    }

    public ArrayList<Integer> getPotionPrices() {
        return potionPrices;
    }

    public ArrayList<Integer> getRelicPrices() {
        return relicPrices;
    }

    /**
     * removes the sold card object, and adds a new random card in place of it,
     * it checks that if the new card exists int he list already
     * @param location index of the sold card
     * @return returns the sold card object
     */
    public Card sellCard(int location)
    {
        Card result = cards.get(location);

        int start = (int) (Math.random()*allCards.size());
        boolean found = false;
        for(int i= start; i<allCards.size() && !found; i++)
        {
            if( ! inTheCurrentCards(allCards.get(i)))
            {
                cards.add(location,allCards.get(i));
                found = true;
            }
        }
        for(int i= start; i>=0 && !found; i--) {
            if (!inTheCurrentCards(allCards.get(i))) {
                cards.add(location,allCards.get(i));
                found = true;
            }
        }
        if(found)
        {
            cards.remove(location+1);
            int newPrice =  15 + (int) (Math.random()* 20);
            cardPrices.set(location,newPrice);
        }
        else
        {
            cards.remove(location);
        }

        return result;
    }

    /**
     * removes the sold relic object, and adds a new random relic in place of it,
     * it checks that if the new relic exists int he list already
     * @param location index of the sold relic
     * @return returns the sold relic object
     */
    public Relic sellRelic(int location)
    {
        Relic result = relics.get(location);
        int start = (int) (Math.random()*allRelics.size());
        boolean found = false;
        for(int i = start; i<allRelics.size() && !found; i++)
        {
            if(! inTheCurrentRelics(allRelics.get(i)))
            {
                relics.add(location,allRelics.get(i));
                found = true;
            }
        }
        for(int i = start; i>=0 && !found; i--)
        {
            if(! inTheCurrentRelics(allRelics.get(i)))
            {
                relics.add(location,allRelics.get(i));
                found = true;
            }
        }
        if(found) {
            relics.remove(location + 1);
            int newPrice =  20 + (int) (Math.random()* 25);
            relicPrices.set(location,newPrice);
        }
        else
        {
            relics.remove(location);
        }
        return result;
    }

    /**
     * removes the sold potion object, and adds a new random potion in place of it,
     * it checks that if the new potion exists in the list already
     * @param location index of the sold potion
     * @return returns the sold potion object
     */
    public Potion sellPotion(int location)
    {
        Potion result = potions.get(location);
        int start = (int) (Math.random()*allPotions.size());
        boolean found = false;
        for(int i= start; i<allPotions.size() && !found; i++)
        {
            if(!inTheCurrentPotions(allPotions.get(i)))
            {
                potions.add(location,allPotions.get(i));
                found = true;
            }
        }
        for(int i= start; i>=0&& !found; i--)
        {
            if(!inTheCurrentPotions(allPotions.get(i)))
            {
                potions.add(location,allPotions.get(i));
                found = true;
            }
        }
        if(found)
        {
            potions.remove(location+1);
            int newPrice =  25 + (int) (Math.random()* 30);
            potionPrices.set(location,newPrice);
        }
        else
        {
            potions.remove(location);
        }
        return result;
    }


    /**
     * @param check
     * @return returns if the given potion in the list of potions
     */
    private boolean inTheCurrentPotions(Potion check)
    {
        for(int i=0; i< potions.size(); i++)
        {
            if(check.getName().equals(potions.get(i).getName()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @param check
     * @return returns if the given relic in the list of relics
     */
    private boolean inTheCurrentRelics(Relic check)
    {
        for(int i=0; i< relics.size(); i++)
        {
            if(check.getName().equals(relics.get(i).getName()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @param check
     * @return returns if the given card in the list of cards
     */
    private boolean inTheCurrentCards(Card check)
    {
        for(int i=0; i< cards.size(); i++)
        {
            if(check.getName().equals(cards.get(i).getName()))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Card> getCards()
    {
        return cards;
    }
    public ArrayList<Potion> getPotions()
    {
        return potions;
    }
    public ArrayList<Relic> getRelics()
    {
        return relics;
    }

    public void setAllCards(ArrayList<Card> allCards) {
        this.allCards = allCards;
    }

    public void setAllPotions(ArrayList<Potion> allPotions) {
        this.allPotions = allPotions;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    public void setRelics(ArrayList<Relic> relics) {
        this.relics = relics;
    }

    @Override
    public String toString() {
        return "MerchantRoom{" +
                "cards=" + cards +
                ", potions=" + potions +
                ", relics=" + relics +
                ", json=" + json +
                '}';
    }
}
