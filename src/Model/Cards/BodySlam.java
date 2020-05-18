package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

/**
 * The type Body slam.
 */
public class BodySlam extends Card {
    /**
     * Instantiates a new Body slam.
     *
     * @param upgrade the upgrade
     */
    public BodySlam(boolean upgrade) {
        super(upgrade,true);
        name = "BodySlam";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal damage equal to your current Block.";
        energy = 1;
        if(upgrade) upgrade();
    }

    /**
     * Upgrade.
     */
    public void upgrade(){
        super.upgrade();
        energy = 0;
    }


    /**
     * Play array list.
     *
     * @param dependencies the dependencies
     * @return the array list
     */
/*
        Deal damage equal to your current Block.
     */
    public ArrayList<Effect> play(CardDependencies dependencies){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;

        effect = new Damage(dependencies.getCharacter().getBlock(), dependencies.getTarget() ,dependencies.getCharacter());
        effects.add(effect);

        return effects;
    }

    /**
     * Get effects array list.
     *
     * @param e     the e
     * @param block the block
     * @return the array list
     */
//TODO remove this method
    public ArrayList<Effect> getEffects(Enemy e, int block){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;

        effect = new Damage(block,e,null);
        effects.add(effect);

        return effects;
    }

}
