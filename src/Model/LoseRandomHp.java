package Model;

public class LoseRandomHp extends Option {
    public LoseRandomHp()
    {
        this.description = "Lose random amount of Hp (3 - 5)";
    }
    @Override
    public void applyOption(Character character) {
        int hp = 3 +(int) (Math.random()*3);
        character.setHp(character.getHp()-hp);
    }
    @Override
    public String toString() {
        return "description: " + this.description;
    }
}
