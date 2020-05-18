package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Buffs.Weak;
import Model.Effects.ApplyBuff;
import Model.Effects.Damage;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Clothesline.
 */
public class Clothesline extends Card {
    /**
     * Instantiates a new Clothesline.
     *
     * @param upgrade the upgrade
     */
    public Clothesline(boolean upgrade) {
        super(upgrade,true);
        name = "Clothesline";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal 12 damage. Apply 2 Weak.";
        energy = 2;
        if(upgrade) upgrade();
    }

    /**
     * Upgrade.
     */
    public void upgrade(){
        super.upgrade();
        description = "Deal 14 damage. Apply 3 Weak.";
    }


    /**
     * Play array list.
     *
     * @param dep the dep
     * @return the array list
     */
/*
        Deal 12(14) damage. Apply 2(3) Weak.
    */
    public ArrayList<Effect> play(CardDependencies dep){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){ //check this card is upgraded
            effect = new Damage( 14,dep.getTarget(),dep.getCharacter());
            effects.add(effect);
            effect = new ApplyBuff(new Weak(3),dep.getTarget() );
        }
        else{
            effect = new Damage( 12,dep.getTarget(),dep.getCharacter());
            effects.add(effect);
            effect = new ApplyBuff(new Weak(2),dep.getTarget() );
        }
        effects.add(effect);

        return effects;
    }

}
