package Controller.Fight;

import Model.*;
import Model.Cards.Bash;
import Model.Character;
import Model.Effects.*;
import Model.Effects.EffectFactory;
import jdk.nashorn.api.scripting.JSObject;


import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.util.ArrayList;

public class EffectHandler {

    //instances
    private ArrayList<Enemy> enemies;
    private Integer turn, currentEnergy;
    private Integer block;
    private Pile handPile, drawPile, exhaustPile, discardPile;
    private Character character;
    private ScriptEngineManager manager;
    private ScriptEngine engine;
    private Invocable inv;
    private EffectFactory effectFactory;
    private CardEffectManager cardEffectManager;

    public EffectHandler(ArrayList<Enemy> enemies,
                         Integer turn, Integer currentEnergy,
                         Pile handPile, Pile drawPile, Pile exhaustPile, Pile discardPile,
                         Character character
    ){
        this.enemies = enemies;
        this.turn = turn;
        this.currentEnergy = currentEnergy;
        this.handPile = handPile;
        this.drawPile = drawPile;
        this.exhaustPile = exhaustPile;
        this.discardPile = discardPile;
        this.character = character;
        this.cardEffectManager = cardEffectManager;
        cardEffectManager = new CardEffectManager(enemies,turn,currentEnergy,handPile,drawPile,exhaustPile,discardPile,character);
    }

    public ArrayList<Effect> getEffect(Card card, Enemy target){
        return cardEffectManager.getEffects( card,target );
    }

    public ArrayList<Effect> getEffect(Card card){
        return cardEffectManager.getEffects( card,null);
    }

    public Effect getPotionEffect( Potion potion){

        return null;
    }

    public ArrayList<Effect> getCardRelicEffects(){
        return null;
    }

    public ArrayList<Effect> getTurnRelicEffects(){

        return null;
    }

    public void applyEffect( Effect effect){
        if(effect instanceof Damage){
            applyDamageEffect( (Damage)effect );
        }
        else if(effect instanceof Block){
            applyBlockEffect((Block)effect);
        }
    }

    private void applyDamageEffect(Damage damage){

        if( damage.getTarget() == null ){
            int blockDamage = Math.min( block, damage.getDamage() );
            block -=  blockDamage;
            //TODO decrease character damage
        }
        else{
            //TODO decrease enemy damage
        }
    }

    private void applyBlockEffect(Block block){
        //TODO apply given block effect
    }

    private void applyBuffEffect(ApplyBuff applyBuff){
        //TODO apply given buff effect
    }

    private void applyMoveCardEffect(MoveCard moveCard){
        //TODO apply given move card effect.
    }

    private void applyUpgradeCardEffect(UpgradeCard upgradeCard){
        //TODO apply given upgrade card effect
    }

}