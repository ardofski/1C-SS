package Controller;

import Controller.Fight.FightController;
import Model.Character;
import Model.Map;
import Model.Pet;
import Model.Room.*;

public class GameController {
    private Map map;
    private Character character;
    private RoomController controller;
    int gameMode;

    public GameController(Character character, int gameMode){
        this.gameMode = gameMode;
        this.character = character;
        controller = null;
        map = createMap(gameMode);
    }
    public GameController(Character character, int gameMode, Map map){
        this.character = character;
        this.gameMode = gameMode;
        this.map = map;
        controller = null;
    }

    public void createController(Room room){

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
    }


    public void setActiveRoom(Room room){

    }

    public boolean saveGame(){
        return false;
    }

    public void getAvailableRooms(){
        // return type belirlenmeli
    }

    public void selectPet(Pet pet){

    }

    private Map createMap(int gameMode) {
        return null;
    }
}
