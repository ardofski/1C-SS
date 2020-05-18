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
     * @param turn          the turn
     * @param currentEnergy the current energy
     * @param piles         the piles
     * @param character     the character
     * @param effectStack   the effect stack
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
     * Clean buffs.
     */
    public void cleanBuffs(){
        cleanCharacterBuffs();
        for( int i = 0 ; i < enemies.size() ; i++){
            cleanEnemyBuffs( enemies.get(i) );
        }
    }

    /**
     * Clean character buffs.
     */
    private void cleanCharacterBuffs(){
        character.getBuffs().cleanBuffs();
    }

    /**
     * Clean enemy buffs.
     *
     * @param e the e
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
     *
     * @return the array list
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
     *
     * @param e the e
     * @return the array list
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
     *
     * @return the array list
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
     *
     * @param enemy    the enemy
     * @param nextTurn the next turn
     * @return the array list
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
     * Get character buff effects array list.
     *
     * @param nextTurn the next turn
     * @return the array list
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

    /*
    TODO implement createEffects function
     */

    /**
     * Create turn effects array list.
     *
     * @param buff  the buff
     * @param owner the owner
     * @return the array list
     */
    private ArrayList<Effect> createTurnEffects( Buff buff,Fightable owner ){
        BuffDependencies dep = new BuffDependencies(owner,effectStack);
        if( buff.isValid() )
            return buff.getTurnEffects(dep);
        return null;
    }

    /**
     * Create next turn effects array list.
     *
     * @param buff  the buff
     * @param owner the owner
     * @return the array list
     */
    private ArrayList<Effect> createNextTurnEffects(Buff buff, Fightable owner){
        BuffDependencies dep = new BuffDependencies(owner,effectStack);
        if( buff.isValid() )
            return buff.getNextTurnEffects(dep);
        return null;
    }


}
