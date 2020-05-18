package Model;
import Model.Buffs.Buff;
import Model.Buffs.BuffCollection;
import Model.Effects.Effect;

import java.util.ArrayList;
import java.util.Queue;

/**
 * The type Enemy.
 */
public class Enemy implements Fightable{
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
     * The Block.
     */
    private int block;
    /**
     * The Effects.
     */
    private Queue<ArrayList<Effect>> effects;
    /**
     * The Buffs.
     */
    private BuffCollection buffs;

    /**
     * Instantiates a new Enemy.
     *
     * @param name the name
     */
    public Enemy(String name) {
		this.buffs = new BuffCollection();
		this.name=name;
	}

    /**
     * Gets block.
     *
     * @return the block
     */
//getters
	public int getBlock() {
		return block;
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
     * Gets effects.
     *
     * @return the effects
     */
    public Queue<ArrayList<Effect>> getEffects() {
		return effects;
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
     * Get name string.
     *
     * @return the string
     */
    public String getName(){
		return name;
	}

    /**
     * Is dead boolean.
     *
     * @return the boolean
     */
    public boolean isDead()
	{
		if (hp <= 0 )
			return true;
		return false;
	}

    /**
     * Sets hp.
     *
     * @param hp the hp
     */
//setters
	public void setHp(int hp) {
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
		this.name = name;
	}

    /**
     * Sets effects.
     *
     * @param effects the effects
     */
    public void setEffects(Queue<ArrayList<Effect>> effects) {
		this.effects = effects;
	}

    /**
     * Sets buffs.
     *
     * @param newBuffs the new buffs
     */
    public void setBuffs(ArrayList<Buff> newBuffs) {
		buffs.setBuffs(newBuffs);
	}

    /**
     * Sets block.
     *
     * @param block the block
     */
    public void setBlock(int block) {
		this.block = block;
	}

    /**
     * Increase hp.
     *
     * @param amount the amount
     */
//HP mutators
	public void increaseHp(int amount){
		hp += amount;
	}

    /**
     * Decrease hp.
     *
     * @param amount the amount
     */
    public void decreaseHp(int amount){
		hp-=amount;
		if(hp < 0)hp = 0;
	}

    /**
     * Increase block.
     *
     * @param amount the amount
     */
//Block Mutators
	public void increaseBlock(int amount){
		block += amount;
	}

    /**
     * Decrease block.
     *
     * @param toDecrease the to decrease
     */
    public void decreaseBlock(int toDecrease) {
		if( toDecrease > block )block = 0;
		else  this.block-=toDecrease;
	}

    /**
     * Add buff.
     *
     * @param toAdd the to add
     */
    public void addBuff(Buff toAdd){
		this.buffs.addBuff(toAdd);
	}
	/*
	public void removeBuff(Buff toRemove) {
		this.buffs.removeBuff(toRemove);
	}
	*/

    /**
     * Decrease hp.
     *
     * @param decreaseHPAmount the decrease hp amount
     */
//hp mutator methods
	public void decreaseHP( int decreaseHPAmount ){
		if( decreaseHPAmount > hp )hp = 0;
		else hp -= decreaseHPAmount;
	}

    /**
     * To string string.
     *
     * @return the string
     */
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
