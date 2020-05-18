package Model.Effects;

/**
 * The type Heal.
 */
public class Heal implements Effect {
    /**
     * The Heal amount.
     */
    int healAmount;

    /**
     * Instantiates a new Heal.
     *
     * @param x the x
     */
    public Heal(int x){
        healAmount=x;
    }

    /**
     * Gets heal amount.
     *
     * @return the heal amount
     */
    public int getHealAmount() {
        return healAmount;
    }
}
