package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Pile;

import java.util.ArrayList;

/**
 * The type Perfected strike.
 */
public class PerfectedStrike extends Card {
    /**
     * Instantiates a new Perfected strike.
     *
     * @param upgrade the upgrade
     */
    public PerfectedStrike( boolean upgrade) {
        super(upgrade,true);
        name = "PerfectedStrike";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal 6 damage. Deals an additional 2 damage for ALL of your cards containing Strike.";
        energy = 2;
        if(upgrade) upgrade();
    }

    /**
     * Upgrade.
     */
    public void upgrade(){
        super.upgrade();
        description = "Deal 6 damage. Deals an additional 3 damage for ALL of your cards containing Strike.";
    }

    /**
     * Play array list.
     *
     * @param dep the dep
     * @return the array list
     */
/*
        Deal 6 damage. Deals an additional 2(3) damage for ALL of your cards containing "Strike".
    */
    public ArrayList<Effect> play(CardDependencies dep){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;

        int numOfStrikes,addPerStrike;
        numOfStrikes = 0;

        //Read number of cards containing strike
        Pile deck = dep.getCharacter().getDeck();
        ArrayList<Card> cards = deck.getCards();
        for( int i = 0 ; i < cards.size(); i++ ){
            if( cards.get(i).getType() == "Strike" ){
                numOfStrikes++;
            }
        }

        if(upgrade){
            addPerStrike = 3;
        }
        else{
            addPerStrike = 2;
        }

        effect = new Damage(6+ addPerStrike * numOfStrikes, dep.getTarget(),dep.getCharacter() );

        effects.add(effect);

        return effects;
    }

}
