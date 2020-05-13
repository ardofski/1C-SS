package Model.Effects;

import Model.Fightable;

public class RemoveBlock implements Effect {
    private Fightable target;

    public RemoveBlock( Fightable target){
        this.target = target;
    }

    public Fightable getTarget(){
        return target;
    }

    public void setTarget(Fightable t){target = t;}

    public String toString(){
        String str = new String("Remove block effect");
        return str;
    }
}

