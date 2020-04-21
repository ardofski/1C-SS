package Model.Cards;

import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class IronWave extends Card {
    public IronWave(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade);
    }

    /*
        Gain 5(7) Block. Deal 5(7) damage
    */

    public ArrayList<Effect> getEffects(Enemy e){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Block(5,null);
            effects.add(effect);
            effect = new Damage(5,e);
            effects.add(effect);
        }
        else{
            effect = new Block(7,null);
            effects.add(effect);
            effect = new Damage(7,e);
            effects.add(effect);
        }

        return effects;
    }
}
