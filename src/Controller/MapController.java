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

/**
 * The type Map controller.
 */
public class MapController {
    /**
     * The Map.
     */
    private Map map;
    /**
     * The Character.
     */
    private Character character;
    /**
     * The Controller.
     */
    private RoomController controller;

    /**
     * Instantiates a new Map controller.
     */
    public MapController(){
        map = null;
        character = null;
        controller = null;
    }

    /**
     * Instantiates a new Map controller.
     *
     * @param character the character
     * @param gameMode  the game mode
     */
    public MapController(Character character, int gameMode){
        this.character = character;
        controller = null;
        map = new Map(gameMode);
    }

    /**
     * Instantiates a new Map controller.
     *
     * @param character the character
     * @param map       the map
     */
    public MapController(Character character, Map map){
        this.character = character;
        this.map = map;
        controller = null;
    }

    /**
     * Create controller room controller.
     *
     * @param room the room
     * @return the room controller
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

    /**
     * Visit boolean.
     *
     * @param i the
     * @param j the j
     * @return the boolean
     */
    public boolean visit( int i , int j){
        return map.visit(i,j);
    }

    /**
     * Save game boolean.
     *
     * @return the boolean
     */
    public boolean saveGame(){
        DateFormat df = new SimpleDateFormat("dd.MM.yy HH.mm");
        Date dateObj = new Date();
        String fileName = df.format(dateObj) ;
        GameSaver.saveGame(map, character, fileName);

        return false;
    }

    /**
     * Is accessible boolean.
     *
     * @param i the
     * @param j the j
     * @return the boolean
     */
    public boolean isAccessible(int i , int j){
        return map.isAccessible(i,j);
    }

    /**
     * Is visited boolean.
     *
     * @param i the
     * @param j the j
     * @return the boolean
     */
    public boolean isVisited(int i, int j){ return map.isVisited(i,j);}

    /**
     * Get paths boolean [ ] [ ] [ ] [ ].
     *
     * @return the boolean [ ] [ ] [ ] [ ]
     */
    public boolean[][][][] getPaths(){
        return map.getPaths();
    }

    /**
     * Get locations room [ ] [ ].
     *
     * @return the room [ ] [ ]
     */
    public Room[][] getLocations(){
        return map.getLocations();
    }

    /**
     * Select pet.
     *
     * @param pet the pet
     */
    public void selectPet(Pet pet){
        character.changeActivePet(pet);
    }

    /**
     * Get character character.
     *
     * @return the character
     */
    public Character getCharacter(){
        return character;
    }

    /**
     * Get floor number int.
     *
     * @return the int
     */
    public int getFloorNumber(){
        return map.getCurrentLocation()[0]+map.getCurrentLocation()[1]+1;
    }
}
