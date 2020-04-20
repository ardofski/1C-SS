package Controller;

import Model.Character;
import Model.Pile;
import Model.Player;
import Model.Relic;

import java.io.File;
import java.util.ArrayList;

public class MenuController {
    private Player activePlayer;
    private ArrayList<Player> players;

    //Constructor
    public MenuController(){

    }

    public GameController createNewGame(){
        return null;
    }

    public GameController loadGame(String savedGameName){
        return null;
    }

    public Pile getAllCards(){
        return null;
    }

    public ArrayList<Relic> getAllRelics(){
        return null;
    }

    public boolean renamePlayer(String oldName, String newName){
        return true;
    }

    public boolean addNewPlayer(String name){
        return true;
    }

    public void setActivePlayer(String name){

    }

    public boolean saveGame(){


        return true;
    }

    public GameController createGameController(int gameMode, Character character){
        return null;
    }

    public GameController createGameController(){
        return null;
    }





}
