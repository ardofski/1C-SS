package Model.Cards;

import Model.Buff;
import Model.Card;
import Model.Effects.ApplyBuff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class Thunderclap extends Card {
    public Thunderclap(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade);
    }

    /*
        Deal 4(7) damage and apply 1 Vulnerable to ALL enemies.
    */
    public ArrayList<Effect> getEffects(Enemy e, ArrayList<Enemy> enemies){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Damage(4,e);
        }
        else{
            effect = new Damage(7,e);
        }

        effects.add(effect);

        for( int i = 0 ; i < enemies.size();i++){
            effect = new ApplyBuff(new Buff(), enemies.get(i) ); //TODO init vulnerable buff
            effects.add(effect);
        }

        return effects;
    }
}
