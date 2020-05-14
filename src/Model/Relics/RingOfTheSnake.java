package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Effects.DrawCard;
import Model.Effects.Effect;

import java.util.ArrayList;

public class RingOfTheSnake extends Relic {

    public RingOfTheSnake(){
        name = "RingOfTheSnake";
        description = "At the start of each combat, draw 2 additional cards.";
        type = "Starter";
        price = 0;
    }
    @Override
    public ArrayList<Effect> getBeginingOfFightEffects(RelicDependencies dep) {
        ArrayList<Effect> effects = new ArrayList<>();
        effects.add( new DrawCard() );
        effects.add( new DrawCard() );
        return effects;
    }
}
