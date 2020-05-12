package Model.Cards;

import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;
import Model.Character;
import Model.Pile;

import java.util.ArrayList;

public class PerfectedStrike extends Card {
    public PerfectedStrike( boolean upgrade) {
        super(upgrade,true);
        name = "PerfectedStrike";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal 6 damage. Deals an additional 2 damage for ALL of your cards containing Strike.";
        energy = 2;
    }
    public void upgrade(){
        super.upgrade();
        description = "Deal 6 damage. Deals an additional 3 damage for ALL of your cards containing Strike.";
    }

    /*
        Deal 6 damage. Deals an additional 2(3) damage for ALL of your cards containing "Strike".
    */
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
