package Model.Room;

public class UnknownRoom extends Room {
    public Room visit()
    {
        // not implemented yet
        return new EnemyRoom(1);
    }
}
