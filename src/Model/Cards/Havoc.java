package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

/**
 * The type Havoc.
 */
public class Havoc extends Card {
    /**
     * Instantiates a new Havoc.
     *
     * @param upgrade the upgrade
     */
    public Havoc(boolean upgrade) {
        super( upgrade,false);
        name = "Havoc";
        rarity = "Common";
        type = "Skill";
        color = "Red";
        description = "Play the top card of your draw pile and Exhaust it.";
        energy = 1;
        if(upgrade) upgrade();
    }

    /**
     * Upgrade.
     */
    public void upgrade(){
        super.upgrade();
        energy = 0;
    }

    /*
    Play the top card of your draw pile and Exhaust it.
    */

    /**
     * Play array list.
     *
     * @param dep the dep
     * @return the array list
     */
    public ArrayList<Effect> play(CardDependencies dep){
        //TODO
        return null;
    }

    /**
     * Get effects array list.
     *
     * @param e the e
     * @return the array list
     */
//todo remove this method
    public ArrayList<Effect> getEffects(Enemy e){
        //TODO
        return null;
    }
}
