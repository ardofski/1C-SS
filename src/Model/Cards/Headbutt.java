package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Effects.MoveCard;
import Model.Enemy;
import Model.Pile;

import java.util.ArrayList;

/**
 * The type Headbutt.
 */
public class Headbutt extends Card {
    /**
     * Instantiates a new Headbutt.
     *
     * @param upgrade the upgrade
     */
    public Headbutt(boolean upgrade) {
        super( upgrade,true);
        name = "Headbutt";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal 9 damage. Place a card from your discard pile on top of your draw pile.";
        energy = 1;
        if(upgrade) upgrade();
    }

    /**
     * Upgrade.
     */
    public void upgrade(){
        super.upgrade();
        description = "Deal 12 damage. Place a card from your discard pile on top of your draw pile.";
    }

    /**
     * Play array list.
     *
     * @param dep the dep
     * @return the array list
     */
/*
        Deal 9(12) damage. Place a card from your discard pile on top of your draw pile.
     */
        /*
        Gain 5(8) Block.
    */
    public ArrayList<Effect> play(CardDependencies dep){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Block(12,dep.getTarget());
        }
        else{
            effect = new Damage(9,dep.getTarget(),dep.getCharacter() );
        }
        effects.add(effect);

        effect = new MoveCard(dep.getDiscardPile(),dep.getDrawPile(),null ); //TODO get top card of discard pile
        effects.add(effect);

        return effects;
    }

}
