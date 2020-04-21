package Model.Cards;

import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class SwordBoomerang extends Card {
    public SwordBoomerang(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade);
    }

    /*
        Deal 3 damage to a random enemy 3(4) times.
    */
    public ArrayList<Effect> getEffects(ArrayList<Enemy> enemies ){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;

        Enemy enemy = null; //TODO initilize it randomly

        if( upgrade ){
            for( int i = 1 ; i <= 4; i++){
                effect = new Damage(3,enemy);
                effects.add(effect);
            }
        }
        else{
            for( int i = 1 ; i <= 3; i++){
                effect = new Damage(3,enemy);
                effects.add(effect);
            }
        }

        return effects;
    }
}