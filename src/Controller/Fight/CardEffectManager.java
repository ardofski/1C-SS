package Controller.Fight;

import Model.Card;
import Model.Cards.*;
import Model.Character;
import Model.Effects.Effect;
import Model.Effects.EffectFactory;
import Model.Enemy;
import Model.Pile;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.util.ArrayList;

public class CardEffectManager {

    //instances
    private ArrayList<Enemy> enemies;
    private Integer turn, currentEnergy;
    private Integer block;
    private Pile handPile, drawPile, exhaustPile, discardPile;
    private Character character;

    public CardEffectManager(ArrayList<Enemy> enemies,
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
    }

    public ArrayList<Effect> getEffects(Card card, Enemy target){
        if(card instanceof Anger){
            Anger castedCard = (Anger)card;
            return castedCard.getEffects(target, handPile);
        }
        else if( card instanceof Armaments){
            Armaments castedCard = (Armaments)card;
            return castedCard.getEffects(target,handPile);
        }
        else if( card instanceof Bash){
            Bash castedCard = (Bash)card;
            return castedCard.getEffects(target);
        }
        else if( card instanceof BodySlam){
            BodySlam castedCard = (BodySlam)card;
            return castedCard.getEffects(target,block);
        }
        else if(card instanceof Defend){
            Defend castedCard = (Defend)card;
            return castedCard.getEffects(target);
        }
        else if(card instanceof Strike){
            Strike castedCard = (Strike)card;
            return castedCard.getEffects(target);
        }
        return null;
    }
}