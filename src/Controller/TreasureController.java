package Controller;

import Model.Card;
import Model.Character;
import Model.Pile;
import Model.Reward;
import Model.Room.EnemyRoom;
import Model.Room.Room;
import Model.Room.TreasureRoom;

/**
 * The type Treasure controller.
 */
public class TreasureController extends RoomController {
    /**
     * The Reward.
     */
    Reward reward;
    /**
     * The Card reward given.
     */
    boolean cardRewardGiven;
    /**
     * The Relic reward given.
     */
    boolean relicRewardGiven;
    /**
     * The Gold reward given.
     */
    boolean goldRewardGiven;
    /**
     * The Pot reward given.
     */
    boolean potRewardGiven;

    /**
     * Instantiates a new Treasure controller.
     *
     * @param character the character
     * @param room      the room
     */
    public TreasureController(Character character, Room room) {
        super(character, room);
        reward = ((TreasureRoom)room).getTreasure();
    }


    /**
     * Get rewards reward.
     *
     * @return the reward
     */
    public Reward getRewards(){
        return reward;
    }

    /**
     * Take gold reward boolean.
     *
     * @return the boolean
     */
    public boolean takeGoldReward(){
        if( goldRewardGiven )return false;
        int gold = character.getGold();
        character.setGold( gold + reward.getGold() );
        goldRewardGiven=true;
        return true;
    }

    /**
     * Take card reward boolean.
     *
     * @param i the
     * @return the boolean
     */
    public boolean takeCardReward(int i){
        if( cardRewardGiven )return false;
        Pile cPile = character.getDeck();
        Card c = reward.getCards().get(i);
        character.getDeck().addCard(c);
        cardRewardGiven=true;
        return true;
    }

    /**
     * Take potion reward boolean.
     *
     * @return the boolean
     */
    public boolean takePotionReward(){
        if( potRewardGiven )return false;
        boolean isAdded = character.addPotion( reward.getPot() );
        potRewardGiven=true;
        return isAdded;
    }

    /**
     * Take relic reward boolean.
     *
     * @return the boolean
     */
    public boolean takeRelicReward(){
        if( relicRewardGiven )return false;
        character.addRelic( reward.getRelic() );
        relicRewardGiven=true;
        return true;
    }

}
