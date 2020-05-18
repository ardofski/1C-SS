package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Effects.Damage;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Heavy blade.
 */
public class HeavyBlade extends Card {

    /**
     * Instantiates a new Heavy blade.
     *
     * @param upgrade the upgrade
     */
    public HeavyBlade( boolean upgrade) {
        super( upgrade,true);
        name = "HeavyBlade";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal 14 damage. Strength affects Heavy Blade 3 times.";
        energy = 2;
        if(upgrade) upgrade();
    }

    /**
     * Upgrade.
     */
    public void upgrade(){
        super.upgrade();
        description = "Deal 14 damage. Strength affects Heavy Blade 5 times.";
    }

    /**
     * Play array list.
     *
     * @param dep the dep
     * @return the array list
     */
/*
        Deal 14 damage. Strength affects Heavy Blade 3(5) times.
    */
    public ArrayList<Effect> play(CardDependencies dep){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            //TODO check strenght of character
            effect = new Damage(14,dep.getTarget(),dep.getCharacter());
        }
        else{
            //TODO check strenght of character
            effect = new Damage(14,dep.getTarget(),dep.getCharacter());
        }

        effects.add(effect);

        return effects;
    }

}
