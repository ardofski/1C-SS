package Controller;

import Model.Card;
import Model.Character;
import Model.Pile;
import Model.Reward;
import Model.Room.Room;
import Model.Room.TreasureRoom;

/**
 * The type Room controller.
 */
public class RoomController {
    /**
     * The Character.
     */
    protected Character character;
    /**
     * The Room.
     */
    protected Room room;

    /**
     * Instantiates a new Room controller.
     *
     * @param character the character
     * @param room      the room
     */
    public RoomController(Character character, Room room){
        this.character = character;
        this.room = room;
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
     * Get rewards reward.
     *
     * @return the reward
     */
    public Reward getRewards(){return null;}

    /**
     * Take gold reward boolean.
     *
     * @return the boolean
     */
    public boolean takeGoldReward(){return false;}

    /**
     * Take card reward boolean.
     *
     * @param i the
     * @return the boolean
     */
    public boolean takeCardReward(int i){return false;}

    /**
     * Take potion reward boolean.
     *
     * @return the boolean
     */
    public boolean takePotionReward(){return false;}

    /**
     * Take relic reward boolean.
     *
     * @return the boolean
     */
    public boolean takeRelicReward(){return false;}
}
