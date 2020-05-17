package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Card;
import Model.Effects.*;
import Model.Enemy;
import Model.Pile;

import java.util.ArrayList;

public class Armaments extends Card {


    public Armaments(boolean upgrade) {
        super(upgrade,false);
        name = "Armaments";
        rarity = "Common";
        type = "Skill";
        color = "Red";
        description = "Gain 5 Block. Upgrade a card in your hand for the rest of combat.";
        energy = 1;
        if(upgrade) upgrade();
    }
    public void upgrade(){
        super.upgrade();
        description = "Gain 5 Block. Upgrade ALL cards in your hand for the rest of combat.";
    }

    /*
        Gain 5 Block. Upgrade a(ALL) card(s) in your hand for the rest of combat.
     */

    public ArrayList<Effect> play(CardDependencies dependencies){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;

        effect = new Block(5,dependencies.getCharacter());
        effects.add(effect);
        int handSize = dependencies.getHandPile().getCards().size();
        if( upgrade ){

            for( int i = 0 ; i < handSize ; i++ ){
                effects.add( new UpgradeCard(dependencies.getHandPile().getCards().get(i) ) );
            }

        }
        else{
            int index = (int) (Math.random()*handSize);
            effect = new UpgradeCard( dependencies.getHandPile().getCards().get(index) );
            effects.add(effect);
        }
        return effects;


    }

}
