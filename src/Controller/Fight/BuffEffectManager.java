package Controller.Fight;

import Model.Buff;
import Model.Buffs.*;
import Model.Character;
import Model.Effects.Effect;
import Model.Enemy;
import Model.Pile;

import java.util.ArrayList;
import java.util.Stack;

public class BuffEffectManager {

    //instances
    private ArrayList<Enemy> enemies;
    private Integer turn, currentEnergy;
    private Integer block;
    private Pile handPile, drawPile, exhaustPile, discardPile;
    private Character character;
    private Stack<Effect> effectStack;

    public BuffEffectManager(ArrayList<Enemy> enemies,
                             Integer turn, Integer currentEnergy,
                             Pile handPile, Pile drawPile, Pile exhaustPile, Pile discardPile,
                             Character character,Stack<Effect> effectStack
    ){
        this.enemies = enemies;
        this.turn = turn;
        this.currentEnergy = currentEnergy;
        this.handPile = handPile;
        this.drawPile = drawPile;
        this.exhaustPile = exhaustPile;
        this.discardPile = discardPile;
        this.character = character;
        this.effectStack = effectStack;
    }

    public ArrayList<Effect> nextEffects(){

        ArrayList<Effect> effects = new ArrayList<Effect>();

        ArrayList<Effect> cEffects = checkCharacterBuffs();
        effects.addAll( cEffects );
        ArrayList<Effect> eEffects;


        for( int i = 0 ; i < enemies.size() ; i++ ){
            eEffects = checkEnemyBuffs( enemies.get(i) );
            effects.addAll( eEffects );
        }

        return effects;
    }

    private ArrayList<Effect> checkEnemyBuffs(Enemy enemy){

        ArrayList<Effect> effects = new ArrayList<Effect>();
        ArrayList<Effect> newEffects;
        ArrayList<Buff> eBuffs = enemy.getBuffs();

        for( int i = 0 ; i < eBuffs.size() ; i++ ){
            newEffects = createEffects( eBuffs.get(i), enemy );
            effects.addAll( newEffects );
        }

        return effects;

    }

    private ArrayList<Effect> checkCharacterBuffs( ){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        ArrayList<Effect> newEffects;
        ArrayList<Buff> cBuffs = character.getBuffs();

        for( int i = 0 ; i < cBuffs.size() ; i++ ){
            newEffects = createEffects( cBuffs.get(i), null );
            effects.addAll( newEffects );
        }

        return effects;
    }

    /*
    TODO implement createEffects function
     */

    private ArrayList<Effect> createEffects( Buff buff,Enemy owner ){
        if( buff instanceof Artifact){
            Artifact castedBuff = (Artifact)buff;
            //TODO call artifact run
        }
        //TODO call each buufs run functions with correct parameters.

        return null;
    }


}
