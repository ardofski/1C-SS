package Controller;

import Model.Card;
import Model.Character;
import Model.Pile;
import Model.Reward;
import Model.Room.EnemyRoom;
import Model.Room.Room;
import Model.Room.TreasureRoom;

public class TreasureController extends RoomController {
    Reward reward;
    boolean cardRewardGiven;
    boolean relicRewardGiven;
    boolean goldRewardGiven;
    boolean potRewardGiven;

    public TreasureController(Character character, Room room) {
        super(character, room);
        reward = ((TreasureRoom)room).getTreasure();
    }


    public Reward getRewards(){
        return reward;
    }

    public boolean takeGoldReward(){
        if( goldRewardGiven )return false;
        int gold = character.getGold();
        character.setGold( gold + reward.getGold() );
        goldRewardGiven=true;
        return true;
    }

    public boolean takeCardReward(int i){
        if( cardRewardGiven )return false;
        Pile cPile = character.getDeck();
        Card c = reward.getCards().get(i);
        character.getDeck().addCard(c);
        cardRewardGiven=true;
        return true;
    }

    public boolean takePotionReward(){
        if( potRewardGiven )return false;
        boolean isAdded = character.addPotion( reward.getPot() );
        potRewardGiven=true;
        return isAdded;
    }

    public boolean takeRelicReward(){
        if( relicRewardGiven )return false;
        character.getRelics().add( reward.getRelic() );
        relicRewardGiven=true;
        return true;
    }

}
