package Model.Room;

import Model.*;
import Model.Cards.Card;
import Model.Relics.Relic;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * The type Merchant room.
 */
public class MerchantRoom extends Room
{
    /**
     * The Cards.
     */
    private ArrayList<Card> cards;
    /**
     * The Potions.
     */
    private ArrayList<Potion> potions;
    /**
     * The Relics.
     */
    private ArrayList<Relic> relics;

    /**
     * The All cards.
     */
    private ArrayList<Card> allCards;
    /**
     * The All potions.
     */
    private ArrayList<Potion> allPotions;
    /**
     * The All relics.
     */
    private ArrayList<Relic> allRelics;
    /**
     * The Card prices.
     */
    private ArrayList<Integer> cardPrices;
    /**
     * The Relic prices.
     */
    private ArrayList<Integer> relicPrices;
    /**
     * The Potion prices.
     */
    private ArrayList<Integer> potionPrices;
    /**
     * The Json.
     */
    private JSONObject json;

    /**
     * Instantiates a new Merchant room.
     *
     * @param act the act
     */
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

    /**
     * Set.
     *
     * @param json       the json
     * @param allCards   the all cards
     * @param allPotions the all potions
     * @param allRelics  the all relics
     */
    public void set(JSONObject json, ArrayList<Card> allCards, ArrayList<Potion> allPotions, ArrayList<Relic> allRelics)
    {
        this.json = json;
        this.allCards = allCards;
        this.allPotions = allPotions;
        this.allRelics = allRelics;
    }


    /**
     * Initialize.
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

    /**
     * Gets card prices.
     *
     * @return the card prices
     */
    public ArrayList<Integer> getCardPrices() {
        return cardPrices;
    }

    /**
     * Gets potion prices.
     *
     * @return the potion prices
     */
    public ArrayList<Integer> getPotionPrices() {
        return potionPrices;
    }

    /**
     * Gets relic prices.
     *
     * @return the relic prices
     */
    public ArrayList<Integer> getRelicPrices() {
        return relicPrices;
    }

    /**
     * Sell card card.
     *
     * @param location the location
     * @return the card
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
     * Sell relic relic.
     *
     * @param location the location
     * @return the relic
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
     * Sell potion potion.
     *
     * @param location the location
     * @return the potion
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
     * In the current potions boolean.
     *
     * @param check the check
     * @return the boolean
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
     * In the current relics boolean.
     *
     * @param check the check
     * @return the boolean
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
     * In the current cards boolean.
     *
     * @param check the check
     * @return the boolean
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

    /**
     * Gets cards.
     *
     * @return the cards
     */
    public ArrayList<Card> getCards()
    {
        return cards;
    }

    /**
     * Gets potions.
     *
     * @return the potions
     */
    public ArrayList<Potion> getPotions()
    {
        return potions;
    }

    /**
     * Gets relics.
     *
     * @return the relics
     */
    public ArrayList<Relic> getRelics()
    {
        return relics;
    }

    /**
     * Sets all cards.
     *
     * @param allCards the all cards
     */
    public void setAllCards(ArrayList<Card> allCards) {
        this.allCards = allCards;
    }

    /**
     * Sets all potions.
     *
     * @param allPotions the all potions
     */
    public void setAllPotions(ArrayList<Potion> allPotions) {
        this.allPotions = allPotions;
    }

    /**
     * Sets json.
     *
     * @param json the json
     */
    public void setJson(JSONObject json) {
        this.json = json;
    }

    /**
     * Sets relics.
     *
     * @param relics the relics
     */
    public void setRelics(ArrayList<Relic> relics) {
        this.relics = relics;
    }

    /**
     * To string string.
     *
     * @return the string
     */
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
