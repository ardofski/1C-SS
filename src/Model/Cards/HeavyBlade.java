package Model.Cards;

import Model.Buff;
import Model.Buffs.Strength;
import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;
import Model.Character;

import java.util.ArrayList;

public class HeavyBlade extends Card {

    public HeavyBlade(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade);
    }

    /*
        Deal 14 damage. Strength affects Heavy Blade 3(5) times.
    */
    public ArrayList<Effect> getEffects(Enemy e, Character character){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            //TODO check strenght of character
            effect = new Damage(14,e);
        }
        else{
            //TODO check strenght of character
            effect = new Damage(14,e);
        }

        effects.add(effect);

        return effects;
    }
}
