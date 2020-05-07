package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;
import Model.Character;
import Model.Pile;

import java.util.ArrayList;

public class PerfectedStrike extends Card {
    public PerfectedStrike(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade,true);
    }

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

        effect = new Damage(6+ addPerStrike * numOfStrikes, dep.getTarget() );

        effects.add(effect);

        return effects;
    }


    //TODO remove this method
    public ArrayList<Effect> getEffects(Enemy e, Character character){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;

        int numOfStrikes,addPerStrike;
        numOfStrikes = 0;

        //Read number of cards containing strike
        Pile deck = character.getDeck();
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

        effect = new Damage(6+ addPerStrike * numOfStrikes, e);

        effects.add(effect);

        return effects;
    }
}
