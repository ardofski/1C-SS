package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Effects.Block;
import Model.Effects.Effect;
import Model.Effects.MoveCard;

import java.util.ArrayList;

/**
 * The type True grit.
 */
public class TrueGrit extends Card {

    /**
     * Instantiates a new True grit.
     *
     * @param upgrade the upgrade
     */
    public TrueGrit( boolean upgrade) {
        super(upgrade,false);
        name = "TrueGrit";
        rarity = "Common";
        type = "Skill";
        color = "Red";
        description = "Gain 7 Block. Exhaust a random card from your hand.";
        energy = 1;
        if(upgrade) upgrade();
    }

    /**
     * Upgrade.
     */
    public void upgrade(){
        super.upgrade();
        description = "Gain 9 Block. Exhaust a card from your hand.";
    }

    /**
     * Play array list.
     *
     * @param dep the dep
     * @return the array list
     */
/*
        Gain 7(9) Block. Exhaust a random(not random) card from your hand.
    */
    public ArrayList<Effect> play(CardDependencies dep){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Block(7,dep.getCharacter());
        }
        else{
            effect = new Block(9,dep.getCharacter());
        }

        effects.add(effect);

        int cardIndex = (int)( (Math.random())*(dep.getHandPile().getCards().size()) );
        Card exCard = dep.getHandPile().getCards().get( cardIndex );
        effect = new MoveCard( dep.getHandPile(),dep.getExhaustPile(), exCard );
        effects.add(effect);
        //TODO check if upgraded

        return effects;
    }

}
