package Model.Cards;

import Model.Card;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Effects.MoveCard;
import Model.Enemy;
import Model.Pile;
import Model.Character;

import java.util.ArrayList;

public class Anger extends Card {
    public Anger(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade);
    }

    /*
        Deal 6(8) damage. Add a copy of this card to your discard pile.
    */

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

        effect = new MoveCard(null,handPile, new Anger(name, rarity, type, color, description, energy, upgrade) );

        effects.add(effect);

        System.out.println( "Return effects  of anger : "  + effects );
        return effects;
    }
}
