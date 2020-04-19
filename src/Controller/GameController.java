package Controller;

import Model.Character;
import Model.Map;
import Model.Pet;
import Model.Room.Room;

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

    public RoomController createController(Room room){
        return null;
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
