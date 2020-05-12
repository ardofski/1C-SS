package Model.Cards;

import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Effects.UpgradeCard;
import Model.Enemy;
import Model.Pile;

import java.util.ArrayList;

public class BodySlam extends Card {
    public BodySlam(String name, String rarity, String type, String color, String description, int energy, boolean upgrade) {
        super(name, rarity, type, color, description, energy, upgrade,true);
        name = "BodySlam";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal damage equal to your current Block.";
        energy = 1;
    }


    /*
        Deal damage equal to your current Block.
     */

    public ArrayList<Effect> getEffects(Enemy e, int block){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;

        effect = new Damage(block,e,null);
        effects.add(effect);

        return effects;
    }

}
