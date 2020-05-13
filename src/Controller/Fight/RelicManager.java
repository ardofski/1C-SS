package Controller.Fight;
import Model.Effects.Effect;
import Model.Relics.Relic;
import java.util.ArrayList;

public class RelicManager {
    ArrayList<Relic> relics;

    public RelicManager(){
        relics = new ArrayList<>();
    }

    //next turn relics
    public int getSize(){
        return relics.size();
    }

    //next turn relics

    public Relic getRelic( int relicIndex){
        if(relicIndex < 0 || relicIndex >= relics.size())return null;
        return relics.get(relicIndex);
    }

    public void addRelic(Relic r){
        for( int i = 0 ; i < relics.size() ; i++ ){
            if( relics.get(i).getName().equals( r.getName() ) )return;
        }
        relics.add(r);
    }
}
