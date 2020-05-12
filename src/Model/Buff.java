package Model;

import Controller.Fight.BuffDependencies;
import Model.Effects.Effect;

import java.util.ArrayList;

public class Buff {
	//declare stack properties of buffs
	public static final int NO = 0;
	public static final int INTENSITY = 1;
	public static final int DURATION = 2;

	protected String name;
	protected String description;
	protected int remainingTurn;
	protected int x;
	protected boolean isDebuff;
	protected int stackProperty;

	public Buff(String name, int remainingTurn, boolean isDebuff, String description) {
		this.name = name;
		this.remainingTurn=remainingTurn;
		this.isDebuff=isDebuff;
		this.description=description;
	}

	public Buff( String name, int remainingTurn ){
		this.name = name;
		this.remainingTurn = remainingTurn;
	}

	public Buff( int x ){
		this.x = x;
	}

	public ArrayList<Effect> getTurnEffects(BuffDependencies dep ){
		return null;
	}

	public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep){
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
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRemainingTurn() {
		return remainingTurn;
	}
	public void setRemainingTurn(int remainingTurn) {
		this.remainingTurn = remainingTurn;
	}

	public int getX(){return x;}
	public void setX( int xx ){x = xx;}
	public boolean isValid(){return x > 0;}
	public void decreaseRemainingTurn(){
		remainingTurn--;
	}
	public void increaseRemainingTurn(){
		remainingTurn++;
	}
	@Override
	public String toString() {
		return "Buff [name=" + name + ", description=" + description + ", remainingTurn=" + remainingTurn + "]";
	}
	
	
}
