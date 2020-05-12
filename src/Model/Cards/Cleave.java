package Model.Cards;

import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class Cleave extends Card {
    public Cleave(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade,false);
        name = "Cleave";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal 8(11) damage to ALL enemies.";
        energy = 1;
    }

    /*
        Deal 8(11) damage to ALL enemies.
    */
    public ArrayList<Effect> getEffects(ArrayList<Enemy> enemies){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            for( int i = 0 ; i < enemies.size(); i++){
                effect = new Damage(11,enemies.get(i),null );
                effects.add( effect );
            }
        }
        else{
            for( int i = 0 ; i < enemies.size(); i++){
                effect = new Damage(8,enemies.get(i),null );
                effects.add( effect );
            }
        }

        return effects;
    }
}
