package Model.Cards;

import Model.Buff;
import Model.Card;
import Model.Effects.ApplyBuff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class Clothesline extends Card {
    public Clothesline(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade);
    }


    /*
        Deal 12(14) damage. Apply 2(3) Weak.
    */
    public ArrayList<Effect> getEffects(Enemy e){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Damage( 14,e);
            effects.add(effect);
            effect = new ApplyBuff(new Buff(),e ); //TODO change buff constructor
            effects.add(effect);
        }
        else{
            effect = new Damage( 12,e);
            effects.add(effect);
            effect = new ApplyBuff(new Buff(),e ); //TODO change buff constructor
            effects.add(effect);
        }

        effects.add(effect);

        return effects;
    }
}