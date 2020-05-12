package Model;

import java.util.ArrayList;

public interface Fightable {
    int getHp();
    int getMaxHp();
    int getBlock();

    public BuffCollection getBuffs();

    String getName();


    public void setBuffs(ArrayList<Buff> newBuffs);
    public void addBuff(Buff toAdd);


    void increaseHp(int amount);
    void decreaseHp(int amount);

    void increaseBlock(int amount);
    void decreaseBlock(int amount);
}
