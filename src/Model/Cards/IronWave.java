package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Iron wave.
 */
public class IronWave extends Card {
    /**
     * Instantiates a new Iron wave.
     *
     * @param upgrade the upgrade
     */
    public IronWave(boolean upgrade) {
        super( upgrade,true);
        name = "IronWave";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Gain 5 Block. Deal 5 damage.";
        energy = 1;
        if(upgrade) upgrade();
    }

    /**
     * Upgrade.
     */
    public void upgrade(){
        super.upgrade();
        description = "Gain 7 Block. Deal 7 damage.";
    }

    /**
     * Play array list.
     *
     * @param dep the dep
     * @return the array list
     */
/*
        Gain 5(7) Block. Deal 5(7) damage
    */
    public ArrayList<Effect> play(CardDependencies dep){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Block(5,dep.getCharacter());
            effects.add(effect);
            effect = new Damage(5,dep.getTarget(),dep.getCharacter() );
            effects.add(effect);
        }
        else{
            effect = new Block(7,dep.getCharacter());
            effects.add(effect);
            effect = new Damage(7,dep.getTarget(), dep.getCharacter() );
            effects.add(effect);
        }

        return effects;
    }

}
