package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Buff;
import Model.Buffs.Strength;
import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;
import Model.Character;

import java.util.ArrayList;

public class HeavyBlade extends Card {

    public HeavyBlade( boolean upgrade) {
        super( upgrade,true);
        name = "HeavyBlade";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal 14 damage. Strength affects Heavy Blade 3 times.";
        energy = 2;
        if(upgrade) upgrade();
    }
    public void upgrade(){
        super.upgrade();
        description = "Deal 14 damage. Strength affects Heavy Blade 5 times.";
    }

    /*
        Deal 14 damage. Strength affects Heavy Blade 3(5) times.
    */
    public ArrayList<Effect> play(CardDependencies dep){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            //TODO check strenght of character
            effect = new Damage(14,dep.getTarget(),dep.getCharacter());
        }
        else{
            //TODO check strenght of character
            effect = new Damage(14,dep.getTarget(),dep.getCharacter());
        }

        effects.add(effect);

        return effects;
    }

}
