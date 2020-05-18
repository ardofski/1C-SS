package Controller.Fight;
import Model.Effects.Effect;
import Model.Enemy;
import Model.Relics.Relic;
import java.util.ArrayList;
import java.util.Stack;

import Model.Character;

/**
 * The type Relic manager.
 */
public class  RelicManager {
    /**
     * The Character.
     */
    Character character;


    /**
     * Instantiates a new Relic manager.
     *
     * @param character the character
     */
    public RelicManager(Character character){
        this.character = character;

    }

    /**
     * Get relic size int.
     *
     * @return the int
     */
    public int getRelicSize(){
        return character.getRelics().size();
    }

    /**
     * Apply begining of fight effects array list.
     *
     * @param effectStack the effect stack
     * @param enemyList   the enemy list
     * @return the array list
     */
//beginning of fight relics
    public ArrayList<Effect> applyBeginingOfFightEffects(Stack<Effect> effectStack,ArrayList<Enemy> enemyList){
        RelicDependencies dep = new RelicDependencies(character,effectStack,enemyList);
        ArrayList<Effect> returnEffects = new ArrayList<>();
        for(int i=0; i<getRelicSize();i++){
            System.out.println(( "Relic : " + getRelic(i) + " is processed."));
            ArrayList<Effect> begOfFight = getRelic(i).getBeginingOfFightEffects(dep);
            if( begOfFight != null)
                returnEffects.addAll( begOfFight );
        }

        return returnEffects;
    }

    /**
     * Apply room effects array list.
     *
     * @param effectStack the effect stack
     * @param enemyList   the enemy list
     * @return the array list
     */
//entering room relics
    public ArrayList<Effect> applyRoomEffects(Stack<Effect> effectStack,ArrayList<Enemy> enemyList){
        RelicDependencies dep = new RelicDependencies(character,effectStack,enemyList);
        ArrayList<Effect> returnEffects = new ArrayList<>();
        for(int i=0; i<getRelicSize();i++){
            getRelic(i).applyRoomEffects(dep);
        }

        return returnEffects;
    }

    /**
     * Apply next turn effects array list.
     *
     * @param effectStack the effect stack
     * @param enemyList   the enemy list
     * @return the array list
     */
//next turn relics
    public ArrayList<Effect> applyNextTurnEffects(Stack<Effect> effectStack, ArrayList<Enemy> enemyList){
        RelicDependencies dep = new RelicDependencies(character,effectStack,enemyList);
        ArrayList<Effect> returnEffects = new ArrayList<>();
        for(int i=0; i<getRelicSize();i++){
            returnEffects.addAll( getRelic(i).getNextTurnEffects(dep) );
        }

        return returnEffects;
    }

    /**
     * Get turn effects array list.
     *
     * @param effectStack the effect stack
     * @param enemyList   the enemy list
     * @return the array list
     */
//turn effects
    public ArrayList<Effect> getTurnEffects( Stack<Effect> effectStack, ArrayList<Enemy> enemyList){
        RelicDependencies dep = new RelicDependencies(character,effectStack,enemyList);
        ArrayList<Effect> returnEffects = new ArrayList<>();
        for(int i=0; i<getRelicSize();i++){
            ArrayList<Effect> relicEffects = getRelic(i).getTurnEffects(dep);
            if(relicEffects != null) returnEffects.addAll( relicEffects );
        }

        return returnEffects;
    }

    /**
     * Get end of fight effects array list.
     *
     * @param effectStack the effect stack
     * @param enemyList   the enemy list
     * @return the array list
     */
//apply end of fight effects
    public ArrayList<Effect> getEndOfFightEffects(Stack<Effect> effectStack,ArrayList<Enemy> enemyList){
        RelicDependencies dep = new RelicDependencies(character,effectStack,enemyList);
        ArrayList<Effect> returnEffects = new ArrayList<>();
        for(int i=0; i<getRelicSize();i++){
            ArrayList<Effect> endOfFightEffects = getRelic(i).getEndOfFightEffects(dep);
            if( endOfFightEffects != null )
                returnEffects.addAll( endOfFightEffects );
        }

        return returnEffects;
    }

    /**
     * Get start of turn effects array list.
     *
     * @param effectStack the effect stack
     * @param enemyList   the enemy list
     * @return the array list
     */
//apply start of turn effects
    public ArrayList<Effect> getStartOfTurnEffects(Stack<Effect> effectStack,ArrayList<Enemy> enemyList){
        RelicDependencies dep = new RelicDependencies(character,effectStack,enemyList);
        ArrayList<Effect> returnEffects = new ArrayList<>();
        for(int i=0; i<getRelicSize();i++){
            ArrayList<Effect> startEffects = getRelic(i).getStartOfTurnEffects(dep);
            if( startEffects != null )
                returnEffects.addAll( startEffects );
        }

        return returnEffects;
    }


    /**
     * Get relic relic.
     *
     * @param relicIndex the relic ındex
     * @return the relic
     */
    public Relic getRelic( int relicIndex){
        if(relicIndex < 0 || relicIndex >= character.getRelics().size() )return null;
        return character.getRelics().get(relicIndex);
    }

    /**
     * Add relic.
     *
     * @param r the r
     */
    public void addRelic(Relic r){
        for( int i = 0 ; i < character.getRelics().size() ; i++ ){
            if( character.getRelics().get(i).getName().equals( r.getName() ) )return;
        }
        character.getRelics().add(r);
    }
}
