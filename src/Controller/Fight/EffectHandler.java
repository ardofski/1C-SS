package Controller.Fight;

import Model.*;
import Model.Character;
import Model.Effects.Effect;
import Model.Effects.EffectFactory;
import jdk.nashorn.api.scripting.JSObject;


import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.util.ArrayList;

public class EffectHandler {

    private final static String SCRIPT_PATH = "../../Cards/card_effects.js";
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
                         Character character, CardEffectManager cardEffectManager
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

}
