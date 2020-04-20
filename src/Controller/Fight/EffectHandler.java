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

        //js manage
        manager = new ScriptEngineManager();
        engine = manager.getEngineByName("javascript");
        inv = (Invocable) engine;
        try {
            engine.eval(new FileReader(SCRIPT_PATH));
        }catch( Exception e){
            System.out.println( e );
        }

        //Effect Factory
        effectFactory = new EffectFactory();

    }

    private void addParam( String p,String dependency, Enemy target, Card c){
        if( dependency.equals("target") ){
            p += target.getName();
        }
        else if( dependency.equals("block") ){
            p += block;
        }
        else if( dependency.equals("handPile")){
            p  += handPile.toString();
        }

    }

    public ArrayList<Effect> getEffect(Card card, Enemy target){
        String cardName = card.getName();
        JSObject jsCard = (JSObject) engine.get("cardName");
        try{
            //Read Dependencies
            JSObject dependencies = (JSObject) inv.invokeMethod(card, "getDependencies");
            int numOfEffects = (int) dependencies.getMember("effectNum");
            int numOfDependencies = (int)dependencies.getMember("num");

            String param = new String("");

            //prepare parameters of js function
            for( int i = 1; i <= numOfDependencies; i++){
                String dep = (String)dependencies.getMember("d"+i );
                this.addParam( param, dep, target, card);
                param += ",";
            }

            //create effects and add them to arraylist.
            ArrayList<Effect> effects = new ArrayList<Effect>();
            Effect e = null;

            for(int i = 1; i<= numOfEffects; i++){
                JSObject effect = (JSObject) inv.invokeMethod(card, "next"+i );
                e = effectFactory.createEffect( effect,target);
                effects.add( e );
            }
            return effects;

        }
        catch( Exception e){
            System.out.println(e);
        }

        return null;
    }

    public ArrayList<Effect> getEffect(Card card){
        String cardName = card.getName();
        JSObject jsCard = (JSObject) engine.get("cardName");
        try{
            //Read Dependencies
            JSObject dependencies = (JSObject) inv.invokeMethod(card, "getDependencies");
            int numOfEffects = (int) dependencies.getMember("effectNum");
            int numOfDependencies = (int)dependencies.getMember("num");

            String param = new String("");

            //prepare parameters of js function
            for( int i = 1; i <= numOfDependencies; i++){
                String dep = (String)dependencies.getMember("d"+i );
                this.addParam( param, dep, null, card);
                param += ",";
            }

            //create effects and add them to arraylist.
            ArrayList<Effect> effects = new ArrayList<Effect>();
            Effect e = null;

            for(int i = 1; i<= numOfEffects; i++){
                JSObject effect = (JSObject) inv.invokeMethod(card, "next"+i );
                e = effectFactory.createEffect( effect,null);
                effects.add( e );
            }
            return effects;

        }
        catch( Exception e){
            System.out.println(e);
        }

        return null;
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
