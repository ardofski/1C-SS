package Model.Cards;

import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class IronWave extends Card {
    public IronWave(boolean upgrade) {
        super( upgrade,true);
        name = "IronWave";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Gain 5 Block. Deal 5 damage.";
        energy = 1;
    }
    public void upgrade(){
        super.upgrade();
        description = "Gain 7 Block. Deal 7 damage.";
    }

    /*
        Gain 5(7) Block. Deal 5(7) damage
    */

    public ArrayList<Effect> getEffects(Enemy e){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Block(5,null);
            effects.add(effect);
            effect = new Damage(5,e);
            effects.add(effect);
        }
        else{
            effect = new Block(7,null);
            effects.add(effect);
            effect = new Damage(7,e);
            effects.add(effect);
        }

        return effects;
    }
}
