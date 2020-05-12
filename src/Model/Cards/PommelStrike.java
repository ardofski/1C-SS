package Model.Cards;

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
    public ArrayList<Effect> getEffects(Enemy e, Pile drawPile, Pile handPile ){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Damage(9,e);
        }
        else{
            effect = new Damage(10,e);
        }
        effects.add(effect);

        //read top card of draw pile.

        effect = new MoveCard(drawPile,handPile,drawPile.getTop() );
        return effects;
    }
}
