package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Effects.UpgradeCard;
import Model.Enemy;
import Model.Pile;

import java.util.ArrayList;

public class BodySlam extends Card {
    public BodySlam(boolean upgrade) {
        super(upgrade,true);
        name = "BodySlam";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal damage equal to your current Block.";
        energy = 1;
        if(upgrade) upgrade();
    }
    public void upgrade(){
        super.upgrade();
        energy = 0;
    }


    /*
        Deal damage equal to your current Block.
     */
    public ArrayList<Effect> play(CardDependencies dependencies){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;

        effect = new Damage(dependencies.getCharacter().getBlock(), dependencies.getTarget() ,null);
        effects.add(effect);

        return effects;
    }

    //TODO remove this method
    public ArrayList<Effect> getEffects(Enemy e, int block){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;

        effect = new Damage(block,e,null);
        effects.add(effect);

        return effects;
    }

}
