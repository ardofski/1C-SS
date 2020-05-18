package Controller;

import Model.Card;
import Model.Character;
import Model.Pile;
import Model.Room.Room;

/**
 * The type Rest site controller.
 */
public class RestSiteController extends RoomController {
    /**
     * Instantiates a new Rest site controller.
     *
     * @param character the character
     * @param room      the room
     */
    public RestSiteController(Character character, Room room) {
        super(character, room);
    }

    /**
     * Upgrade card boolean.
     *
     * @param c the c
     * @return the boolean
     */
    public boolean upgradeCard(Card c){
        //TODO
        c.upgrade();
        return false;
    }

    /**
     * Upgrade card boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean upgradeCard(String name){
        for(int i = 0 ; i < character.getDeck().getCards().size(); i++) {
            if (name == character.getDeck().getCards().get(i).getName()){
                System.out.println("Before UPGRADE CARD NAME ->"+ name + " "+character.getDeck().getCards().get(i).getUpgrade());
                character.getDeck().getCards().get(i).upgrade();
                System.out.println("After UPGRADE CARD NAME ->"+ name + " "+character.getDeck().getCards().get(i).getUpgrade());
                return true;
            }
        }
        return false;
    }

    /**
     * Smith boolean.
     *
     * @param card1 the card 1
     * @param card2 the card 2
     * @param card3 the card 3
     * @return the boolean
     */
    public boolean smith( Card card1, Card card2, Card card3){
        //TODO
        return false;
    }


    /**
     * Rest.
     */
    public void rest(){
        int maxHP = character.getMaxHp();
        int newValue = character.getHp()+ (maxHP*3/10);
        character.setHp(newValue<=maxHP ? newValue : maxHP);
    }

    /**
     * Get card list pile.
     *
     * @return the pile
     */
    public Pile getCardList(){
        return character.getDeck();
    }
}
