package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Buff;
import Model.Buffs.Vulnerable;
import Model.Card;
import Model.Effects.ApplyBuff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

/**
 * The type Thunderclap.
 */
public class Thunderclap extends Card {
    /**
     * Instantiates a new Thunderclap.
     *
     * @param upgrade the upgrade
     */
    public Thunderclap( boolean upgrade) {
        super( upgrade,false);
        name = "Thunderclap";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal 4 damage and apply 1 Vulnerable to ALL enemies.";
        energy = 1;
        if(upgrade) upgrade();
    }

    /**
     * Upgrade.
     */
    public void upgrade(){
        super.upgrade();
        description = "Deal 7 damage and apply 1 Vulnerable to ALL enemies.";
    }

    /**
     * Play array list.
     *
     * @param dep the dep
     * @return the array list
     */
/*
        Deal 4(7) damage and apply 1 Vulnerable to ALL enemies.
    */
    public ArrayList<Effect> play(CardDependencies dep){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Damage(4,dep.getTarget(),dep.getCharacter());
        }
        else{
            effect = new Damage(7,dep.getTarget(),dep.getCharacter());
        }

        effects.add(effect);

        for( int i = 0 ; i < dep.getEnemies().size();i++){
            effect = new ApplyBuff(new Vulnerable(1), dep.getEnemies().get(i) );
            effects.add(effect);
        }

        return effects;
    }

}
