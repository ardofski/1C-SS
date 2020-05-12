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

public class Headbutt extends Card {
    public Headbutt(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade,true);
    }

    /*
        Deal 9(12) damage. Place a card from your discard pile on top of your draw pile.
     */
        /*
        Gain 5(8) Block.
    */
    public ArrayList<Effect> play(CardDependencies dep){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Block(12,dep.getTarget());
        }
        else{
            effect = new Damage(9,dep.getTarget(),dep.getCharacter() );
        }
        effects.add(effect);

        effect = new MoveCard(dep.getDiscardPile(),dep.getDrawPile(),null ); //TODO get top card of discard pile
        effects.add(effect);

        return effects;
    }

}
