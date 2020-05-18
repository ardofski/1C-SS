package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The type Cleave.
 */
public class Cleave extends Card {
    /**
     * Instantiates a new Cleave.
     *
     * @param upgrade the upgrade
     */
    public Cleave(boolean upgrade) {
        super(upgrade,false);
        name = "Cleave";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal 8 damage to ALL enemies.";
        energy = 1;
        if(upgrade) upgrade();
    }

    /**
     * Upgrade.
     */
    public void upgrade(){
        super.upgrade();
        description = "Deal 11 damage to ALL enemies.";
    }

    /**
     * Play array list.
     *
     * @param dep the dep
     * @return the array list
     */
/*
        Deal 8(11) damage to ALL enemies.
    */
    public ArrayList<Effect> play(CardDependencies dep){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        ArrayList<Enemy> enemies = dep.getEnemies();
        if( upgrade ){
            for( int i = 0 ; i < enemies.size(); i++){
                effect = new Damage(11,enemies.get(i),dep.getCharacter() );
                effects.add( effect );
            }
        }
        else{
            for( int i = 0 ; i < enemies.size(); i++){
                effect = new Damage(8,enemies.get(i),dep.getCharacter() );
                effects.add( effect );
            }
        }

        return effects;
    }


    /**
     * Get effects array list.
     *
     * @param enemies the enemies
     * @return the array list
     */
//TODO remove this method
    public ArrayList<Effect> getEffects(ArrayList<Enemy> enemies){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            for( int i = 0 ; i < enemies.size(); i++){
                effect = new Damage(11,enemies.get(i),null );
                effects.add( effect );
            }
        }
        else{
            for( int i = 0 ; i < enemies.size(); i++){
                effect = new Damage(8,enemies.get(i),null );
                effects.add( effect );
            }
        }

        return effects;
    }
}
