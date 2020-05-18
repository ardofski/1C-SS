package Model.Room;

import Model.Enemy;

import java.util.ArrayList;

/**
 * The type Unknown room.
 */
public class UnknownRoom extends Room {

    /**
     * The All monster rooms.
     */
    private ArrayList<EnemyRoom> allMonsterRooms;
    /**
     * The All merchant rooms.
     */
    private ArrayList<MerchantRoom> allMerchantRooms;
    /**
     * The All treasure rooms.
     */
    private ArrayList<TreasureRoom> allTreasureRooms;

    /**
     * Instantiates a new Unknown room.
     *
     * @param act the act
     */
    public UnknownRoom( int act)
    {
        this.act = act;
    }

    /**
     * Sets rooms.
     *
     * @param allMonsterRooms  the all monster rooms
     * @param allMerchantRooms the all merchant rooms
     * @param allTreasureRooms the all treasure rooms
     */
    public void setRooms(ArrayList<EnemyRoom> allMonsterRooms,ArrayList<MerchantRoom> allMerchantRooms,ArrayList<TreasureRoom> allTreasureRooms)
    {
        this.allMonsterRooms = allMonsterRooms;
        this.allMerchantRooms = allMerchantRooms;
        this.allTreasureRooms = allTreasureRooms;
    }

    /**
     * Initialize.
     */
    @Override
    public void initialize() {
        super.initialize();
    }

    /**
     * Visit room.
     *
     * @return the room
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
