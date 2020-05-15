package Controller.Fight;

import Model.Effects.ApplyBuff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Potion;
import Model.Enemy;
public class PotionController {
    public PotionController(){}

    Effect getPotionEffect(Enemy e,Potion p){
       if(p.getName().equals("Fire Potion")){
           Damage d = (Damage) p.getEffect();
           d.setTarget(e);
           return d;
       }else if(p.getName().equals("Fear Potion")){
           ApplyBuff a = (ApplyBuff) p.getEffect();
           a.setTarget(e);
           return a;
       }
       return null;
    }
}
