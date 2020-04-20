package Controller;

import DBConnection.GameSaver;
import DBConnection.ItemListGetter;
import Model.*;
import Model.Character;

import java.io.File;
import java.util.ArrayList;

public class MenuController {
    private Player activePlayer;
    private ArrayList<Player> players;

    //Constructor
    public MenuController(){
            //TODO
    }

    public GameController createNewGame(int gameMode, Character character){
        return new GameController(character, gameMode);
    }

    public GameController loadGame(String savedGameName){

        Map map = new Map();
        Character character = new Character();
        GameSaver.loadGame(map, character, savedGameName);
        activePlayer.setCharacter(character);

        return new GameController(character, map);
    }

    public ArrayList<Card> getAllCards(){

        return ItemListGetter.allCards();
    }

    public ArrayList<Relic> getAllRelics(){

        return ItemListGetter.allRelics();
    }

    public boolean renamePlayer(String oldName, String newName){
        //TODO
        return true;
    }

    public boolean addNewPlayer(String name){
        //TODO
        return true;
    }

    public void setActivePlayer(String name){
        //TODO
    }

    public boolean saveGame(){
        //TODO
        return true;
    }







}
