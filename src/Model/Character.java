package Model;
import Model.Relics.Relic;

import java.util.ArrayList;

public class Character implements Fightable{
    private String name;
	private int hp;
	private int maxHp;
	private int gold;
	private int energy;
	private int block;
	private String color;
	private Pet activePet;
	private Pile deck;
	private ArrayList<Relic> relics; 
	private BuffCollection buffs;
	private ArrayList<Pet> pets;
	private ArrayList<Potion> potions;

	public Character (){	}

	public Character(String name) {
		this.name = name;
		this.buffs = new BuffCollection();
		this.pets = new ArrayList<Pet>();
		this.potions = new ArrayList<Potion>();
		activePet = null;
		gold = 99;
	}
	//getters
	public String getName() {
		return name;
	}
	public int getHp() {
		return hp;
	}
	public int getMaxHp() {
		return maxHp;
	}
	public int getGold() {
		return gold;
	}
	public String getColor() {
		return color;
	}
	public Pet getActivePet() {
		return activePet;
	}
	public Pile getDeck() {
		return deck;
	}
	public ArrayList<Relic> getRelics() {
		return relics;
	}
	public BuffCollection getBuffs() {
		return buffs;
	}
	public ArrayList<Pet> getPets() {
		return pets;
	}
	public ArrayList<Potion> getPotions() {
		return potions;
	}
	public int getBlock(){return block;}
	public int getEnergy(){return energy;}

	//mutators

	public void setName(String name) {
		this.name = name;
	}
	

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setActivePet(Pet activePet) {
		this.activePet = activePet;
	}

	public void setDeck(Pile deck) {
		this.deck = deck;
	}

	public void setRelics(ArrayList<Relic> relics) {
		this.relics = relics;
	}

	public void setBuffs(ArrayList<Buff> newBuffs) {
		buffs.setBuffs( newBuffs );
	}
	public void setPets(ArrayList<Pet> pets) {
		this.pets = pets;
	}
	public void setPotions(ArrayList<Potion> potions) {
		this.potions = potions;
	}

	//hp Mutators
	public void increaseHp(int heal){
		this.hp+=heal;
	}
	public void decreaseHp(int decreaseHPAmount){
		if( decreaseHPAmount > hp )hp = 0;
		else hp -= decreaseHPAmount;
	}

	//Block Mutators
	public void removeBlock(){
		block = 0;
	}
	public void increaseBlock(int addBlockAmount){
		block += addBlockAmount;
	}
	public void decreaseBlock( int decreaseBlockAmount ){
		if( decreaseBlockAmount > block ) block = 0;
		else block -= decreaseBlockAmount;
	}

	//Buff mutators
	public void addBuff(Buff b){
		buffs.addBuff(b);
	}


	public void increaseEnergy( int i ){
		energy += i;
	}

	public void fillEnergy(){
		energy = 3;
	}



	//mutator methods of hp



	@Override
	public String toString() {
		String relicsStr ="";
		if( relics != null ){
			for(Relic relic: this.relics) {
				relicsStr += relic.toString()+ " ";
			}
		}

		String buffsStr="";
		for(Buff buff: this.buffs.getBuffs()) {
			buffsStr +=buff.toString() + " ";
		}
		String petsStr="";
		for(Pet pet: this.pets) {
			petsStr += pet.toString()+" ";
		}
		String potionsStr="";
		for(Potion potion: this.potions){
			potionsStr +=this.potions.toString()+" ";
		}
		return "Character [name=" + name + ", hp=" + hp + ", maxHp=" + maxHp + ", gold=" + gold + ", color=" + color
				+ ", activePet=" + activePet + ", deck=" + deck + ", relics=" + relicsStr + ", buffs=" + buffsStr + ", pets="
				+ petsStr + ", potions=" + potionsStr + "]";
	}
	
	public void increaseGold(int amount) {
		this.gold +=amount;
	}
	public void decreaseGold(int amount) {
		this.gold -=amount;
	}
	public void changeActivePet(Pet active) {
		this.activePet = active;
	}
	public boolean discardPotion(Potion pot) {
		if (potions.contains(pot)) {
			potions.remove(pot);
			return true;
		}else
			return false;
	}
	public boolean deleteCard(String name){
		return deck.delete(name);
	}


    public ArrayList<String> getRelicNames(){
        //karakterin sahip oldugu reliclarin isimlerinin listesini donucek
    	ArrayList<String> toReturn = new ArrayList<String>();
    	for(Relic relic: this.relics)
    		toReturn.add(relic.getName());
        return toReturn;
    }

    public ArrayList<String> getPotionNames(){
        //karakterin sahip oldugu potionlarin isimlerinin listesini donucek
    	ArrayList<String> toReturn = new ArrayList<String>();
    	for(Potion potion: this.potions)
    		toReturn.add(potion.getName());
        return toReturn;
    }

    public ArrayList<String> getCardNames(){
        //karakterin sahip oldugu kartlarin isimlerinin listesini donucek
    	ArrayList<String> toReturn = new ArrayList<String>();
    	for(Card card: this.deck.getCards())
    		toReturn.add(card.getName());
        return toReturn;
    }
    public ArrayList<String> getPetNames(){
        //karakterin sahip oldugu petlerin isimlerinin listesini donucek
    	ArrayList<String> toReturn = new ArrayList<String>();
    	for(Pet pet: this.pets)
    		toReturn.add(pet.getName());
        return toReturn;
    }

    public void setActivePet(String name){
		//TODO
	}

	
}

