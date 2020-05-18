package Model.Room;

import Model.*;
import Model.Buffs.*;
import Model.Cards.Card;
import Model.Cards.CardFactory;
import Model.Effects.ApplyBuff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Relics.Relic;
import Model.Relics.RelicFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The type Enemy room.
 */
public class EnemyRoom extends Room
{
    /**
     * The Type.
     */
    private String type;
    /**
     * The Enemies.
     */
    private ArrayList<Enemy> enemies;
    /**
     * The Json.
     */
    private JSONObject json;
    /**
     * The All relics.
     */
    private ArrayList<Relic> allRelics;
    /**
     * The All cards.
     */
    private ArrayList<Card> allCards;
    /**
     * The All potions.
     */
    private ArrayList<Potion> allPotions;
    /**
     * The All enemies.
     */
    ArrayList<Enemy> allEnemies;

    /**
     * Instantiates a new Enemy room.
     *
     * @param act the act
     */
    public EnemyRoom(int act)
    {
        this.act = act;
        enemies = new ArrayList<Enemy>();
    }

    /**
     * Set.
     *
     * @param json       the json
     * @param allEnemies the all enemies
     */
    public void set(JSONObject json,ArrayList<Enemy> allEnemies)
    {
        this.json = json;
        this.allEnemies = allEnemies;
        this.type = (String) json.get("type");
    }


    /**
     * Initialize.
     */
    public void initialize()
    {
        enemies = new ArrayList<Enemy>();
        allRelics = RelicFactory.getAllRelics();
        allCards = CardFactory.getAllCards();
        //allPotions = PotionFactory.getAllPotions(); todo
        
       // Math.toIntExact((Long) loc
        JSONArray enemyArr = (JSONArray) json.get("enemyList");
        //initialize the enemyroom object from database
        for (Object loc : enemyArr)
        {
            Enemy copy = allEnemies.get( Math.toIntExact((Long) loc));
            Enemy toAdd = new Enemy(copy.getName());  // buff olayi problematic olabilir.
            toAdd.setBuffs(copy.getBuffs().getBuffs());
            toAdd.setMaxHp(copy.getMaxHp());
            //deep effect copy
            Queue<ArrayList<Effect>> addEffects = new LinkedList<>();

            for(int i=0; i<copy.getEffects().size(); i++)
            {
               ArrayList<Effect> effects =  copy.getEffects().remove();
               ArrayList<Effect> newEffects = new ArrayList<Effect>();
               addEffects.add(newEffects);
               for(int k=0; k<effects.size(); k++)
               {
                   if(effects.get(k) instanceof Damage)
                   {
                       Damage damage = new Damage(((Damage)effects.get(k)).getDamage(), null,toAdd);
                       newEffects.add(damage);
                   }
                   if(effects.get(k) instanceof Block)
                   {
                       Block block = new Block(((Block)effects.get(k)).getBlock(),toAdd);
                       newEffects.add(block);
                   }
                   if(effects.get(k) instanceof ApplyBuff)
                   {
                       Buff buff = ((ApplyBuff)effects.get(k)).getBuff();

                       if(buff instanceof Vulnerable || buff instanceof Weak || buff instanceof Artifact || buff instanceof Thorns)
                       {
                           ApplyBuff applyBuff = new ApplyBuff(buff,null);
                           newEffects.add(applyBuff);
                       }
                       else
                       {
                           ApplyBuff applyBuff = new ApplyBuff(buff,toAdd);
                           newEffects.add(applyBuff);
                       }
                   }
               }
               copy.getEffects().add(effects);

            }
            toAdd.setEffects(addEffects);
            toAdd.setHp(copy.getMaxHp());//basta full hp
            System.out.println("IN ENEMY ROOM- ENEMY HP : "+ toAdd.getHp());
            enemies.add(toAdd);
        }
        System.out.println("IN ENEMY ROOM- ENEMY SIZE : "+ enemies.size() );

    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets enemies.
     *
     * @return the enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }


    /**
     * Give reward reward.
     *
     * @return the reward
     */
    public Reward giveReward()
    {
        Reward reward = new Reward();

        //add random potion
        PotionFactory potionFactory = new PotionFactory();
        reward.setPot( potionFactory.getRandomPotion() );

        //add relic randomly
        double randRelic = Math.random();
        if(randRelic < 0.3)
        {
            int location = (int)(Math.random()* allRelics.size());
            reward.setRelic(allRelics.get(location));
        }
        //add random amount of gold 10-50
        int amount = 10 + (int)(Math.random()*40);
        reward.setGold(amount);

        //put 3 cards randomly
        ArrayList<Integer> used = new ArrayList<Integer>();
        while(reward.getCards().size() < 3)
        {
            int loc = (int) (allCards.size()*Math.random());
            boolean found = false;
            for(int k=0; k< used.size(); k++)
            {
                if(used.get(k) == loc)
                    found=true;
            }
            if(!found)
            {
                reward.getCards().add(allCards.get(loc));
                used.add(loc);
            }

        }


        return reward;
    }

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "EnemyRoom{" +
                "type='" + type + '\'' +
                ", enemies=" + enemies +
                ", json=" + json +
                '}';
    }
}
