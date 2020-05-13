package Controller.Fight;

import Model.Effects.Effect;
import Model.Fightable;
import Model.Character;

import java.util.Stack;

public class RelicDependencies {
    Character character;
    Stack<Effect> effectStack;
    RelicDependencies(Character character){
        this.character = character;
        effectStack = null;
    }

    RelicDependencies(Character c, Stack<Effect> s){
        character = c;
        effectStack = s;
    }

    public Stack<Effect> getEffectStack(){
        return effectStack;
    }

    public Character getCharacter() {
        return character;
    }
}

