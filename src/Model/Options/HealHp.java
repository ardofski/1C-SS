package Model.Options;

import Model.Character;
import Model.Options.Option;

public class HealHp extends Option {

    public HealHp()
    {
        this.description = "Heal 1/3 of your max HP.";
    }

    @Override
    public void applyOption(Character character) {
        character.setHp((int) (character.getHp() +  character.getMaxHp()/3));
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public String toString() {
        return "description: " + this.description;
    }
}
