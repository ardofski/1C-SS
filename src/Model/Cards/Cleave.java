package Model.Cards;

import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class Cleave extends Card {
    public Cleave(boolean upgrade) {
        super(upgrade,false);
        name = "Cleave";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal 8 damage to ALL enemies.";
        energy = 1;
    }
    public void upgrade(){
        super.upgrade();
        description = "Deal 11 damage to ALL enemies.";
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
