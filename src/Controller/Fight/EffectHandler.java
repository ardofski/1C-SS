package Controller.Fight;

import Model.*;
import Model.Character;
import jdk.nashorn.api.scripting.JSObject;


import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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

        /*
        Object card = engine.get("Bash");

        //Object damage = inv.invokeMethod(card, "next1", "enemy-1", false);
        JSObject obj = inv.invokeMethod(card, "next1", "enemy-1", false);
        JSObject obj2 = inv.invokeMethod(card, "getDependencies");
        System.out.println(" obj is " + obj);
        System.out.println("obj.target = " + obj.getMember("target"));
        System.out.println("obj.damage = " + obj.getMember("damage"));
        System.out.println("obj2 = " + obj2.getMember("0"));
        */

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

    /*
    private Effect createEffect(JSObject effect,Enemy target){
        String effectName = (String) effect.getMember( "name" );
        EffectFactory effectFactory = new EffectFactory();
        Effect returnEffect = effectFactory.createEffect(effectName,target,0);
        return returnEffect;

    }
    */


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
