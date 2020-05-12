package Model.Cards;

import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class Havoc extends Card {
    public Havoc(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade,false);
        name = "Havoc";
        rarity = "Common";
        type = "Skill";
        color = "Red";
        description = "Play the top card of your draw pile and Exhaust it.";
        energy = 1;
    }

    /*
    Play the top card of your draw pile and Exhaust it.
    */

    public ArrayList<Effect> getEffects(Enemy e){
        //TODO
        return null;
    }
}
