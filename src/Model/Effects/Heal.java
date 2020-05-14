package Model.Effects;

public class Heal implements Effect {
    int healAmount;
    public Heal(int x){
        healAmount=x;
    }
    public int getHealAmount() {
        return healAmount;
    }
}
