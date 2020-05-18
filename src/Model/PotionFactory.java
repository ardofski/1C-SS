package Model;

import Model.Buffs.*;
import Model.Effects.*;
import java.util.ArrayList;

/**
 * The type Potion factory.
 */
public class PotionFactory {
    /**
     * The All potions.
     */
    ArrayList<Potion> allPotions;

    /**
     * Instantiates a new Potion factory.
     */
    public PotionFactory(){
        allPotions = new ArrayList<>();

        //targetless potions
        Potion blockPotion = new Potion("Block Potion", "Gain 12 Block.",10, new Block(12, null));
        Potion ancientPotion = new Potion("Ancient Potion", "Gain 1 Artifact", 3, new ApplyBuff(new Artifact(1),null));
        Potion bloodPotion = new Potion("Blood Potion", "Heal 10 hp points", 7, new Heal(10));
        Potion cultistPotion = new Potion("Cultist Potion","Gain 1 Ritual", 3, new ApplyBuff(new Ritual(3), null));
        Potion dexterityPotion = new Potion("Dexterity Potion","Gain 2 Dexterity", 5, new ApplyBuff(new Dexterity(2), null));
        Potion energyPotion = new Potion("Energy Potion", "Gain 2 Energy", 10, new ChangeEnergy(2));
        Potion essenceOfSteelPotion = new Potion("Essence Of Steel Potion","Gain 4 Plated Armor", 8, new ApplyBuff(new PlatedArmor(4),null));
        Potion strengthPotion = new Potion("Strength Potion","Gain 2 Strength", 6, new ApplyBuff(new Strength(2),null));

        //potions with target enemy
        Potion fearPotion = new Potion("Fear Potion", "Give 3 Vulnerable to the selected enemy", 5, new ApplyBuff(new Vulnerable(3),null));
        fearPotion.setHasTarget(true);
        Potion firePotion = new Potion("Fire Potion", "Deal 20 damage to the selected enemy",10, new Damage(20,null,null));
        firePotion.setHasTarget(true);

        //add all potions to allPotions list
        allPotions.add(blockPotion);
        allPotions.add(ancientPotion);
        allPotions.add(bloodPotion);
        allPotions.add(cultistPotion);
        allPotions.add(dexterityPotion);
        allPotions.add(energyPotion);
        allPotions.add(essenceOfSteelPotion);
        allPotions.add(strengthPotion);
        allPotions.add(fearPotion);
        allPotions.add(firePotion);
    }

    /**
     * Get random potion potion.
     *
     * @return the potion
     */
    public Potion getRandomPotion(){
        int index = (int) (Math.random() * allPotions.size());
        return allPotions.get(index);
    }

    /**
     * Gets all potions.
     *
     * @return the all potions
     */
    public ArrayList<Potion> getAllPotions() {
        return allPotions;
    }

    /**
     * Get potion potion.
     *
     * @param name the name
     * @return the potion
     */
    public Potion getPotion(String name){
        for(Potion potion: allPotions){
            if(potion.getName().equals(name)) return potion;
        }
        return null;
    }

    /**
     * Get potions array list.
     *
     * @param names the names
     * @return the array list
     */
    public ArrayList<Potion> getPotions(ArrayList<String> names){
        ArrayList<Potion> result = new ArrayList<>();
        for(String name: names){
            result.add(getPotion(name));
        }
        return result;
    }

}
