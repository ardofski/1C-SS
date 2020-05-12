package Model.Buffs;

import Controller.Fight.BuffDependencies;
import Controller.Fight.EffectHandler;
import Model.Buff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Effects.MoveCard;
import Model.Pile;
import Model.Effects.*;

import java.util.ArrayList;
import java.util.Stack;

public class DrawCard extends Buff {
    int x;
    public DrawCard(String name, int x) {
        super(name,1);
        this.x = x;
    }

    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
        ArrayList<Effect> effects = new ArrayList<Effect>();
        for( int i = 1 ;  i <= x ; i++ ){
            //get the top card of drawpile
            effects.add( new Model.Effects.DrawCard() );
        }
        x--;
        return effects;

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
