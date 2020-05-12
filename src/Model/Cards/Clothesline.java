package Model.Cards;

import Controller.Fight.CardDependencies;
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
    public Clothesline(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade,true);
    }


    /*
        Deal 12(14) damage. Apply 2(3) Weak.
    */
    public ArrayList<Effect> play(CardDependencies dep){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){ //check this card is upgraded
            effect = new Damage( 14,dep.getTarget(),dep.getCharacter());
            effects.add(effect);
            effect = new ApplyBuff(new Weak("Weak",3),dep.getTarget() );
        }
        else{
            effect = new Damage( 12,dep.getTarget(),dep.getCharacter());
            effects.add(effect);
            effect = new ApplyBuff(new Weak("Weak",2),dep.getTarget() );
        }
        effects.add(effect);

        return effects;
    }

}
