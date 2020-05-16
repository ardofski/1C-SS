package Model.Options;

import Model.Character;
import Model.Options.Option;

public class IncrementMaxHp extends Option {
    public IncrementMaxHp()
    {
        this.description = "Increment Max Hp by 5";
    }
    @Override
    public void applyOption(Character character) {
        character.setMaxHp(character.getMaxHp()+5);
    }
    @Override
    public String toString() {
        return "description: " + this.description;
    }
}
