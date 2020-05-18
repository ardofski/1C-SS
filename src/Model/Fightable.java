package Model;

import Model.Buffs.Buff;
import Model.Buffs.BuffCollection;

import java.util.ArrayList;

/**
 * The interface Fightable.
 */
public interface Fightable {
    /**
     * Gets hp.
     *
     * @return the hp
     */
    int getHp();

    /**
     * Gets max hp.
     *
     * @return the max hp
     */
    int getMaxHp();

    /**
     * Gets block.
     *
     * @return the block
     */
    int getBlock();

    /**
     * Gets buffs.
     *
     * @return the buffs
     */
    public BuffCollection getBuffs();

    /**
     * Gets name.
     *
     * @return the name
     */
    String getName();


    /**
     * Sets buffs.
     *
     * @param newBuffs the new buffs
     */
    public void setBuffs(ArrayList<Buff> newBuffs);

    /**
     * Add buff.
     *
     * @param toAdd the to add
     */
    public void addBuff(Buff toAdd);


    /**
     * Increase hp.
     *
     * @param amount the amount
     */
    void increaseHp(int amount);

    /**
     * Decrease hp.
     *
     * @param amount the amount
     */
    void decreaseHp(int amount);

    /**
     * Increase block.
     *
     * @param amount the amount
     */
    void increaseBlock(int amount);

    /**
     * Decrease block.
     *
     * @param amount the amount
     */
    void decreaseBlock(int amount);
}
