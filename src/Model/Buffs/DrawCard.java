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
    public DrawCard( int x) {
        super("DrawCard",1);
        this.x = x;
        stackProperty = INTENSITY;
    }

    @Override
    public ArrayList<Effect> getNextTurnEffects(BuffDependencies dep) {
        ArrayList<Effect> effects = new ArrayList<Effect>();
        for( int i = 1 ;  i <= x ; i++ ){
            //get the top card of drawpile
            effects.add( new Model.Effects.DrawCard() );
        }
        setX(0);
        return effects;

    }

    @Override
    public ArrayList<Effect> getTurnEffects(BuffDependencies dep) {
        return super.getTurnEffects(dep);
    }
}
