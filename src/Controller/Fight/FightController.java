package Controller.Fight;

import Controller.RoomController;
import Model.Character;
import Model.Enemy;
import Model.Pile;
import Model.Room.Room;

import java.util.ArrayList;

public class FightController extends RoomController {

    //instances
    private ArrayList<Enemy> enemies;
    private Integer turn, currentEnergy;
    private Pile handPile, drawPile, discardPile, exhaustPile;
    private EffectHandler effectHandler;

    //Constructor
    public FightController(Character character, Room room) {
        super(character, room);
    }

}
