package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Effects.MoveCard;
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
    public ArrayList<Effect> getEffects(CardDependencies dep){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Damage(9,dep.getTarget(),dep.getCharacter());
        }
        else{
            effect = new Damage(10,dep.getTarget(),dep.getCharacter());
        }
        effects.add(effect);

        //read top card of draw pile.

        effect = new MoveCard(dep.getHandPile(),dep.getHandPile(),dep.getDrawPile().getTop() );
        return effects;
    }

}
