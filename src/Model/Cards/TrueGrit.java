package Model.Cards;

import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class TrueGrit extends Card {

    public TrueGrit(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade);
    }

    /*
        Gain 7(9) Block. Exhaust a random(not random) card from your hand.
    */
    public ArrayList<Effect> getEffects(Enemy e){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Block(7,null);
        }
        else{
            effect = new Damage(9,null);
        }

        effects.add(effect);

        //TODO Exhaust a random(not random) card from your hand.

        return effects;
    }
}
