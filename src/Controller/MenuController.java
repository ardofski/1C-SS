package Controller;

import DBConnection.CharacterFactory;
import Model.Cards.CardFactory;
import DBConnection.GameSaver;
import Model.*;
import Model.Character;
import Model.Relics.Relic;
import Model.Relics.RelicFactory;

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

    public GameController createNewGame(int gameMode, String charName){
        return new GameController(CharacterFactory.getCharacter(charName), gameMode);
    }

    public GameController loadGame(String savedGameName){

        Map map = new Map();
        Character character = new Character();
        GameSaver.loadGame(map, character, savedGameName);

        return new GameController(character, map);
    }

    public ArrayList<Card> getAllCards(){
        return CardFactory.getAllCards();
    }

    public ArrayList<Relic> getAllRelics(){
        return RelicFactory.getAllRelics();
    }

    public boolean renamePlayer(String oldName, String newName){
        for(Player player: players){
            if(player.getName().equals(oldName)) {
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
            if(p.getName().equals(name))
                activePlayer = p;
        }
    }

    public void saveGame(){
        GameSaver.savePlayer(players);
    }

    public Player getActivePlayer(){
        return activePlayer;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public String[] getSavedGamesNames(){
        File file = new File("data\\savedGames");
        String[] fileList = file.list();
        for(String name:fileList){
            System.out.println(name);
        }
        return fileList;
    }

    public void deletePlayer(String name){
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getName().equals(name)){
                players.remove(i);
                return;
            }
        }
    }







}
