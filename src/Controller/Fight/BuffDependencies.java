package Controller.Fight;

import Model.Effects.Effect;
import Model.Enemy;
import Model.Character;
import Model.Fightable;

import java.util.Stack;

public class BuffDependencies {
    Stack<Effect> effectStack;
    Fightable owner;

    BuffDependencies(Fightable owner, Stack<Effect> s ){
        effectStack = s;
        this.owner = owner;
    }

    public Stack<Effect> getEffectStack(){
        return effectStack;
    }

    public Fightable getOwner() {
        return owner;
    }

}
