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
    public PommelStrike(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade,true);
    }

    /*
        Deal 9(10) damage. Draw 1(2) card(s)
    */
    public ArrayList<Effect> getEffects(CardDependencies dep){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Damage(9,dep.getTarget());
        }
        else{
            effect = new Damage(10,dep.getTarget());
        }
        effects.add(effect);

        //read top card of draw pile.

        effect = new MoveCard(dep.getHandPile(),dep.getHandPile(),dep.getDrawPile().getTop() );
        return effects;
    }


    //TODO  remove this method
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
