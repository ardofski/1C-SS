package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Card;
import Model.Effects.*;
import Model.Enemy;
import Model.Pile;

import java.util.ArrayList;

public class PommelStrike extends Card {
    public PommelStrike(boolean upgrade) {
        super(upgrade,true);
        name = "PommelStrike";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal 9 damage. Draw 1 card.";
        energy = 1;
    }
    public void upgrade(){
        super.upgrade();
        description = "Deal 10 damage. Draw 2 cards.";
    }

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
