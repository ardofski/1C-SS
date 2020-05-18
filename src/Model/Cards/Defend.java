package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Effects.Block;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Defend.
 */
public class Defend extends Card {

    /**
     * Instantiates a new Defend.
     *
     * @param upgrade the upgrade
     */
    public Defend(boolean upgrade) {
        super(upgrade,false);
        name = "Defend";
        rarity = "Starter";
        type = "Skill";
        color = "Red";
        description = "Gain 5 Block.";
        energy = 1;
        if(upgrade) upgrade();
    }

    /**
     * Upgrade.
     */
    public void upgrade(){
        super.upgrade();
        description = "Gain 8 Block.";
    }

    /**
     * Play array list.
     *
     * @param dep the dep
     * @return the array list
     */
/*
        Gain 5(8) Block.
    */
    public ArrayList<Effect> play(CardDependencies dep){

        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Block(8,dep.getCharacter());
        }
        else{
            effect = new Block(5,dep.getCharacter());
        }

        effects.add(effect);

        return effects;
    }


}
