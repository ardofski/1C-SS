package Model.Cards;

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
        description = "Gain 5 Block. Upgrade a(ALL) card(s) in your hand for the rest of combat.";
        energy = 1;
    }

    /*
        Gain 5 Block. Upgrade a(ALL) card(s) in your hand for the rest of combat.
     */

    public ArrayList<Effect> getEffects(Enemy e, Pile handPile){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;

        effect = new Block(5,null);
        effects.add(effect);

        if( upgrade ){

            //TODO

        }
        else{
            effect = new UpgradeCard( null ); //TODO
        }

        effects.add(effect);

        return effects;
    }
}
