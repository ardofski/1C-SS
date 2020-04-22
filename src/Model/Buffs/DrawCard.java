package Model.Buffs;

import Controller.Fight.EffectHandler;
import Model.Buff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Effects.MoveCard;
import Model.Pile;

import java.util.ArrayList;
import java.util.Stack;

public class DrawCard extends Buff {
    int x;
    public DrawCard(String name, int x) {
        super(name,1);
        this.x = x;
    }

    public ArrayList<Effect> runNextTurn(Pile drawPile, Pile handPile ){
        remainingTurn--;
        ArrayList<Effect> effects = new ArrayList<Effect>();
        MoveCard drawCard;
        for( int i = 1 ;  i <= x ; i++ ){
            //get the top card of drawpile
            drawCard = new MoveCard( drawPile, handPile, drawPile.getTop() );
            effects.add( drawCard );
        }

        return effects;
    }


}
