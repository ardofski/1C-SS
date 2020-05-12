package Model;

import java.util.ArrayList;

public class BuffCollection {
    ArrayList<Buff> buffs;

    public BuffCollection(){
        buffs = new ArrayList<>();
    }

    public ArrayList<Buff> getBuffs(){
        return buffs;
    }

    public void setBuffs(ArrayList<Buff> newBuffs ){
        buffs = new ArrayList<>();
        for( int i = 0 ; i < newBuffs.size() ; i++ ){
            addBuff(newBuffs.get(i));
        }
    }

    public void cleanBuffs(){
        for( int i = 0 ; i < buffs.size() ; i++ ){
            if( buffs.get(i).getX() < 1 ){
                buffs.remove( i );
            }
        }
    }

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
            buffs.get(index).setX( newX );
            return;
        }
        else if( buff.getStackProperty() == Buff.NO )return;
    }

    private int findBuff(String buffName ){
        for( int i = 0 ; i < buffs.size(); i++ ){
            if( buffs.get(i).getName().equals( buffName )  )return i;
        }
        return -1;
    }
}
