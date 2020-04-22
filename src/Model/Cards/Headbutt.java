package Model.Cards;

import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Effects.MoveCard;
import Model.Enemy;
import Model.Pile;

import java.util.ArrayList;

public class Headbutt extends Card {
    public Headbutt(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade);
    }

    /*
        Deal 9(12) damage. Place a card from your discard pile on top of your draw pile.
     */
        /*
        Gain 5(8) Block.
    */
    public ArrayList<Effect> getEffects(Enemy e, Pile discardPile, Pile drawPile){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Block(12,e);
        }
        else{
            effect = new Damage(9,e);
        }
        effects.add(effect);


        effect = new MoveCard(discardPile,drawPile,null ); //TODO get top card of discard pile
        effects.add(effect);

        return effects;
    }
}
