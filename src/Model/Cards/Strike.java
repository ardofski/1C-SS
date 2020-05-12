package Model.Cards;

import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class Strike extends Card {
    public Strike( boolean upgrade) {
        super( upgrade,true);
        name = "Strike";
        rarity = "Starter";
        type = "Attack";
        color = "Red";
        description = "Deal 6 damage.";
        energy = 1;
    }
    public void upgrade(){
        super.upgrade();
        description = "Deal 9 damage.";
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
