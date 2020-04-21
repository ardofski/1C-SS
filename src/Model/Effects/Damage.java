package Model.Effects;

import Model.Effects.Effect;
import Model.Enemy;

public class Damage implements Effect {
    private int damage;
    private Enemy target;
    public Damage(int damage, Enemy target){
        this.damage = damage;
        this.target = target;
    }

    public int getDamage(){
        return damage;
    }
    public Enemy getTarget(){
        return target;
    }

}
