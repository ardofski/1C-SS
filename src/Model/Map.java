package Model;

import GUI.RestScene;
import Model.Room.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Map {

    public static final int LENGTH = 6;
    private static final int DENSITY = 5;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    private boolean[][][][] paths;
    private Room[][] locations;
    private int[] currentLocation = new int[2];
    ArrayList<Room> visitedRoom;
    private boolean[][] roomVisited;
    RoomFactory roomFactory;

    public boolean[][] getRoomVisited() {
        return roomVisited;
    }

    public void setRoomVisited(boolean[][] roomVisited) {
        this.roomVisited = roomVisited;
    }

    public Map(){
        new Map(1);
    }

    public Map(int act ){
        roomFactory = new RoomFactory();
        currentLocation = new int[2];
        currentLocation[0] = -1;
        currentLocation[1] = -1;

        roomVisited = new boolean[LENGTH][LENGTH];
        //init all locations empty

        locations = new Room[LENGTH][LENGTH];
        for( int i = 0 ; i< LENGTH ; i++ ){
            for( int j = 0 ; j < LENGTH ; j++){
                locations[i][j] = null;
                roomVisited[i][j] = false;
            }
        }

        paths = new boolean[LENGTH][LENGTH][LENGTH][LENGTH];
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

        //Room newRoom = new Room();
        //TODO create new Room
        Room newRoom;
        newRoom = roomFactory.getMonsterRooms().get(0);
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
                if( locations[right][left] == null ){
                    //newRoom = new Room();
                    //TODO create new room
                    newRoom = roomFactory.getRandomRoom();
                    locations[right][left] = newRoom;
                }
            }
            if( locations[right][left] == null ){
                //newRoom = new Room();
                //TODO createNewRoom
                newRoom = roomFactory.getRandomRoom();
                locations[right][left] = newRoom;
            }

        }
        locations[LENGTH-1][LENGTH-1] = roomFactory.getBossRoom();

    }

    public Map(Room[][] locations,boolean[][][][] paths ,int currentI , int currentJ ){
        this.locations = locations;
        this.paths = paths;
        currentLocation = new int[2];
        currentLocation[0]= currentI;
        currentLocation[1]= currentJ;
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

    public boolean[][][][] getPaths(){
        return paths;
    }

    public Room[][] getLocations(){

        return locations;

    }

    public Room getCurrentRoom(){
        return locations[ currentLocation[0] ][ currentLocation[1] ];
    }

    public int[] getCurrentLocation(){
        return currentLocation;
    }

    public boolean isAccessible(int i, int j){
        if ( currentLocation[0] == -1 && currentLocation[1] == -1 && i == 0 && j == 0 )return true;
        if ( currentLocation[0] == -1 && currentLocation[1] == -1 && !(i == 0 && j == 0) )return false;
        boolean b = paths[currentLocation[0]][currentLocation[1]][i][j];
        if( b && i+j > currentLocation[0] + currentLocation[1] ) return true;
        return false;
    }

    public boolean visit( int i , int j){
        System.out.println( "Map visit called. i : " + i + " j : " + j);
        if(!isAccessible(i,j) )return false;
        currentLocation[0] = i;
        currentLocation[1] = j;
        System.out.println( "location = " + locations[i][j]);
        locations[i][j].initialize();
        roomVisited[i][j]=true;
        return true;
    }

    public boolean isVisited(int i, int j){
        return roomVisited[i][j];
    }

    public void setPaths(boolean[][][][] paths) {
        this.paths = paths;
    }

    public void setLocations(Room[][] locations) {
        this.locations = locations;
    }

    public void setCurrentLocation(int i, int j) {
        currentLocation[0] = i;
        currentLocation[1] = j;
    }


    @Override
    public String toString() {
        return "Map{" +
                "paths=" + Arrays.toString(paths) +
                ", locations=" + Arrays.toString(locations) +
                ", currentLocation=" + Arrays.toString(currentLocation) +
                '}';
    }
}
