package Model;

import jdk.nashorn.api.scripting.JSObject;

import javax.swing.*;

public class EffectFactory {

    public EffectFactory(){

    }

    public Effect createEffect(JSObject jsEffect, Enemy target){

        String effectName = (String) jsEffect.getMember( "name" );

        Effect effect = null;
        if( effectName.equals("Damage") ){
            effect = this.createDamageEffect( jsEffect, target);
        }
        else if( effectName.equals("Block") ){
            effect = this.createBlockEffect( jsEffect, target);
        }

        return effect;
    };

    private Damage createDamageEffect(JSObject jsEffect,Enemy target ){
        Damage damageEffect = null;
        int damage = (int) jsEffect.getMember( "damage" );
        damageEffect = new Damage( damage,target);
        return damageEffect;
    }

    private Block createBlockEffect(JSObject jsEffect, Enemy target ){
        Block blockEffect = null;
        int block = (int )jsEffect.getMember("block");
        blockEffect = new Block(block,target);
        return blockEffect;
    }
}
