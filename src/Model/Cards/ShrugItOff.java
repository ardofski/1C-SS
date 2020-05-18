package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Effects.*;

import java.util.ArrayList;

/**
 * The type Shrug ıt off.
 */
public class ShrugItOff extends Card {
    /**
     * Instantiates a new Shrug ıt off.
     *
     * @param upgrade the upgrade
     */
    public ShrugItOff( boolean upgrade) {
        super(upgrade,false);
        name = "ShrugItOff";
        rarity = "Common";
        type = "Skill";
        color = "Red";
        description = "Gain 8 Block. Draw 1 card.";
        energy = 1;
        if(upgrade) upgrade();
    }

    /**
     * Upgrade.
     */
    public void upgrade(){
        super.upgrade();
        description = "Gain 11 Block. Draw 1 card.";
    }

    /**
     * Play array list.
     *
     * @param dep the dep
     * @return the array list
     */
/*
        Gain 8(11) Block. Draw 1 card.
    */
    public ArrayList<Effect> play(CardDependencies dep){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Block(11,dep.getCharacter());
        }
        else{
            effect = new Block(8,dep.getCharacter());
        }
        effects.add(effect);

        //take the top card of draw pile to pass.
        effect = new DrawCard();
        effects.add(effect);

        return effects;
    }
}
