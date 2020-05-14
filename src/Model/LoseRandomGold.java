package Model;

public class LoseRandomGold extends Option {

    public LoseRandomGold()
    {
        this.description = "Lose random amount of Gold (40 - 110)";
    }
    @Override
    public void applyOption(Character character) {
        int gold = 40 +(int) (Math.random()*70);
        character.setGold(character.getGold()-gold);
    }
    @Override
    public String toString() {
        return "description: " + this.description;
    }
}
