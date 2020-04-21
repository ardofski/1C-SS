package Model;
import java.util.ArrayList;

public class Enemy {
    String name;
	private ArrayList<Buff> buffs;


    public String getName(){
        return name;
    }

	public Enemy(ArrayList<Buff> buffs) {
		this.buffs = buffs;
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
