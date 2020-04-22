package Controller.Fight;

import Model.*;
import Model.Character;
import Model.Effects.*;
import Model.Effects.EffectFactory;


import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.Stack;

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
    private BuffManager buffManager;
    private Stack<Effect> effectStack;
    private Stack<Effect> nextTunEffectStack;

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
        effectStack = new Stack<Effect>();
        cardEffectManager = new CardEffectManager(enemies,turn,currentEnergy,handPile,drawPile,exhaustPile,discardPile,character);
        buffManager = new BuffManager(enemies,turn,currentEnergy,handPile,drawPile,exhaustPile,discardPile,character,effectStack);

        nextTunEffectStack = new Stack<Effect>();
    }

    public void playCard(Card card,Enemy target){
        ArrayList<Effect> cardEffects = cardEffectManager.getEffects(card , target);
        for( int i = cardEffects.size() - 1 ; i >= 0 ; i-- ){
            effectStack.push( cardEffects.get(i) );
        }
        effectStack.push( new MoveCard(handPile,discardPile,card) );
        //call run stack
        runStack();

    }

    public void playEnemy(ArrayList<Effect> enemyEffects, Enemy target ){
        for( int i = enemyEffects.size() -  1 ; i >= 0 ; i-- ){
            effectStack.push( enemyEffects.get(i) );
        }
        runStack();
    }

    public void nextTurn(){
        ArrayList<Effect> nextTurnEffects;
        nextTurnEffects = buffManager.getNextTurnEffects();
        for( int i = 0 ; i < nextTurnEffects.size(); i++ ){
            effectStack.push( nextTurnEffects.get(i) );
        }
        runStartStack();
        buffManager.cleanBuffs();
    }

    private void runStack(){

        while( !effectStack.isEmpty() ){
            ArrayList<Effect> buffEffects;

            //read all affects considering the top of stack
            buffEffects = buffManager.nextEffects();
            Effect effect = effectStack.pop();

            buffEffects.add(0,effect);

            //run all effects in the stack

            for( int i = 0 ; i < buffEffects.size() ; i++){

                applyEffect( buffEffects.get(i) );
            }

        }
    }

    private void runStartStack(){
        Effect effect;
        while( !effectStack.isEmpty() ){
            effect = effectStack.pop();
            applyEffect( effect );
        }
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
        //TODO consider all effects
        if(effect instanceof Damage){
            applyDamageEffect( (Damage)effect );
        }
        else if(effect instanceof Block){
            applyBlockEffect((Block)effect);
        }
    }

    private void applyDamageEffect(Damage damage){

        if( damage.getTarget() == null ){
            int damageAmount = damage.getDamage();
            int blockDamage = Math.min( block, damageAmount );
            block -=  blockDamage;
            damageAmount -= blockDamage;
            if( damageAmount > 0){
                int characterHP = character.getHp();
                characterHP -= damageAmount;
                character.setHp( damageAmount );
            }

        }
        else{
            int damageAmount = damage.getDamage();
            Enemy target = damage.getTarget();

            //TODO decrease enemy damage
        }
    }

    private void applyBlockEffect(Block block){
        Enemy target = block.getTarget();

        if( target == null){
            this.block += block.getBlock();
        }
        //TODO apply given block effect to enemy
    }

    private void applyEnergyEffect(ChangeEnergy energy){
        currentEnergy += energy.getEnergy();
    }


    private void applyBuffEffect(ApplyBuff applyBuff){
        Enemy target = applyBuff.getTarget();
        if( target == null ){
            //TODO apply given buff to character
        }
        //TODO apply given buff effect
    }

    private void applyMoveCardEffect(MoveCard moveCard){
        
        //TODO apply given move card effect.
    }

    private void applyUpgradeCardEffect(UpgradeCard upgradeCard){
        //TODO apply given upgrade card effect
    }

}
