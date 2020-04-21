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

    public void setRelics(ArrayList<Relic> relics) {
        this.relics = relics;
    }

    public void setPotions(ArrayList<Potion> potions) {
        this.potions = potions;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setDeck(Pile deck) {
        this.deck = deck;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    public void setActivePet(Pet activePet) {
        this.activePet = activePet;
    }

    public void setBuffs(ArrayList<Buff> buffs) {
        this.buffs = buffs;
    }

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
