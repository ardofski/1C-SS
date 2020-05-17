package Model;
import Model.Effects.Effect;

import java.beans.BeanInfo;
import java.util.ArrayList;
import java.util.Queue;

public class Enemy implements Fightable{
    private String name;
	private int hp;
	private int maxHp;
	private int block;
	private Queue<ArrayList<Effect>> effects;
	private BuffCollection buffs;

	public Enemy(String name) {
		this.buffs = new BuffCollection();
		this.name=name;
	}
	//getters
	public int getBlock() {
		return block;
	}
	public int getHp() {
		return hp;
	}
	public int getMaxHp() {
		return maxHp;
	}
	public Queue<ArrayList<Effect>> getEffects() {
		return effects;
	}
	public BuffCollection getBuffs() {
		return buffs;
	}
	public String getName(){
		return name;
	}
	public boolean isDead()
	{
		if (hp <= 0 )
			return true;
		return false;
	}

	//setters
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEffects(Queue<ArrayList<Effect>> effects) {
		this.effects = effects;
	}
	public void setBuffs(ArrayList<Buff> newBuffs) {
		buffs.setBuffs(newBuffs);
	}
	public void setBlock(int block) {
		this.block = block;
	}

	//HP mutators
	public void increaseHp(int amount){
		hp += amount;
	}
	public void decreaseHp(int amount){
		hp-=amount;
		if(hp < 0)hp = 0;
	}

	//Block Mutators
	public void increaseBlock(int amount){
		block += amount;
	}
	public void decreaseBlock(int toDecrease) {
		if( toDecrease > block )block = 0;
		else  this.block-=toDecrease;
	}

	public void addBuff(Buff toAdd){
		this.buffs.addBuff(toAdd);
	}
	/*
	public void removeBuff(Buff toRemove) {
		this.buffs.removeBuff(toRemove);
	}
	*/

	//hp mutator methods
	public void decreaseHP( int decreaseHPAmount ){
		if( decreaseHPAmount > hp )hp = 0;
		else hp -= decreaseHPAmount;
	}

	@Override
	public String toString() {
		return "Enemy{" +
				"name='" + name + '\'' +
				", hp=" + hp +
				", maxHp=" + maxHp +
				", block=" + block +
				", effects=" + effects +
				", buffs=" + buffs +
				'}';
	}
}
