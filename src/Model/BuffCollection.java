package Model;

import java.util.ArrayList;

/**
 * The type Buff collection.
 */
public class BuffCollection {
    /**
     * The Buffs.
     */
    ArrayList<Buff> buffs;

    /**
     * Instantiates a new Buff collection.
     */
    public BuffCollection(){
        buffs = new ArrayList<>();
    }

    /**
     * Get buffs array list.
     *
     * @return the array list
     */
    public ArrayList<Buff> getBuffs(){
        return buffs;
    }

    /**
     * Set buffs.
     *
     * @param newBuffs the new buffs
     */
    public void setBuffs(ArrayList<Buff> newBuffs ){
        buffs = new ArrayList<>();
        for( int i = 0 ; i < newBuffs.size() ; i++ ){
            addBuff(newBuffs.get(i));
        }
    }

    /**
     * Clean buffs.
     */
    public void cleanBuffs(){
        for( int i = 0 ; i < buffs.size() ; i++ ){
            if( buffs.get(i).getX() < 1 ){
                buffs.remove( i );
            }
        }
    }

    /**
     * Clear all buffs.
     */
    public void clearAllBuffs(){
        for( int i = buffs.size() - 1 ; i >= 0 ; i--)
            buffs.remove(i);
    }

    /**
     * Add buff.
     *
     * @param buff the buff
     */
    public void addBuff( Buff buff ){
        int index = findBuff( buff.getName() );
        if( index == -1 ){
            buffs.add( buff );
            return;
        }
        if( buff.getStackProperty() == Buff.DURATION ){
            int newX = buff.getX() + buffs.get(index).getX();
            buffs.get(index).setX( newX );
            return;
        }
        else if( buff.getStackProperty() == Buff.INTENSITY ){
            int newX = buff.getX() + buffs.get(index).getX();
            System.out.println("add buff is called" );
            System.out.println("added x : " + buff.getX() + " prev x: " + buffs.get(index).getX() );
            buffs.get(index).setX( newX );
            return;
        }
        else if( buff.getStackProperty() == Buff.NO )return;
    }

    /**
     * Find buff int.
     *
     * @param buffName the buff name
     * @return the int
     */
    private int findBuff(String buffName ){
        for( int i = 0 ; i < buffs.size(); i++ ){
            if( buffs.get(i).getName().equals( buffName )  )return i;
        }
        return -1;
    }
}
