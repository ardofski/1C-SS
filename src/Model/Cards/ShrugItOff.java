package Model.Cards;

import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Effects.MoveCard;
import Model.Enemy;
import Model.Pile;

import java.util.ArrayList;

public class ShrugItOff extends Card {
    public ShrugItOff( boolean upgrade) {
        super(upgrade,false);
        name = "ShrugItOff";
        rarity = "Common";
        type = "Skill";
        color = "Red";
        description = "Gain 8 Block. Draw 1 card.";
        energy = 1;
    }
    public void upgrade(){
        super.upgrade();
        description = "Gain 11 Block. Draw 1 card.";
    }
    /*
        Gain 8(11) Block. Draw 1 card.
    */
    public ArrayList<Effect> getEffects(Enemy e, Pile drawPile,Pile handPile){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;
        if( upgrade ){
            effect = new Block(11,null);
        }
        else{
            effect = new Damage(8,null);
        }
        effects.add(effect);

        //take the top card of draw pile to pass.
        effect = new MoveCard(drawPile,handPile,drawPile.getTop() );
        effects.add(effect);

        return effects;
    }
}
