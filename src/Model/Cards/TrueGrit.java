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

public class TrueGrit extends Card {

    public TrueGrit( boolean upgrade) {
        super(upgrade,false);
        name = "TrueGrit";
        rarity = "Common";
        type = "Skill";
        color = "Red";
        description = "Gain 7 Block. Exhaust a random card from your hand.";
        energy = 1;
    }
    public void upgrade(){
        super.upgrade();
        description = "Gain 9 Block. Exhaust a card from your hand.";
    }

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
