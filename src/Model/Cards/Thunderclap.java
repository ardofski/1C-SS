package Model.Cards;

import Model.Buff;
import Model.Buffs.Vulnerable;
import Model.Card;
import Model.Effects.ApplyBuff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class Thunderclap extends Card {
    public Thunderclap( boolean upgrade) {
        super( upgrade,false);
        name = "Thunderclap";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal 4 damage and apply 1 Vulnerable to ALL enemies.";
        energy = 1;
    }
    public void upgrade(){
        super.upgrade();
        description = "Deal 7 damage and apply 1 Vulnerable to ALL enemies.";
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
            effect = new ApplyBuff(new Vulnerable("Vulnerable",1), enemies.get(i) );
            effects.add(effect);
        }

        return effects;
    }
}
