package Model.Cards;

import Model.*;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;

import java.util.ArrayList;

public class Clash extends Card {
    public Clash(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade);
    }

    /*
    Can only be played if every card in your hand is an Attack. Deal 14(18) damage.
    */

    public boolean isPlayable(){
        return true;
    }

    public ArrayList<Effect> getEffects(Enemy e){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Block(18,null);
        }
        else{
            effect = new Damage(14,null);
        }

        effects.add(effect);
        return effects;
    }
}
