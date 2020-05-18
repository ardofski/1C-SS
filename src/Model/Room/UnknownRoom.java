package Model.Room;

import Model.Enemy;

import java.util.ArrayList;

public class UnknownRoom extends Room {

    private ArrayList<EnemyRoom> allMonsterRooms;
    private ArrayList<MerchantRoom> allMerchantRooms;
    private ArrayList<TreasureRoom> allTreasureRooms;
    public UnknownRoom( int act)
    {
        this.act = act;
    }
    public void setRooms(ArrayList<EnemyRoom> allMonsterRooms,ArrayList<MerchantRoom> allMerchantRooms,ArrayList<TreasureRoom> allTreasureRooms)
    {
        this.allMonsterRooms = allMonsterRooms;
        this.allMerchantRooms = allMerchantRooms;
        this.allTreasureRooms = allTreasureRooms;
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    /**
     * @return this function returns a random room
     * the returning probabilities of each room is different
     */
    public Room visit()
    {
        int num = ((int) Math.random()*10);
        if( 0<=num && num <= 2)
        {
            //enemy
            int loc = (int) (Math.random()*allMonsterRooms.size());
            return allMonsterRooms.get(loc);
        }
        if( 3<=num && num <= 6)
        {
            return new RestRoom(1);
        }
        if( 7<=num && num <= 8) {
            // treasure
            int loc = (int) (Math.random() * allTreasureRooms.size());
            return allTreasureRooms.get(loc);
        }
        if( num==9)
        {
            //merchant
            int loc = (int) (Math.random()*allMerchantRooms.size());
            return allMerchantRooms.get(loc);
        }
        //30 enemy 40 rest / 20 treasrue /10 merchant
        return new RestRoom(1);
    }


}
