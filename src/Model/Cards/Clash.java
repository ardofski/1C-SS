package Model.Cards;

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
    }
    public void upgrade(){
        super.upgrade();
        description = "Can only be played if every card in your hand is an Attack. Deal 18 damage.";
    }

    /*
    Can only be played if every card in your hand is an Attack. Deal 14(18) damage.
    */

    public boolean isPlayable(Pile handPile){
        ArrayList<Card> cards = handPile.getCards();
        for( int i = 0 ; i < cards.size() ; i++){
            if( !cards.get(i).getType().equals("Attack")){
                return false;
            }
        }
        return true;
    }

    public ArrayList<Effect> getEffects(Enemy e){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Damage(18,e,null);
        }
        else{
            effect = new Damage(14,e,null);
        }

        effects.add(effect);
        return effects;
    }
}
