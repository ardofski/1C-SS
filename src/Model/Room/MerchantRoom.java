package Model.Room;

import Model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class MerchantRoom extends Room
{
    private ArrayList<Card> cards;
    private ArrayList<Potion> potions;
    private ArrayList<Relic> relics;

    public MerchantRoom(int act)
    {
        this.act = act;
        cards = new ArrayList<Card>();
        potions = new ArrayList<Potion>();
        relics = new ArrayList<Relic>();
    }

    public void initialize(JSONObject json, ArrayList<Card> allCards, ArrayList<Potion> allPotions, ArrayList<Relic> allRelics)
    {
        JSONArray cardArr = (JSONArray) json.get("cardList");
        JSONArray potionArr = (JSONArray) json.get("potionList");
        JSONArray relicArr = (JSONArray) json.get("cardList");
        for(Object loc: cardArr)
        {
            cards.add(allCards.get(Math.toIntExact((Long) loc));
        }
        for(Object loc: potionArr)
        {
            potions.add(allPotions.get(Math.toIntExact((Long) loc));
        }
        for(Object loc: relicArr)
        {
            relics.add(allRelics.get(Math.toIntExact((Long) loc));
        }
    }

    public Card sellCard(int location,ArrayList<Card> allCards)
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
            if (!inTheCurrentCards(Card allCards.get(i))) {
                cards.add(allCards.get(i));
                found = true;
            }
        }
        cards.remove(location);
        return result;
    }
    public Relic sellRelic(int location, ArrayList<Relic> allRelics)
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
    public Potion sellPotion(int location, ArrayList<Potion> allPotions)
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

}
