package Model.Cards;

import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class Strike extends Card {
    public Strike(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade,true);
        name = "Strike";
        rarity = "Starter";
        type = "Attack";
        color = "Red";
        description = "Deal 6(9) damage.";
        energy = 1;
    }

    /*
        Deal 6(9) damage.
    */
    public ArrayList<Effect> getEffects(Enemy e){

        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Damage(6,e,null);
        }
        else{
            effect = new Damage(9,e,null);
        }
        effects.add(effect);
        System.out.println("************************IN STRIKE CLASS*************************");
        return effects;
    }
}
