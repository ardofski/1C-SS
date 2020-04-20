package Model;

import java.util.ArrayList;

public class Character {
    //instances
    String name, color;
    ArrayList<Relic> relics;
    ArrayList<Potion> potions;
    int hp, maxHP, gold;
    Pile deck;
    ArrayList<Pet> pets;
    Pet activePet;
    ArrayList<Buff> buffs;

    public Character(){}

    public String getName(){
        return name;
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }

    public ArrayList<Relic> getRelics() {
        return relics;
    }

    public int getGold() {
        return gold;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public Pile getDeck() {
        return deck;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public Pet getActivePet() {
        return activePet;
    }

    public String getColor() {
        return color;
    }

    public ArrayList<String> getRelicNames(){
        //karakterin sahip oldugu reliclarin isimlerinin listesini donucek
        return null;
    }

    public ArrayList<String> getPotionNames(){
        //karakterin sahip oldugu potionlarin isimlerinin listesini donucek
        return null;
    }

    public ArrayList<String> getCardNames(){
        //karakterin sahip oldugu kartlarin isimlerinin listesini donucek
        return null;
    }
    public ArrayList<String> getPetNames(){
        //karakterin sahip oldugu petlerin isimlerinin listesini donucek
        return null;
    }
}
