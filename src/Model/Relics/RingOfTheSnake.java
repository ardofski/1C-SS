package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Effects.DrawCard;
import Model.Effects.Effect;

import java.util.ArrayList;

/**
 * The type Ring of the snake.
 */
public class RingOfTheSnake extends Relic {

    /**
     * Instantiates a new Ring of the snake.
     */
    public RingOfTheSnake(){
        name = "RingOfTheSnake";
        description = "At the start of each combat, draw 2 additional cards.";
        type = "Starter";
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
        effects.add( new DrawCard() );
        effects.add( new DrawCard() );
        return effects;
    }
}
