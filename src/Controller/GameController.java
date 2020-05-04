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

public class GameController {
    private Map map;
    private Character character;
    private RoomController controller;

    public GameController(){
        map = null;
        character = null;
        controller = null;
    }
    public GameController(Character character, int gameMode){
        this.character = character;
        controller = null;
        map = new Map(gameMode);
    }
    public GameController(Character character, Map map){
        this.character = character;
        this.map = map;
        controller = null;
    }

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


    public void setActiveRoom(Room room){
        //TODO
    }

    public boolean saveGame(){
        DateFormat df = new SimpleDateFormat("dd.MM.yy HH.mm");
        Date dateObj = new Date();
        String fileName = df.format(dateObj) ;
        GameSaver.saveGame(map, character, fileName);

        return false;
    }

    public void getAvailableRooms(){
        //TODO
        // return type belirlenmeli
    }

    public void selectPet(Pet pet){
        character.changeActivePet(pet);
    }

    public Character getCharacter(){
        return character;
    }

    public Map getMap(){
        return map;
    }

}
