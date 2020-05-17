package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Buff;
import Model.Card;
import Model.Effects.ApplyBuff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.*;

import java.util.ArrayList;

public class Defend extends Card {

    public Defend(boolean upgrade) {
        super(upgrade,false);
        name = "Defend";
        rarity = "Starter";
        type = "Skill";
        color = "Red";
        description = "Gain 5 Block.";
        energy = 1;
        if(upgrade) upgrade();
    }
    public void upgrade(){
        super.upgrade();
        description = "Gain 8 Block.";
    }

    /*
        Gain 5(8) Block.
    */
    public ArrayList<Effect> play(CardDependencies dep){

        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Block(8,dep.getCharacter());
        }
        else{
            effect = new Block(5,dep.getCharacter());
        }

        effects.add(effect);

        return effects;
    }


}
