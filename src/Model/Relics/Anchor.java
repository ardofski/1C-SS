package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Effects.Block;
import Model.Effects.DrawCard;
import Model.Effects.Effect;

import java.util.ArrayList;

public class Anchor extends Relic {

    public Anchor(){
        name = "Anchor";
        description = "Start each combat with 10 Block.";
        type = "Common";
        price = 0;
    }

    @Override
    public ArrayList<Effect> getBeginingOfFightEffects(RelicDependencies dep) {
        ArrayList<Effect> effects = new ArrayList<>();
        effects.add( new Block(10,dep.getCharacter()) );
        return effects;
    }
}
