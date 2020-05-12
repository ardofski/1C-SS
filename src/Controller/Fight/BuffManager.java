package Controller.Fight;

import Model.*;
import Model.Buffs.*;
import Model.Character;
import Model.Effects.Effect;

import java.util.ArrayList;
import java.util.Stack;

public class BuffManager {

    //instances
    private ArrayList<Enemy> enemies;
    private Integer turn, currentEnergy;
    private Integer block;
    private PileCollection piles;
    private Character character;
    private Stack<Effect> effectStack;

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
        Cleans the all buffs that turn is passed.
     */
    public void cleanBuffs(){
        cleanCharacterBuffs();
        for( int i = 0 ; i < enemies.size() ; i++){
            cleanEnemyBuffs( enemies.get(i) );
        }
    }

    private void cleanCharacterBuffs(){
        character.getBuffs().cleanBuffs();
    }

    private void cleanEnemyBuffs(Enemy e){
        e.getBuffs().cleanBuffs();
    }

    /**
    Returns the Effects of Buffs that are going to be active in the following turn.
     */
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

    /**
    Returns the Effects of Buffs that are active when it is called.
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
     * Returns the Effects of enemy buffs
     * @param enemy owner of the buffs
     * @param nextTurn if it is true , it will return the effects of buffs in the next turn.
     * @return Effects of buffs
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
     * it will return the effects of character buffs
     * @param nextTurn if true, it will return the effects that are going to be active in the next turn.
     * @return Effects of character buffs.
     */
    private ArrayList<Effect> getCharacterBuffEffects( boolean nextTurn){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        ArrayList<Effect> newEffects;
        ArrayList<Buff> cBuffs = character.getBuffs().getBuffs();

        if( cBuffs == null )return effects
                ;
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

    private ArrayList<Effect> createTurnEffects( Buff buff,Fightable owner ){
        BuffDependencies dep = new BuffDependencies(owner,effectStack);
        if( buff.isValid() )
            return buff.getTurnEffects(dep);
        return null;
    }

    /**
     * This function calls the nextTurn function of each buff and Returns the effects of that buff.
     * @param buff Buff that function interested in
     * @param owner Owner of the buff
     * @return
     */
    private ArrayList<Effect> createNextTurnEffects(Buff buff, Fightable owner){
        BuffDependencies dep = new BuffDependencies(owner,effectStack);
        if( buff.isValid() )
            return buff.getNextTurnEffects(dep);
        return null;
    }


}
