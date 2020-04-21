package Model;
import java.util.ArrayList;

public class Character {
	private String name;
	private int hp;
	private int maxHp;
	private int gold;
	private String color;
	private Pet activePet;
	private Pile deck;
	private ArrayList<Relic> relics; 
	private ArrayList<Buff> buffs;
	private ArrayList<Pet> pets;
	private ArrayList<Potion> potions;
	
	public Character(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getMaxHp() {
		return maxHp;
	}
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Pet getActivePet() {
		return activePet;
	}
	public void setActivePet(Pet activePet) {
		this.activePet = activePet;
	}
	public Pile getDeck() {
		return deck;
	}
	public void setDeck(Pile deck) {
		this.deck = deck;
	}
	public ArrayList<Relic> getRelics() {
		return relics;
	}
	public void setRelics(ArrayList<Relic> relics) {
		this.relics = relics;
	}
	public ArrayList<Buff> getBuffs() {
		return buffs;
	}
	public void setBuffs(ArrayList<Buff> buffs) {
		this.buffs = buffs;
	}
	public ArrayList<Pet> getPets() {
		return pets;
	}
	public void setPets(ArrayList<Pet> pets) {
		this.pets = pets;
	}
	public ArrayList<Potion> getPotions() {
		return potions;
	}
	public void setPotions(ArrayList<Potion> potions) {
		this.potions = potions;
	}
	@Override
	public String toString() {
		return "Character [name=" + name + ", hp=" + hp + ", maxHp=" + maxHp + ", gold=" + gold + ", color=" + color
				+ ", activePet=" + activePet + ", deck=" + deck + ", relics=" + relics.toString() + ", buffs=" + buffs.toString() + ", pets="
				+ pets.toString() + ", potions=" + potions.toString() + "]";
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
	public void changeHp(int newHp) {
		this.hp = newHp;
	}
	public void changeMaxHp( int maxHp) {
		this.maxHp = maxHp;
	}
	public boolean discardPotion(Potion pot) {
		if (potions.contains(pot)) {
			potions.remove(pot);
			return true;
		}else
			return false;
	}
}

