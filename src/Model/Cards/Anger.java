package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Card;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Effects.MoveCard;
import Model.Enemy;
import Model.Pile;
import Model.Character;

import java.util.ArrayList;

/**
 * The type Anger.
 */
public class Anger extends Card {
    /**
     * Instantiates a new Anger.
     *
     * @param upgrade the upgrade
     */
    public Anger(boolean upgrade) {
        super(upgrade,true);
        name = "Anger";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal 6 damage. Add a copy of this card to your discard pile.";
        energy = 0;
        if(upgrade) upgrade();
    }

    /**
     * Upgrade.
     */
    public void upgrade(){
        super.upgrade();
        description = "Deal 8 damage. Add a copy of this card to your discard pile.";

    }

    /**
     * Play array list.
     *
     * @param dependencies the dependencies
     * @return the array list
     */
/*
        Deal 6(8) damage. Add a copy of this card to your discard pile.
    */
    public ArrayList<Effect> play(CardDependencies dependencies){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){  //TODO check card upgrade
            effect = new Damage(8,dependencies.getTarget(),dependencies.getCharacter());
        }
        else{
            effect = new Damage(6,dependencies.getTarget(),dependencies.getCharacter());
        }

        effects.add(effect);

        effect = new MoveCard(null, dependencies.getDiscardPile() , new Anger(upgrade) );

        effects.add(effect);

        System.out.println( "Return effects  of anger : "  + effects );
        return effects;
    }

    /*
    //TODO remove this method
    public ArrayList<Effect> getEffects(Enemy e, Pile handPile){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){  //TODO check card upgrade
            effect = new Damage(8,e,null);
        }
        else{
            effect = new Damage(6,e,null);
        }

        effects.add(effect);

        effect = new MoveCard(null,handPile, new Anger(upgrade) );

        effects.add(effect);

        System.out.println( "Return effects  of anger : "  + effects );
        return effects;
    }

     */
}
