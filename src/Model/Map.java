package Model;

import Model.Room.Location;
import Model.Room.Room;

import java.util.ArrayList;

public class Map {

    private static final int NUM_OF_FLOORS = 7;
    private static final int LENGTH = 4;
    private static final int DENSITY = 5;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    //ArrayList<Location> locations;
    //ArrayList<Room> rooms;
    //ArrayList<Path> paths;
    boolean[][][][] paths;
    Room[][] locations;
    Room currentRoom;
    ArrayList<Room> visitedRoom;

    public Map(){
        new Map(1);
    }

    public Map( int act ){

        //init all locations empty

        locations = new Room[LENGTH][LENGTH];
        for( int i = 0 ; i< LENGTH ; i++ ){
            for( int j = 0 ; j < LENGTH ; j++){
                locations[i][j] = null;
            }
        }

        for( int i1 = 0 ; i1 < LENGTH ; i1++ ){
            for( int i2 = 0 ; i2 < LENGTH ; i2++ ){
                for( int i3 = 0 ; i3 < LENGTH ; i3++ ){
                    for( int i4 = 0 ; i4 < LENGTH ; i4++ ){
                        paths[i1][i2][i3][i4] = false;
                    }
                }

            }

        }
        int right = 0;
        int left = 0;
        int direction;
        Room newRoom = new Room();
        locations[right][left] = newRoom;
        for( int i = 1 ; i <= DENSITY ;  i++ ){
            right = 0;
            left = 0;

            while( right < LENGTH - 1 || left < LENGTH - 1 ){

                direction = chooseNext( right , left );
                if( direction == LEFT ){
                    paths[right][left][right][left+1] = true;
                    left++;
                }
                if( direction == RIGHT ){
                    paths[right][left][right+1][left] = true;
                    right++;
                }
                if( locations[right][left] != null ){
                    newRoom = new Room();
                    locations[right][left] = newRoom;
                }
            }
            if( locations[right][left] != null ){
                newRoom = new Room();
                locations[right][left] = newRoom;
            }

        }

    }

    private int chooseNext(int right , int left ){
        //int length = (int) Math.sqrt( floorToRoom() );
        if( right == LENGTH - 1  ){
            return LEFT;
        }
        if( left == LENGTH - 1 ){
            return RIGHT;
        }
        int direction = (int)(Math.random()*2);
        return direction;

    }





}
