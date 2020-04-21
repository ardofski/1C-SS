package Model.Cards;

import Model.*;
import Model.Effects.ApplyBuff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class Bash extends Card {
    public Bash(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade);
    }

    /*
    Deal 8(10) damage. Apply 2(3) Vulnerable.
     */

    public ArrayList<Effect> getEffects(Enemy e){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Damage(8,e);
        }
        else{
            effect = new Damage(10,e);
        }

        effects.add(effect);

        if( upgrade ){
            effect = new ApplyBuff( new Buff(), e);
        }
        else{
            effect = new ApplyBuff( new Buff(), e);
        }

        effects.add(effect);

        return effects;
    }
}
