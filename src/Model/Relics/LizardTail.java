package Model.Relics;

import Controller.Fight.RelicDependencies;
import Model.Effects.Effect;

import java.util.ArrayList;

public class LizardTail extends Relic{
    public LizardTail(){
        name="LizardTail";
        price=0;
        description="When you would die, heal to 50% of your Max HP instead (works once).";
        type = "Rare";
    }

    @Override
    public ArrayList<Effect> getTurnEffects(RelicDependencies dep) {
        if(dep.getCharacter().getHp()<=0){
            //bunu instantly yapsak daha iyi olur diye stacke eklemek yerine direk yapıyom.
            dep.getCharacter().setHp(dep.getCharacter().getMaxHp()/2);
            dep.getCharacter().getRelics().remove(this);// bu yanllış olabilir
        }
        return null;
    }
}
