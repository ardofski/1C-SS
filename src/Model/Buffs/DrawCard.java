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

/**
 * The type Draw card.
 */
public class DrawCard extends Buff {
    /**
     * The X.
     */
    int x;

    /**
     * Instantiates a new Draw card.
     *
     * @param x the x
     */
    public DrawCard( int x) {
        super("DrawCard",1);
        this.x = x;
        stackProperty = INTENSITY;
        description = "Draw X additional cards next turn.";
    }

    /**
     * Gets next turn effects.
     *
     * @param dep the dep
     * @return the next turn effects
     */
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

    /**
     * Gets turn effects.
     *
     * @param dep the dep
     * @return the turn effects
     */
    @Override
    public ArrayList<Effect> getTurnEffects(BuffDependencies dep) {
        return super.getTurnEffects(dep);
    }
}
