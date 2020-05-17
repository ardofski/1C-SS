package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class Havoc extends Card {
    public Havoc(boolean upgrade) {
        super( upgrade,false);
        name = "Havoc";
        rarity = "Common";
        type = "Skill";
        color = "Red";
        description = "Play the top card of your draw pile and Exhaust it.";
        energy = 1;
        if(upgrade) upgrade();
    }
    public void upgrade(){
        super.upgrade();
        energy = 0;
    }

    /*
    Play the top card of your draw pile and Exhaust it.
    */

    public ArrayList<Effect> play(CardDependencies dep){
        //TODO
        return null;
    }

    //todo remove this method
    public ArrayList<Effect> getEffects(Enemy e){
        //TODO
        return null;
    }
}
