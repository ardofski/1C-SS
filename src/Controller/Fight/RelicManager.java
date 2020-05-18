package Controller.Fight;
import Model.Effects.Effect;
import Model.Enemy;
import Model.Relics.Relic;
import java.util.ArrayList;
import java.util.Stack;

import Model.Character;

public class  RelicManager {
    Character character;


    public RelicManager(Character character){
        this.character = character;

    }

    public int getRelicSize(){
        return character.getRelics().size();
    }

    //beginning of fight relics
    public ArrayList<Effect> applyBeginingOfFightEffects(Stack<Effect> effectStack,ArrayList<Enemy> enemyList){
        RelicDependencies dep = new RelicDependencies(character,effectStack,enemyList);
        ArrayList<Effect> returnEffects = new ArrayList<>();
        for(int i=0; i<getRelicSize();i++){
            ArrayList<Effect> begOfFight = getRelic(i).getBeginingOfFightEffects(dep);
            if( begOfFight != null)
                returnEffects.addAll( begOfFight );
        }

        return returnEffects;
    }

    //entering room relics
    public ArrayList<Effect> applyRoomEffects(Stack<Effect> effectStack,ArrayList<Enemy> enemyList){
        RelicDependencies dep = new RelicDependencies(character,effectStack,enemyList);
        ArrayList<Effect> returnEffects = new ArrayList<>();
        for(int i=0; i<getRelicSize();i++){
            getRelic(i).applyRoomEffects(dep);
        }

        return returnEffects;
    }

    //next turn relics
    public ArrayList<Effect> applyNextTurnEffects(Stack<Effect> effectStack, ArrayList<Enemy> enemyList){
        RelicDependencies dep = new RelicDependencies(character,effectStack,enemyList);
        ArrayList<Effect> returnEffects = new ArrayList<>();
        for(int i=0; i<getRelicSize();i++){
            returnEffects.addAll( getRelic(i).getNextTurnEffects(dep) );
        }

        return returnEffects;
    }

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

    //apply end of fight effects
    public ArrayList<Effect> getEndOfFightEffects(Stack<Effect> effectStack,ArrayList<Enemy> enemyList){
        RelicDependencies dep = new RelicDependencies(character,effectStack,enemyList);
        ArrayList<Effect> returnEffects = new ArrayList<>();
        for(int i=0; i<getRelicSize();i++){
            returnEffects.addAll( getRelic(i).getBeginingOfFightEffects(dep) );
        }

        return returnEffects;
    }

    //apply start of turn effects
    public ArrayList<Effect> getStartOfTurnEffects(Stack<Effect> effectStack,ArrayList<Enemy> enemyList){
        RelicDependencies dep = new RelicDependencies(character,effectStack,enemyList);
        ArrayList<Effect> returnEffects = new ArrayList<>();
        for(int i=0; i<getRelicSize();i++){
            returnEffects.addAll( getRelic(i).getStartOfTurnEffects(dep) );
        }

        return returnEffects;
    }


    public Relic getRelic( int relicIndex){
        if(relicIndex < 0 || relicIndex >= character.getRelics().size() )return null;
        return character.getRelics().get(relicIndex);
    }

    public void addRelic(Relic r){
        for( int i = 0 ; i < character.getRelics().size() ; i++ ){
            if( character.getRelics().get(i).getName().equals( r.getName() ) )return;
        }
        character.getRelics().add(r);
    }
}
