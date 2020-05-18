package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Buff.
 */
public class Buff {
    /**
     * The constant NO.
     */
//declare stack properties of buffs
	public static final int NO = 0;
    /**
     * The constant INTENSITY.
     */
    public static final int INTENSITY = 1;
    /**
     * The constant DURATION.
     */
    public static final int DURATION = 2;
    /**
     * The constant COUNTER.
     */
    public static final int COUNTER = 2;

    /**
     * The Name.
     */
    protected String name;
    /**
     * The Description.
     */
    protected String description;
    /**
     * The X.
     */
    protected int x;
    /**
     * The Is debuff.
     */
    protected boolean isDebuff;
    /**
     * The Stack property.
     */
    protected int stackProperty;

    /**
     * Instantiates a new Buff.
     *
     * @param name        the name
     * @param x           the x
     * @param isDebuff    the is debuff
     * @param description the description
     */
    public Buff(String name, int x, boolean isDebuff, String description) {
		this.name = name;
		this.x=x;
		this.isDebuff=isDebuff;
		this.description=description;
	}

    /**
     * Instantiates a new Buff.
     *
     * @param name the name
     * @param x    the x
     */
    public Buff( String name, int x ){
		this.name = name;
		this.x = x;
	}

    /**
     * Instantiates a new Buff.
     *
     * @param x the x
     */
    public Buff( int x ){
		this.x = x;
	}

    /**
     * Get turn effects array list.
     *
     * @param dep the dep
     * @return the array list
     */
    public ArrayList<Effect> getTurnEffects(BuffDependencies dep ){
		return null;
	}

    /**
     * Get next turn effects array list.
     *
     * @param dep the dep
     * @return the array list
     */
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep){
		if( stackProperty == NO)
			setX(0);
		if( stackProperty == DURATION)
			decreaseRemainingTurn();
		return null;
	}

    /**
     * Is debuff boolean.
     *
     * @return the boolean
     */
    public boolean isDebuff(){
		return isDebuff;
	}

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
		return name;
	}

    /**
     * Get stack property int.
     *
     * @return the int
     */
    public int getStackProperty(){ return stackProperty;}

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
		this.name = name;
	}

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {

		return replaceX(description);
	}

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
		this.description = description;
	}

    /**
     * Gets remaining turn.
     *
     * @return the remaining turn
     */
    public int getRemainingTurn() {
		return x;
	}

    /**
     * Sets remaining turn.
     *
     * @param remainingTurn the remaining turn
     */
    public void setRemainingTurn(int remainingTurn) {
		this.x = remainingTurn;
	}

    /**
     * Get x int.
     *
     * @return the int
     */
    public int getX(){return x;}

    /**
     * Set x.
     *
     * @param xx the xx
     */
    public void setX( int xx ){x = xx;}

    /**
     * Is valid boolean.
     *
     * @return the boolean
     */
    public boolean isValid(){return x > 0;}

    /**
     * Decrease remaining turn.
     */
    public void decreaseRemainingTurn(){
		x--;
	}

    /**
     * Increase remaining turn.
     */
    public void increaseRemainingTurn(){
		x++;
	}

    /**
     * Replace x string.
     *
     * @param desc the desc
     * @return the string
     */
    private String replaceX(String desc){
		String[] parts = desc.split("X");
		String returnDescription = parts[0];
		for( int i = 1  ;i < parts.length ; i++ ){
			returnDescription += Integer.toString(x);
			returnDescription += parts[i];
		}

		return returnDescription;
	}

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
	public String toString() {
		return "Buff [name=" + name + ", description=" + description + ", remainingTurn=" + x + "]";
	}
	
	
}
