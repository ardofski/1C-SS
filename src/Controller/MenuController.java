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
            players = GameSaver.loadPlayers();
            activePlayer = players.get(0);
    }

    public GameController createNewGame(int gameMode, Character character){
        //TODO karakterin adi mi alinacak obje mi alinacak
        return new GameController(character, gameMode);
    }

    public GameController loadGame(String savedGameName){

        Map map = new Map();
        Character character = new Character();
        GameSaver.loadGame(map, character, savedGameName);

        return new GameController(character, map);
    }

    public ArrayList<Card> getAllCards(){

        return ItemListGetter.allCards();
    }

    public ArrayList<Relic> getAllRelics(){

        return ItemListGetter.allRelics();
    }

    public boolean renamePlayer(String oldName, String newName){
        for(Player player: players){
            if(player.getName() == oldName) {
                player.setName(newName);
                return true;
            }
        }
        return false;
    }

    public boolean addNewPlayer(String name){
        if(players.size() >= 3)
            return false;
        players.add(new Player(name));
        return true;
    }

    public void setActivePlayer(String name){
        for (Player p: players) {
            if(p.getName() == name)
                activePlayer = p;
        }
    }

    public void saveGame(){
        GameSaver.savePlayer(players);
    }







}
