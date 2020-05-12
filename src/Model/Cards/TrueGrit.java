package Model.Cards;

import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Effects.MoveCard;
import Model.Enemy;
import Model.Pile;

import java.util.ArrayList;

public class TrueGrit extends Card {

    public TrueGrit(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade,false);
        name = "TrueGrit";
        rarity = "Common";
        type = "Skill";
        color = "Red";
        description = "Gain 7(9) Block. Exhaust a random(not random) card from your hand.";
        energy = 1;
    }

    /*
        Gain 7(9) Block. Exhaust a random(not random) card from your hand.
    */
    public ArrayList<Effect> getEffects(Enemy e, Pile handPile, Pile exhausePile ){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Block(7,null);
        }
        else{
            effect = new Damage(9,null);
        }

        effects.add(effect);

        int cardIndex = (int)( (Math.random())*(handPile.getCards().size()) );
        Card exCard = handPile.getCards().get( cardIndex );
        effect = new MoveCard( handPile,exhausePile, exCard );
        effects.add(effect);
        //TODO check if upgraded

        return effects;
    }
}
