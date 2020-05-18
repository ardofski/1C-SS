package Controller;

import Controller.Fight.FightController;
import DBConnection.GameSaver;
import Model.Character;
import Model.Map;
import Model.Pet;
import Model.Room.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MapController {
    private Map map;
    private Character character;
    private RoomController controller;

    public MapController(){
        map = null;
        character = null;
        controller = null;
    }

    public MapController(Character character, int gameMode){
        this.character = character;
        controller = null;
        map = new Map(gameMode);
    }

    public MapController(Character character, Map map){
        this.character = character;
        this.map = map;
        controller = null;
    }

    /**
     * @param room
     * @return creates and returns the controller of the given room
     */
    public RoomController createController(Room room){

        if ( room instanceof UnknownRoom)
            room = ((UnknownRoom) room).visit();


        if(room instanceof TreasureRoom){
            controller = new TreasureController(character, room);
        }
        else if(room instanceof EnemyRoom){
            controller = new FightController(character, room);
        }
        else if(room instanceof MerchantRoom){
            controller = new MerchantController(character, room);
        }
        else if(room instanceof RestRoom){
            controller = new RestSiteController(character, room);
        }
        else if(room instanceof EventRoom){
            controller = new EventController(character, room);
        }
        return controller;
    }

    public boolean visit( int i , int j){
        return map.visit(i,j);
    }

    /**
     * @return true if the game is saved successfully
     */
    public boolean saveGame(){
        DateFormat df = new SimpleDateFormat("dd.MM.yy HH.mm");
        Date dateObj = new Date();
        String fileName = df.format(dateObj) ;
        GameSaver.saveGame(map, character, fileName);

        return false;
    }

    public boolean isAccessible(int i , int j){
        return map.isAccessible(i,j);
    }

    /**
     * @param i i component of the room
     * @param j j component of the location of hte room
     * @return returns if the given location is visited before
     */
    public boolean isVisited(int i, int j){ return map.isVisited(i,j);}

    public boolean[][][][] getPaths(){
        return map.getPaths();
    }

    public Room[][] getLocations(){
        return map.getLocations();
    }

    public void selectPet(Pet pet){
        character.changeActivePet(pet);
    }

    public Character getCharacter(){
        return character;
    }

    public int getFloorNumber(){
        return map.getCurrentLocation()[0]+map.getCurrentLocation()[1]+1;
    }
}
