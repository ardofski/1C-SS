package Controller.Fight;

import Model.Buffs.Vulnerable;
import Model.Effects.*;
import Model.Potion;
import Model.Enemy;
import Model.Character;
public class PotionController {
    public PotionController(){}

    Effect getPotionEffect(Enemy e,Potion p, Character ch){
       if(p.getName().equals("Fire Potion")){
           Damage d = (Damage) p.getEffect();
           d.setTarget(e);
           d.setSource(ch);
           return d;
       }else if(p.getName().equals("Fear Potion")){
           ApplyBuff a = (ApplyBuff) p.getEffect();
           a.setTarget(e);
           return a;
       }
       else if(p.getName().equals("Block Potion")){
           Block b = (Block) p.getEffect();
           b.setTarget(ch);
           return b;
       }
       else if(p.getName().equals("Ancient Potion")){
           ApplyBuff a = (ApplyBuff) p.getEffect();
           a.setTarget(ch);
           return a;
       }
       else if(p.getName().equals("Blood Potion")){
           return p.getEffect();
       }
       else if(p.getName().equals("Cultist Potion")){
           ApplyBuff a = (ApplyBuff) p.getEffect();
           a.setTarget(ch);
           return a;
       }
       else if(p.getName().equals("Dexterity Potion")){
           ApplyBuff a = (ApplyBuff) p.getEffect();
           a.setTarget(ch);
           return a;
       }
       else if(p.getName().equals("Energy Potion")){
           ChangeEnergy c = (ChangeEnergy) p.getEffect();
           /*int healAmount = c.getEnergy();
           c = new ChangeEnergy(ch.getEnergy()+healAmount);*/
           return c;
       }
       else if(p.getName().equals("Essence Of Steel Potion")){
           ApplyBuff a = (ApplyBuff) p.getEffect();
           a.setTarget(ch);
           return a;
       }
       else if(p.getName().equals("Strength Potion")){
           ApplyBuff a = (ApplyBuff) p.getEffect();
           a.setTarget(ch);
           return a;
       }

       return null;
    }
    
}
