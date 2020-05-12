package Model.Cards;

import Model.*;
import Model.Buffs.Vulnerable;
import Model.Effects.ApplyBuff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class Bash extends Card {
    public Bash(boolean upgrade) {
        super(upgrade,true);
        name = "Bash";
        rarity = "Starter";
        type = "Attack";
        color = "Red";
        description = "Deal 8(10) damage. Apply 2(3) Vulnerable.";
        energy = 2;
    }

    /*
    Deal 8(10) damage. Apply 2(3) Vulnerable.
     */

    public ArrayList<Effect> getEffects(Enemy e){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( this.upgrade ){  // check if card is upgraded
            effect = new Damage(10,e,null);
        }
        else{
            effect = new Damage(8,e,null);
        }

        effects.add(effect);

        if( upgrade ){ //T check if card is upgraded
            effect = new ApplyBuff( new Vulnerable("Vulnerable",3), e);
        }
        else{
            effect = new ApplyBuff( new Vulnerable("Vulnerable",2), e);
        }

        effects.add(effect);

        return effects;
    }
}
