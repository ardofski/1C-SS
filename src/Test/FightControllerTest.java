package Test;

import Controller.Fight.FightController;
import Model.Character;
import Model.Pile;
import Model.Room.EnemyRoom;
import Model.Room.Room;

import java.nio.file.spi.FileSystemProvider;
import java.util.logging.Handler;

public class FightControllerTest {

    public void test(){
        Character character = new Character();
        Room room = new EnemyRoom(1);
        FightController fightController = new FightController(character,room);
        Pile handPile = fightController.getHandPile();
        System.out.println( handPile );





    }
}
