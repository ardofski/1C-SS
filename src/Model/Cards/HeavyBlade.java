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

    public HeavyBlade( boolean upgrade) {
        super( upgrade,true);
        name = "HeavyBlade";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal 14 damage. Strength affects Heavy Blade 3(5) times.";
        energy = 2;
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
