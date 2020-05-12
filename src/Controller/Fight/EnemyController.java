package Controller.Fight;

import Model.Effects.ApplyBuff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;
import Model.Character;
import Model.Room.EnemyRoom;
import Model.Room.Room;

import java.util.ArrayList;
import java.util.Queue;

public class EnemyController {
    private ArrayList<Enemy> enemies;
    private ArrayList<Queue<ArrayList<Effect>>> enemyEffects;
    private Character character;



    EnemyController(Room r, Character c){
        character = c;
        enemies = ((EnemyRoom)r).getEnemies();

        enemyEffects = new ArrayList<>();
        for( int i = 0 ; i < enemies.size() ; i++ ){
            setEnemyTargets( enemies.get(i).getEffects() );
            enemyEffects.add( enemies.get(i).getEffects() );
            //System.out.println( "effects of enemy" + i + " added : " + enemies.get(i).getEffects() );
        }
    }

    public int getSize(){
        return enemies.size();
    }

    public ArrayList<Effect> getEnemyEffects(int index){
        if( index < 0 || index >= getSize() )return null;

        ArrayList<Effect> effects;
        effects = enemyEffects.get(index).poll();
        //pass them at the back of queue
        enemyEffects.get(index).add( effects );

        return effects;
    }


    public void removeDeadEnemies( ){
        for( int i = 0 ; i < enemies.size() ; i++ ){
            if( enemies.get(i).getHp() <= 0 ){
                System.out.println( "remove:" + enemies.get(i).getName() );
                enemies.remove(i);
                enemyEffects.remove(i);
            }
        }
    }

    private void setEnemyTargets(Queue<ArrayList<Effect>> effectList){
        ArrayList<Effect> effects;
        for( int i = 0 ; i < effectList.size() ; i++){
            effects = effectList.poll();
            for(int j = 0 ; j < effects.size() ; j++)
                convertEffect( effects.get(j) );
            effectList.add( effects );
        }
    }

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
