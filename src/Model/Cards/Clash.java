package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.*;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;

import java.util.ArrayList;

public class Clash extends Card {
    public Clash(boolean upgrade) {
        super(upgrade,true);
        name = "Clash";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Can only be played if every card in your hand is an Attack. Deal 14 damage.";
        energy = 0;
        if(upgrade) upgrade();
    }
    public void upgrade(){
        super.upgrade();
        description = "Can only be played if every card in your hand is an Attack. Deal 18 damage.";
    }

    /*
    Can only be played if every card in your hand is an Attack. Deal 14(18) damage.
    */

    public ArrayList<Effect> play(CardDependencies dependencies){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Damage(18, dependencies.getTarget(),dependencies.getCharacter());
        }
        else{
            effect = new Damage(14,dependencies.getTarget(),dependencies.getCharacter());
        }

        effects.add(effect);
        return effects;
    }

    @Override
    public boolean isPlayable(CardDependencies dep) {
        if(! super.isPlayable(dep) )return false;
        ArrayList<Card> cards = dep.getHandPile().getCards();
        for( int i = 0 ; i < cards.size() ; i++){
            if( !cards.get(i).getType().equals("Attack")){
                return false;
            }
        }
        return true;
    }


}
