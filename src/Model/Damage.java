package Model;

public class Damage implements Effect {
    private int damage;
    private Enemy target;
    public Damage(int damage, Enemy target){
        this.damage = damage;
        this.target = target;
    }

}
