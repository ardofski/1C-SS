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

public class ShrugItOff extends Card {
    public ShrugItOff(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade,false);
    }
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
        effect = new MoveCard(dep.getDrawPile(),dep.getHandPile(),dep.getDrawPile().getTop() );
        effects.add(effect);

        return effects;
    }
}
