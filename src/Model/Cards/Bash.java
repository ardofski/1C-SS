package Model.Cards;

import Controller.Fight.CardDependencies;
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
        description = "Deal 8 damage. Apply 2 Vulnerable.";
        energy = 2;
    }
    public void upgrade(){
        super.upgrade();
        description = "Deal 10 damage. Apply 3 Vulnerable.";
    }

    /*
    Deal 8(10) damage. Apply 2(3) Vulnerable.
     */
    public ArrayList<Effect> play(CardDependencies dependencies){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( this.upgrade ){  // check if card is upgraded
            effect = new Damage(10, dependencies.getTarget(),dependencies.getCharacter());
        }
        else{
            effect = new Damage(8,dependencies.getTarget(),dependencies.getCharacter());
        }

        effects.add(effect);

        if( upgrade ){ //TODO check if card is upgraded
            effect = new ApplyBuff( new Vulnerable(3), dependencies.getTarget());
        }
        else{
            effect = new ApplyBuff( new Vulnerable(2), dependencies.getTarget());
        }

        effects.add(effect);

        return effects;
    }


    //TODO remove this method
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
            effect = new ApplyBuff( new Vulnerable(3), e);
        }
        else{
            effect = new ApplyBuff( new Vulnerable(2), e);
        }

        effects.add(effect);

        return effects;
    }
}
