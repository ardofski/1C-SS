package Controller.Fight;

import Model.Buff;
import Model.Buffs.*;
import Model.Character;
import Model.Effects.Effect;
import Model.Enemy;
import Model.Pile;

import java.util.ArrayList;
import java.util.Stack;

public class BuffManager {

    //instances
    private ArrayList<Enemy> enemies;
    private Integer turn, currentEnergy;
    private Integer block;
    private Pile handPile, drawPile, exhaustPile, discardPile;
    private Character character;
    private Stack<Effect> effectStack;

    public BuffManager(ArrayList<Enemy> enemies,
                       Integer turn, Integer currentEnergy,
                       Pile handPile, Pile drawPile, Pile exhaustPile, Pile discardPile,
                       Character character, Stack<Effect> effectStack
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

    /**
        Cleans the all buffs that turn is passed.
     */
    public void cleanBuffs(){
        cleanCharacterBuffs();
        for( int i = 0 ; i < enemies.size() ; i++){
            cleanEnemyBuffs( enemies.get(i) );
        }
    }

    private void cleanCharacterBuffs(){

        ArrayList<Buff> cBuffs = character.getBuffs();
        for( int i = 0 ; i < cBuffs.size() ; i++ ){
            if( cBuffs.get(i).getRemainingTurn() < 1 ){
                cBuffs.remove(i);
            }
        }
    }

    private void cleanEnemyBuffs(Enemy e){
        ArrayList<Buff> eBuffs = e.getBuffs();
        for( int i = 0 ; i < eBuffs.size() ; i++ ){
            if( eBuffs.get(i).getRemainingTurn() < 1 ){
                eBuffs.remove(i);
            }
        }


    }

    /**
    Returns the Effects of Buffs that are going to be active in the following turn.
     */
    public ArrayList<Effect> getNextTurnEffects(){

        ArrayList<Effect> effects = new ArrayList<Effect>();

        ArrayList<Effect> cEffects = checkCharacterBuffs(true);
        effects.addAll( cEffects );
        ArrayList<Effect> eEffects;


        for( int i = 0 ; i < enemies.size() ; i++ ){
            eEffects = checkEnemyBuffs( enemies.get(i),true );
            effects.addAll( eEffects );
        }

        return effects;
    }

    /**
    Returns the Effects of Buffs that are active when it is called.
     */
    public ArrayList<Effect> nextEffects( ){

        ArrayList<Effect> effects = new ArrayList<Effect>();

        ArrayList<Effect> cEffects = checkCharacterBuffs(false);

        if( cEffects != null ){
            effects.addAll( cEffects );
        }

        ArrayList<Effect> eEffects;

        /*
            TODO uncomment when enemies are ready
            for( int i = 0 ; i < enemies.size() ; i++ ){
                eEffects = checkEnemyBuffs( enemies.get(i),false );
                if( eEffects != null )
                    effects.addAll( eEffects );
            }
        */


        return effects;
    }

    /**
     * Returns the Effects of enemy buffs
     * @param enemy owner of the buffs
     * @param nextTurn if it is true , it will return the effects of buffs in the next turn.
     * @return Effects of buffs
     */
    private ArrayList<Effect> checkEnemyBuffs(Enemy enemy,boolean nextTurn){

        ArrayList<Effect> effects = new ArrayList<Effect>();
        ArrayList<Effect> newEffects;
        ArrayList<Buff> eBuffs = enemy.getBuffs();

        for( int i = 0 ; i < eBuffs.size() ; i++ ){
            if( !nextTurn){
                newEffects = createEffects( eBuffs.get(i), enemy );
            }
            else{
                newEffects = createNextTurnEffects( eBuffs.get(i) , enemy );
            }

            effects.addAll( newEffects );
        }

        return effects;

    }

    /**
     * it will return the effects of character buffs
     * @param nextTurn if true, it will return the effects that are going to be active in the next turn.
     * @return Effects of character buffs.
     */
    private ArrayList<Effect> checkCharacterBuffs( boolean nextTurn){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        ArrayList<Effect> newEffects;
        ArrayList<Buff> cBuffs = character.getBuffs();

        if( cBuffs == null )return null;
        for( int i = 0 ; i < cBuffs.size() ; i++ ){
            if( !nextTurn){
                newEffects = createEffects( cBuffs.get(i), null );
            }
            else{
                newEffects = createNextTurnEffects( cBuffs.get(i) , null );
            }
            if( newEffects != null ){
                effects.addAll( newEffects );
            }
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
        //TODO call each buffs run functions with correct parameters.

        return null;
    }

    /**
     * This function calls the nextTurn function of each buff and Returns the effects of that buff.
     * @param buff Buff that function interested in
     * @param owner Owner of the buff
     * @return
     */
    private ArrayList<Effect> createNextTurnEffects(Buff buff, Enemy owner){
        //TODO call each buff nextTurn method.
        return null;

    }


}
