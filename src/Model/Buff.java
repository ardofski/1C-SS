package Model;

import Controller.Fight.BuffDependencies;
import Model.Effects.Effect;

import java.util.ArrayList;

public class Buff {
	//declare stack properties of buffs
	public static final int NO = 0;
	public static final int INTENSITY = 1;
	public static final int DURATION = 2;
	public static final int COUNTER = 2;

	protected String name;
	protected String description;
	protected int x;
	protected boolean isDebuff;
	protected int stackProperty;

	public Buff(String name, int x, boolean isDebuff, String description) {
		this.name = name;
		this.x=x;
		this.isDebuff=isDebuff;
		this.description=description;
	}

	public Buff( String name, int x ){
		this.name = name;
		this.x = x;
	}

	public Buff( int x ){
		this.x = x;
	}

	public ArrayList<Effect> getTurnEffects(BuffDependencies dep ){
		return null;
	}

	public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep){
		if( stackProperty == NO)
			setX(0);
		if( stackProperty == DURATION)
			decreaseRemainingTurn();
		return null;
	}

	public boolean isDebuff(){
		return isDebuff;
	}
	public String getName() {
		return name;
	}
	public int getStackProperty(){ return stackProperty;}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {

		return replaceX(description);
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRemainingTurn() {
		return x;
	}
	public void setRemainingTurn(int remainingTurn) {
		this.x = remainingTurn;
	}

	public int getX(){return x;}
	public void setX( int xx ){x = xx;}
	public boolean isValid(){return x > 0;}
	public void decreaseRemainingTurn(){
		x--;
	}
	public void increaseRemainingTurn(){
		x++;
	}

	private String replaceX(String desc){
		String[] parts = desc.split("X");
		String returnDescription = parts[0];
		for( int i = 1  ;i < parts.length ; i++ ){
			returnDescription += Integer.toString(x);
			returnDescription += parts[i];
		}

		return returnDescription;
	}

	@Override
	public String toString() {
		return "Buff [name=" + name + ", description=" + description + ", remainingTurn=" + x + "]";
	}
	
	
}
