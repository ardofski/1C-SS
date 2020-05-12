package Model.Cards;

import Model.Buff;
import Model.Buffs.Weak;
import Model.Card;
import Model.Effects.ApplyBuff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class Clothesline extends Card {
    public Clothesline(boolean upgrade) {
        super(upgrade,true);
        name = "Clothesline";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal 12 damage. Apply 2 Weak.";
        energy = 2;
    }
    public void upgrade(){
        super.upgrade();
        description = "Deal 14 damage. Apply 3 Weak.";
    }


    /*
        Deal 12(14) damage. Apply 2(3) Weak.
    */
    public ArrayList<Effect> getEffects(Enemy e){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){ //TODO check this card is upgraded
            effect = new Damage( 14,e);
            effects.add(effect);
            effect = new ApplyBuff(new Weak("Weak",3),e );
        }
        else{
            effect = new Damage( 12,e);
            effects.add(effect);
            effect = new ApplyBuff(new Weak("Weak",2),e );
        }
        effects.add(effect);

        

        return effects;
    }
}
