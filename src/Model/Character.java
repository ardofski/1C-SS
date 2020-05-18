package Model;
import Model.Relics.Relic;

import java.util.ArrayList;


/**
 * The type Character.
 */
public class Character implements Fightable{

    /**
     * The constant MAX_POT.
     */
    private final static int MAX_POT = 3;

    /**
     * The Name.
     */
    private String name;
    /**
     * The Hp.
     */
    private int hp;
    /**
     * The Max hp.
     */
    private int maxHp;
    /**
     * The Gold.
     */
    private int gold;
    /**
     * The Energy.
     */
    private int energy;
    /**
     * The Block.
     */
    private int block;
    /**
     * The Color.
     */
    private String color;
    /**
     * The Active pet.
     */
    private Pet activePet;
    /**
     * The Deck.
     */
    private Pile deck;
    /**
     * The Relics.
     */
    private ArrayList<Relic> relics;
    /**
     * The Buffs.
     */
    private BuffCollection buffs;
    /**
     * The Pets.
     */
    private ArrayList<Pet> pets;
    /**
     * The Potions.
     */
    private ArrayList<Potion> potions;


    /**
     * Instantiates a new Character.
     */
    public Character (){
		this.buffs = new BuffCollection();
	}

    /**
     * Instantiates a new Character.
     *
     * @param name the name
     */
    public Character(String name) {
		this.name = name;
		this.buffs = new BuffCollection();
		this.pets = new ArrayList<Pet>();
		this.potions = new ArrayList<Potion>();
		activePet = null;
		gold = 99;
	}

    /**
     * Gets name.
     *
     * @return the name
     */
//getters
	public String getName() {
		return name;
	}

    /**
     * Gets hp.
     *
     * @return the hp
     */
    public int getHp() {
		return hp;
	}

    /**
     * Gets max hp.
     *
     * @return the max hp
     */
    public int getMaxHp() {
		return maxHp;
	}

    /**
     * Gets gold.
     *
     * @return the gold
     */
    public int getGold() {
		return gold;
	}

    /**
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
		return color;
	}

    /**
     * Gets active pet.
     *
     * @return the active pet
     */
    public Pet getActivePet() {
		return activePet;
	}

    /**
     * Gets deck.
     *
     * @return the deck
     */
    public Pile getDeck() {
		return deck;
	}

    /**
     * Gets relics.
     *
     * @return the relics
     */
    public ArrayList<Relic> getRelics() {
		return relics;
	}

    /**
     * Gets buffs.
     *
     * @return the buffs
     */
    public BuffCollection getBuffs() {
		return buffs;
	}

    /**
     * Gets pets.
     *
     * @return the pets
     */
    public ArrayList<Pet> getPets() {
		return pets;
	}

    /**
     * Gets potions.
     *
     * @return the potions
     */
    public ArrayList<Potion> getPotions() {
		return potions;
	}

    /**
     * Get block int.
     *
     * @return the int
     */
    public int getBlock(){return block;}

    /**
     * Get energy int.
     *
     * @return the int
     */
    public int getEnergy(){return energy;}

	//mutators

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
		this.name = name;
	}


    /**
     * Sets hp.
     *
     * @param hp the hp
     */
    public void setHp(int hp) {
		//if coming hp is greater than the max hp set it to maxHP
		if(hp >= maxHp)
			this.hp = maxHp;
		else
			this.hp = hp;
	}

    /**
     * Sets max hp.
     *
     * @param maxHp the max hp
     */
    public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

    /**
     * Sets gold.
     *
     * @param gold the gold
     */
    public void setGold(int gold) {
		this.gold = gold;
	}

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(String color) {
		this.color = color;
	}

    /**
     * Sets active pet.
     *
     * @param activePet the active pet
     */
    public void setActivePet(Pet activePet) {
		this.activePet = activePet;
	}

    /**
     * Sets deck.
     *
     * @param deck the deck
     */
    public void setDeck(Pile deck) {
		this.deck = deck;
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
     * Sets buffs.
     *
     * @param newBuffs the new buffs
     */
    public void setBuffs(ArrayList<Buff> newBuffs) {
		buffs.setBuffs( newBuffs );
	}

    /**
     * Sets pets.
     *
     * @param pets the pets
     */
    public void setPets(ArrayList<Pet> pets) {
		this.pets = pets;
	}

    /**
     * Has potion boolean.
     *
     * @param potion the potion
     * @return the boolean
     */
//potion methods
	public boolean hasPotion(Potion potion){
		for(int i = 0 ; i < potions.size() ; i++ ){
			if( potions.get(i) == potion )return true;
		}
		return false;
	}

    /**
     * Add potion boolean.
     *
     * @param p the p
     * @return the boolean
     */
    public boolean addPotion(Potion p){
		//maximum potion size is 3, dont add more than max potion sie
		if( potions.size() >= MAX_POT )return false;
		potions.add(p);
		return true;
	}

    /**
     * Add relic boolean.
     *
     * @param r the r
     * @return the boolean
     */
    public boolean addRelic(Relic r){
		//dont add a relic that the character already has
		for(Relic relic: relics){
			if(relic.getName().equals(r.getName()))
				return false;
		}
		relics.add(r);
		return true;
	}

    /**
     * Remove potion boolean.
     *
     * @param p the p
     * @return the boolean
     */
    public boolean removePotion(Potion p){
		for( int i = 0 ; i < potions.size() ;i++){
			if( potions.get(i) == p) potions.remove(i);
			return true;
		}
		return false;
	}

    /**
     * Sets potions.
     *
     * @param potions the potions
     * @return the potions
     */
    public boolean setPotions(ArrayList<Potion> potions) {
		if( potions.size() > MAX_POT )return false;
		this.potions = potions;
		return true;
	}

    /**
     * Increase hp.
     *
     * @param heal the heal
     */
//hp Mutators
	public void increaseHp(int heal){
		//increase hp, if the result is greater than the max hp
		//set it to max hp
		if( heal < 0 ){decreaseHp(-heal);return;}
		if( heal + hp > maxHp ){
			this.hp = maxHp;
			return;
		}
		this.hp+=heal;
	}

    /**
     * Decrease hp.
     *
     * @param decreaseHPAmount the decrease hp amount
     */
    public void decreaseHp(int decreaseHPAmount){
		//derease hp, if result is less than 0,
		//set it to zero
		if(decreaseHPAmount < 0){
			increaseHp(-decreaseHPAmount);
			return;
		}

		if( decreaseHPAmount > hp )hp = 0;
		else hp -= decreaseHPAmount;
	}

    /**
     * Remove block.
     */
//Block Mutators
	public void removeBlock(){
		block = 0;
	}

    /**
     * Increase block.
     *
     * @param addBlockAmount the add block amount
     */
    public void increaseBlock(int addBlockAmount){
		block += addBlockAmount;
	}

    /**
     * Decrease block.
     *
     * @param decreaseBlockAmount the decrease block amount
     */
    public void decreaseBlock( int decreaseBlockAmount ){
		if( decreaseBlockAmount > block ) block = 0;
		else block -= decreaseBlockAmount;
	}

    /**
     * Add buff.
     *
     * @param b the b
     */
//Buff mutators
	public void addBuff(Buff b){
		buffs.addBuff(b);
	}

    /**
     * Clear buffs.
     */
    public void clearBuffs(){
		buffs.clearAllBuffs();
	}


    /**
     * Increase energy.
     *
     * @param i the
     */
    public void increaseEnergy( int i ){
		energy += i;
	}

    /**
     * Fill energy.
     */
    public void fillEnergy(){
		energy = 3;
	}



	//mutator methods of hp

    /**
     * To string string.
     *
     * @return the string
     */
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

    /**
     * Increase gold.
     *
     * @param amount the amount
     */
    public void increaseGold(int amount) {
		this.gold +=amount;
	}

    /**
     * Decrease gold.
     *
     * @param amount the amount
     */
    public void decreaseGold(int amount) {
		this.gold -=amount;
	}

    /**
     * Change active pet.
     *
     * @param active the active
     */
    public void changeActivePet(Pet active) {
		this.activePet = active;
	}

    /**
     * Discard potion boolean.
     *
     * @param pot the pot
     * @return the boolean
     */
    public boolean discardPotion(Potion pot) {
		if (potions.contains(pot)) {
			potions.remove(pot);
			return true;
		}else
			return false;
	}

    /**
     * Delete card boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean deleteCard(String name){
		return deck.delete(name);
	}


    /**
     * Get relic names array list.
     *
     * @return the array list
     */
    public ArrayList<String> getRelicNames(){
        //karakterin sahip oldugu reliclarin isimlerinin listesini donucek
    	ArrayList<String> toReturn = new ArrayList<String>();
    	for(Relic relic: this.relics)
    		toReturn.add(relic.getName());
        return toReturn;
    }

    /**
     * Get potion names array list.
     *
     * @return the array list
     */
    public ArrayList<String> getPotionNames(){
        //karakterin sahip oldugu potionlarin isimlerinin listesini donucek
    	ArrayList<String> toReturn = new ArrayList<String>();
    	for(Potion potion: this.potions)
    		toReturn.add(potion.getName());
        return toReturn;
    }

    /**
     * Get card names array list.
     *
     * @return the array list
     */
    public ArrayList<String> getCardNames(){
        //karakterin sahip oldugu kartlarin isimlerinin listesini donucek
    	ArrayList<String> toReturn = new ArrayList<String>();
    	for(Card card: this.deck.getCards())
    		toReturn.add(card.getName());
        return toReturn;
    }

    /**
     * Get pet names array list.
     *
     * @return the array list
     */
    public ArrayList<String> getPetNames(){
        //karakterin sahip oldugu petlerin isimlerinin listesini donucek
    	ArrayList<String> toReturn = new ArrayList<String>();
    	for(Pet pet: this.pets)
    		toReturn.add(pet.getName());
        return toReturn;
    }

    /**
     * Set active pet.
     *
     * @param name the name
     */
    public void setActivePet(String name){
		//TODO
	}

	
}

