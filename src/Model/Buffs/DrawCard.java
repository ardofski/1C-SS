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
        super(name);
        this.x = x;
    }

    public ArrayList<Effect> runNextTurn(Pile drawPile, Pile handPile ){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        MoveCard drawCard;
        for( int i = 1 ;  i <= x ; i++ ){
            drawCard = new MoveCard( drawPile, handPile, null);// TODO get the top card of drawpile
            effects.add( drawCard );
        }

        return effects;
    }
}
