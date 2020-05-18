package Controller.Fight;

import Model.Effects.*;
import Model.Enemy;
import Model.Character;
import Model.Room.EnemyRoom;
import Model.Room.Room;

import java.util.ArrayList;
import java.util.Queue;

/**
 * The type Enemy controller.
 */
public class EnemyController {
    /**
     * The Enemies.
     */
    private ArrayList<Enemy> enemies;
    /**
     * The Enemy effects.
     */
    private ArrayList<Queue<ArrayList<Effect>>> enemyEffects;
    /**
     * The Character.
     */
    private Character character;


    /**
     * Instantiates a new Enemy controller.
     *
     * @param r the r
     * @param c the c
     */
    EnemyController(Room r, Character c){
        character = c;
        enemies = ((EnemyRoom)r).getEnemies();

        enemyEffects = new ArrayList<>();
        System.out.println("\n\n\n\n\n\n\nenemy size = " + enemies.size());
        for( int i = 0 ; i < enemies.size() ; i++ ){
            setEnemyTargets( enemies.get(i).getEffects() );
            enemyEffects.add( enemies.get(i).getEffects() );
            System.out.println( "effects of enemy" + i + " added : " + enemies.get(i).getEffects() );
        }
    }

    /**
     * Get size int.
     *
     * @return the int
     */
    public int getSize(){
        return enemies.size();
    }

    /**
     * Get enemy enemy.
     *
     * @param i the
     * @return the enemy
     */
    public Enemy getEnemy(int i){return enemies.get(i); }

    /**
     * Has enemy boolean.
     * if given enemy is dead, returns false. returns true otherwise.
     * @param e the e
     * @return the boolean
     */
    public boolean hasEnemy(Enemy e){
        for( int i = 0 ; i < enemies.size() ; i++ ){
            if( (e == enemies.get(i)) && (!enemies.get(i).isDead()) )return true;
        }
        return false;
    }

    /**
     * See enemy effect array list. It returns the effects of enemy that it wants to apply in the next turn
     *
     * @param index the index
     * @return the array list
     */
    public ArrayList<Effect> seeEnemyEffect(int index){
        if( index < 0 || index >= getSize() )return null;
        EffectFactory effectFactory = new EffectFactory();
        ArrayList<Effect> effects;
        effects = enemyEffects.get(index).peek();
        ArrayList<Effect> clonedEffects = new ArrayList<>();
        for( int i = 0 ; i < effects.size() ; i++){
            clonedEffects.add( effectFactory.cloneEffect(effects.get(i) ) );
        }
        return clonedEffects;
    }

    /**
     * Get enemy effects array list. It returns the enemy effects that are going to be applied in next turn.
     * This method also controls the queue of effect of the enemy.Prepares the queuq for the next turn.
     * @param index the index
     * @return the array list
     */
    public ArrayList<Effect> getEnemyEffects(int index){
        System.out.println("-----------------INDEX = " + index);
        System.out.println("-----------------Size = " + getSize());
        if( index < 0 || index >= getSize() )return null;
        EffectFactory effectFactory = new EffectFactory();
        ArrayList<Effect> effects;
        effects = enemyEffects.get(index).poll();
        ArrayList<Effect> clonedEffects = new ArrayList<>();
        for( int i = 0 ; i < effects.size() ; i++){
            clonedEffects.add( effectFactory.cloneEffect(effects.get(i) ) );
        }
        //pass them at the back of queue
        enemyEffects.get(index).add( effects );

        return clonedEffects;
    }


    /**
     * Remove dead enemies.
     */
    public void removeDeadEnemies( ){
        for( int i = 0 ; i < enemies.size() ; i++ ){
            if( enemies.get(i).getHp() <= 0 ){
                System.out.println( "remove:" + enemies.get(i).getName() );
                enemies.remove(i);
                enemyEffects.remove(i);
            }
        }
    }

    /**
     * Set enemy targets.
     * This method sets the targets of the enemy effects, which are null before setted.
     * @param effectList the effect list
     */
    private void setEnemyTargets(Queue<ArrayList<Effect>> effectList){
        ArrayList<Effect> effects;
        for( int i = 0 ; i < effectList.size() ; i++){
            effects = effectList.poll();
            for(int j = 0 ; j < effects.size() ; j++)
                convertEffect( effects.get(j) );
            effectList.add( effects );
        }

    }

    /**
     * Convert effect.
     * sets the target of given single effect.
     * @param e the e
     */
    private void convertEffect(Effect e){

        if(e instanceof ApplyBuff){
            System.out.println( "TARGET OF APPLY BUFF:");
            System.out.println( ((ApplyBuff)e).getTarget() );

            System.out.println( "NAME OF BUFF");
            System.out.println( ((ApplyBuff)e).getBuff().getName() );
        }
        if( e instanceof ApplyBuff && ((ApplyBuff)e).getTarget() == null){
            ((ApplyBuff)e).setTarget(character);
        }
        if( e instanceof Block && ((Block)e).getTarget() == null){
            ((Block)e).setTarget(character);
        }
        if( e instanceof Damage && ((Damage)e).getTarget() == null){
            ((Damage)e).setTarget(character);
        }
    }
}
