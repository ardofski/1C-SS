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
    }

    public void set(JSONObject json, ArrayList<Card> allCards, ArrayList<Potion> allPotions, ArrayList<Relic> allRelics)
    {
        this.json = json;
        this.allCards = allCards;
        this.allPotions = allPotions;
        this.allRelics = allRelics;
    }
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
            //potions.add(allPotions.get(Math.toIntExact((Long) loc)));
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

    public Card sellCard(int location)
    {
        Card result = cards.get(location);
        int start = (int) (Math.random()*allCards.size());
        boolean found = false;
        for(int i= start; i<allCards.size() && !found; i++)
        {
            if( ! inTheCurrentCards(allCards.get(i)))
            {
                cards.add(allCards.get(i));
                found = true;
            }
        }
        for(int i= start; i>=0 && !found; i--) {
            if (!inTheCurrentCards(allCards.get(i))) {
                cards.add(allCards.get(i));
                found = true;
            }
        }
        cards.remove(location);
        return result;
    }
    public Relic sellRelic(int location)
    {
        Relic result = relics.get(location);
        int start = (int) (Math.random()*allRelics.size());
        boolean found = false;
        for(int i = start; i<allRelics.size(); i++)
        {
            if(! inTheCurrentRelics(allRelics.get(i)))
            {
                relics.add(allRelics.get(i));
                found = true;
            }
        }
        for(int i = start; i>=0; i--)
        {
            if(! inTheCurrentRelics(allRelics.get(i)))
            {
                relics.add(allRelics.get(i));
                found = true;
            }
        }
        relics.remove(location);
        return result;
    }
    public Potion sellPotion(int location)
    {
        Potion result = potions.get(location);
        int start = (int) (Math.random()*allPotions.size());
        boolean found = false;
        for(int i= start; i<allPotions.size(); i++)
        {
            if(!inTheCurrentPotions(allPotions.get(i)))
            {
                potions.add(allPotions.get(i));
                found = true;
            }
        }
        for(int i= start; i>=0; i--)
        {
            if(!inTheCurrentPotions(allPotions.get(i)))
            {
                potions.add(allPotions.get(i));
                found = true;
            }
        }
        potions.remove(location);
        return result;
    }
    private boolean inTheCurrentPotions(Potion check)
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
