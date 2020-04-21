package Model.Effects;

import Model.Effects.Effect;
import Model.Enemy;

public class Damage implements Effect {
    private int damage;
    private Enemy target;
    private Enemy source;
    public Damage(int damage, Enemy target, Enemy source){
        this.damage = damage;
        this.target = target;
        this.source = source;
    }

    public int getDamage(){
        return damage;
    }
    public Enemy getTarget(){
        return target;
    }
    public Enemy getSource(){ return source;}

}
