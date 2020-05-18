package Controller.Fight;

import Model.*;
import Model.Buffs.*;
import Model.Character;
import Model.Effects.Effect;

import java.util.ArrayList;
import java.util.Stack;

/**
 * The type Buff manager.
 */
public class BuffManager {

    /**
     * The Enemies.
     */
    //instances
    private ArrayList<Enemy> enemies;
    /**
     * The Turn.
     */
    private Integer turn,
    /**
     * The Current energy.
     */
    currentEnergy;
    /**
     * The Block.
     */
    private Integer block;
    /**
     * The Piles.
     */
    private PileCollection piles;
    /**
     * The Character.
     */
    private Character character;
    /**
     * The Effect stack.
     */
    private Stack<Effect> effectStack;

    /**
     * Instantiates a new Buff manager.
     *
     * @param enemies       the enemies
     * @param turn          the current turn of fight
     * @param currentEnergy the current energy of the character
     * @param piles         the piles which includes hand, discard and draw pile
     * @param character     the character
     * @param effectStack   the effect stack of effect handler
     */
    public BuffManager(ArrayList<Enemy> enemies,
                       Integer turn, Integer currentEnergy,
                       PileCollection piles,
                       Character character, Stack<Effect> effectStack
    ){
        this.enemies = enemies;
        this.turn = turn;
        this.currentEnergy = currentEnergy;
        this.piles = piles;
        this.character = character;
        this.effectStack = effectStack;
    }

    /**
     * Clean buffs that are not active anymore.
     */
    public void cleanBuffs(){
        cleanCharacterBuffs();
        for( int i = 0 ; i < enemies.size() ; i++){
            cleanEnemyBuffs( enemies.get(i) );
        }
    }

    /**
     * Clean character buffs that are not active anymore.
     */
    private void cleanCharacterBuffs(){
        character.getBuffs().cleanBuffs();
    }

    /**
     * Clean enemy buffs than are not active anymore.
     *
     * @param e the enemy
     */
    private void cleanEnemyBuffs(Enemy e){
        e.getBuffs().cleanBuffs();
    }

    /*
    public ArrayList<Effect> getNextTurnEffects(){

        ArrayList<Effect> effects = new ArrayList<Effect>();

        ArrayList<Effect> cEffects = getCharacterBuffEffects(true);
        effects.addAll( cEffects );
        ArrayList<Effect> eEffects;

        for( int i = 0 ; i < enemies.size() ; i++ ){
            eEffects = getEnemyBuffEffects( enemies.get(i),true );
            effects.addAll( eEffects );
        }
        return effects;
    }
    */


    /**
     * Get character next turn effects array list.
     * It contains the effects of character that is going to be applied at the begginnig of next turn.
     * @return the array list of effects
     */
    public ArrayList<Effect> getCharacterNextTurnEffects(){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        ArrayList<Effect> newEffects;
        ArrayList<Buff> cBuffs = character.getBuffs().getBuffs();

        if( cBuffs == null )return effects;
        for( int i = 0 ; i < cBuffs.size() ; i++ ){
            newEffects = createNextTurnEffects( cBuffs.get(i) , character );
            if( newEffects != null ){
                effects.addAll( newEffects );
            }
        }
        return effects;
    }

    /**
     * Get enemy next turn effect array list.
     * Returns the effects of the enemy that is going to be applied at the next turn.
     * @param e the enemy
     * @return the array list of effects.
     */
    public ArrayList<Effect> getEnemyNextTurnEffect(Enemy e){
        return getEnemyBuffEffects( e ,true );
    }

    /*
    public ArrayList<Effect> getEnemyNextTurnEffects(){
        ArrayList<Effect> effects = new ArrayList<Effect>();

        for( int i = 0 ; i < enemies.size() ; i++ ){
            effects.addAll( getEnemyBuffEffects( enemies.get(i),true ) );
        }
        return effects;

    }
    */

    /**
     * Get turn effects array list.
     * Return the Effects that are going to be applied in this turn.
     * @return the array list of effects.
     */
    public ArrayList<Effect> getTurnEffects( ){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        ArrayList<Effect> cEffects = getCharacterBuffEffects(false);
        if( cEffects != null ){
            effects.addAll( cEffects );
        }
        ArrayList<Effect> eEffects;

        for( int i = 0 ; i < enemies.size() ; i++ ){
            eEffects = getEnemyBuffEffects( enemies.get(i),false );
            if( eEffects != null )
                effects.addAll( eEffects );
        }

        return effects;
    }

    /**
     * Get enemy buff effects array list.
     * Returns the enemy effects in this turn if nextTurn is false, returns the enemy effects
     * if nextTurn is true.
     * @param enemy    the enemy
     * @param nextTurn the nextTurn
     * @return the array list of effects
     */
    private ArrayList<Effect> getEnemyBuffEffects(Enemy enemy,boolean nextTurn){

        ArrayList<Effect> effects = new ArrayList<Effect>();
        ArrayList<Effect> newEffects;
        ArrayList<Buff> eBuffs = enemy.getBuffs().getBuffs();

        for( int i = 0 ; i < eBuffs.size() ; i++ ){
            if( !nextTurn ){
                newEffects = createTurnEffects( eBuffs.get(i) , enemy );
            }
            else{
                newEffects = createNextTurnEffects( eBuffs.get(i) , enemy );
            }

            if( newEffects != null )
                effects.addAll( newEffects );
        }

        return effects;

    }

    /**
     * Get character buff effects array list. If nextTurn is true, return the next Turn effects of buffs.
     * returns the current turn effects otherwise.
     * @param nextTurn the next turn
     * @return the array list of effects
     */
    private ArrayList<Effect> getCharacterBuffEffects( boolean nextTurn){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        ArrayList<Effect> newEffects;
        ArrayList<Buff> cBuffs = character.getBuffs().getBuffs();

        if( cBuffs == null )return effects;
        for( int i = 0 ; i < cBuffs.size() ; i++ ){
            if( !nextTurn ){
                newEffects = createTurnEffects( cBuffs.get(i), character );
            }
            else{
                newEffects = createNextTurnEffects( cBuffs.get(i) , character );
            }
            if( newEffects != null ){
                effects.addAll( newEffects );
            }
        }

        return effects;
    }


    /**
     * Create turn effects array list of the owner for given buff.
     *
     * @param buff  the buff
     * @param owner the owner of buff
     * @return the array list of effects of buff
     */
    private ArrayList<Effect> createTurnEffects( Buff buff,Fightable owner ){
        BuffDependencies dep = new BuffDependencies(owner,effectStack);
        if( buff.isValid() )
            return buff.getTurnEffects(dep);
        return null;
    }

    /**
     * Create next turn effects array list of the owner for given buff.
     *
     * @param buff  the buff
     * @param owner the owner of buff
     * @return the array list of effects of buff
     */
    private ArrayList<Effect> createNextTurnEffects(Buff buff, Fightable owner){
        BuffDependencies dep = new BuffDependencies(owner,effectStack);
        if( buff.isValid() )
            return buff.getNextTurnEffects(dep);
        return null;
    }


}
