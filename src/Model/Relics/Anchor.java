package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Effects.Block;
import Model.Effects.DrawCard;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Anchor.
 */
public class Anchor extends Relic {

    /**
     * Instantiates a new Anchor.
     */
    public Anchor(){
        name = "Anchor";
        description = "Start each combat with 10 Block.";
        type = "Common";
        price = 0;
    }

    /**
     * Gets begining of fight effects.
     *
     * @param dep the dep
     * @return the begining of fight effects
     */
    @Override
    public ArrayList<Effect> getBeginingOfFightEffects(RelicDependencies dep) {
        ArrayList<Effect> effects = new ArrayList<>();
        effects.add( new Block(10,dep.getCharacter()) );
        System.out.println( "inside anchor, effects are " + effects);
        return effects;
    }
}
