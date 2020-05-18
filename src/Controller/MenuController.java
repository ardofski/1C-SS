package Controller;

import Model.CharacterFactory;
import Model.Cards.CardFactory;
import DBConnection.GameSaver;
import Model.*;
import Model.Character;
import Model.Relics.Relic;
import Model.Relics.RelicFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Menu controller.
 */
public class MenuController {
    /**
     * The Active player.
     */
    private Player activePlayer;
    /**
     * The Players.
     */
    private ArrayList<Player> players;

    /**
     * Instantiates a new Menu controller.
     */
//Constructor
    public MenuController(){
            players = GameSaver.loadPlayers();
            activePlayer = players.get(0);
    }

    /**
     * Create new game map controller.
     *
     * @param gameMode the game mode
     * @param charName the char name
     * @return the map controller
     */
    public MapController createNewGame(int gameMode, String charName){
        return new MapController(CharacterFactory.getCharacter(charName), gameMode);
    }

    /**
     * Load game map controller.
     *
     * @param savedGameName the saved game name
     * @return the map controller
     */
    public MapController loadGame(String savedGameName){

        Map map = new Map();
        Character character = new Character();
        GameSaver.loadGame(map, character, savedGameName);

        return new MapController(character, map);
    }

    /**
     * Get all cards array list.
     *
     * @return the array list
     */
    public ArrayList<Card> getAllCards(){
        return CardFactory.getAllCards();
    }

    /**
     * Get all relics array list.
     *
     * @return the array list
     */
    public ArrayList<Relic> getAllRelics(){
        return RelicFactory.getAllRelics();
    }

    /**
     * Rename player boolean.
     *
     * @param oldName the old name
     * @param newName the new name
     * @return the boolean
     */
    public boolean renamePlayer(String oldName, String newName){
        for(Player player: players){
            if(player.getName().equals(oldName)) {
                player.setName(newName);
                return true;
            }
        }
        return false;
    }

    /**
     * Add new player boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean addNewPlayer(String name){
        if(players.size() >= 3)
            return false;
        players.add(new Player(name));
        return true;
    }

    /**
     * Set active player.
     *
     * @param name the name
     */
    public void setActivePlayer(String name){
        for (Player p: players) {
            if(p.getName().equals(name))
                activePlayer = p;
        }
    }

    /**
     * Save game.
     */
    public void saveGame(){
        GameSaver.savePlayer(players);
    }

    /**
     * Get active player player.
     *
     * @return the player
     */
    public Player getActivePlayer(){
        return activePlayer;
    }

    /**
     * Get players array list.
     *
     * @return the array list
     */
    public ArrayList<Player> getPlayers(){
        return players;
    }

    /**
     * Get saved games names array list.
     *
     * @return the array list
     */
    public ArrayList<String> getSavedGamesNames(){
        File file = new File(GameSaver.SAVED_GAME_FOLDER_PATH);
        String[] fileList = file.list();
        for(String name:fileList){
            System.out.println(name);
        }
        return new ArrayList<String>(Arrays.asList(fileList));
    }

    /**
     * Delete player.
     *
     * @param name the name
     */
    public void deletePlayer(String name){
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getName().equals(name)){
                players.remove(i);
                return;
            }
        }
    }







}
