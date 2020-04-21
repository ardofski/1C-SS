package Model;
import java.util.ArrayList;

public class Enemy {
	private ArrayList<Buff> buffs;
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
		return "Enemy [buffs=" + buffs + "]";
	}
	
}
