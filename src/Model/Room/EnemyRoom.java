package Model.Room;

import Model.*;
import Model.Buffs.*;
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

public class EnemyRoom extends Room
{
    private String type;
    private ArrayList<Enemy> enemies;
    private JSONObject json;
    private ArrayList<Relic> allRelics;
    private ArrayList<Card> allCards;
    private ArrayList<Potion> allPotions;
    ArrayList<Enemy> allEnemies;
    public EnemyRoom(int act)
    {
        this.act = act;
        enemies = new ArrayList<Enemy>();
    }
    public void set(JSONObject json,ArrayList<Enemy> allEnemies)
    {
        this.json = json;
        this.allEnemies = allEnemies;
        this.type = (String) json.get("type");
    }


    /**
     * initializes the room
     * gets the enemies, relic rewards and card rewards from database
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
    public String getType() {
        return type;
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }


    /**
     * @return returns the rewards of the room
     * a reward objects contains relics, cards, potions and gold awards.
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

    @Override
    public String toString() {
        return "EnemyRoom{" +
                "type='" + type + '\'' +
                ", enemies=" + enemies +
                ", json=" + json +
                '}';
    }
}
