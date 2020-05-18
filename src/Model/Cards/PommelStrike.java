package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Card;
import Model.Effects.*;
import Model.Enemy;
import Model.Pile;

import java.util.ArrayList;

/**
 * The type Pommel strike.
 */
public class PommelStrike extends Card {
    /**
     * Instantiates a new Pommel strike.
     *
     * @param upgrade the upgrade
     */
    public PommelStrike(boolean upgrade) {
        super(upgrade,true);
        name = "PommelStrike";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal 9 damage. Draw 1 card.";
        energy = 1;
        if(upgrade) upgrade();
    }

    /**
     * Upgrade.
     */
    public void upgrade(){
        super.upgrade();
        description = "Deal 10 damage. Draw 2 cards.";
    }

    /**
     * Play array list.
     *
     * @param dep the dep
     * @return the array list
     */
/*
        Deal 9(10) damage. Draw 1(2) card(s)
    */
    public ArrayList<Effect> play(CardDependencies dep){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( !upgrade ){
            effect = new Damage(9,dep.getTarget(),dep.getCharacter());
            effects.add(effect);
        }
        else{
            effect = new Damage(10,dep.getTarget(),dep.getCharacter());
            effect = new DrawCard();
            effects.add(effect);
        }
        effect = new DrawCard();
        effects.add(effect);
        //read top card of draw pile.

        return effects;
    }

}
