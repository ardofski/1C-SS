package Model;
import java.util.ArrayList;

public class Enemy {
    private String name;
	private int hp;
	private int maxHp;
	private int block;
	private ArrayList<Buff> buffs;
	public int getBlock() {
		return block;
	}
	public void setBlock(int block) {
		this.block = block;
	}
	public void decreaseBlock(int toDecrease) {
		this.block-=toDecrease;
	}
	public void increaseBlock(int toIncrease) {
		this.block+=toIncrease;
	}

	public void addBuff(Buff toAdd){
		this.buffs.add(toAdd);
	}
	public void removeBuff(Buff toRemove) {
		this.buffs.remove(toRemove);
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

	public void setName(String name) {
		this.name = name;
	}

    public String getName(){
        return name;
    }

	public Enemy(String name) {
		this.buffs = new ArrayList<Buff>();
		this.name=name;
	}

	public ArrayList<Buff> getBuffs() {
		return buffs;
	}

	public void setBuffs(ArrayList<Buff> buffs) {
		this.buffs = buffs;
	}
	
	@Override
	public String toString() {
		String buffsStr="";
		for(Buff buff: this.buffs) {
			buffsStr +=buff.toString() + " ";
		}
		return "Enemy [buffs=" + buffsStr + "]";
	}
	
}
