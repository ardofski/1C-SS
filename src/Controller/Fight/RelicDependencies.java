package Controller.Fight;

import Model.Effects.Effect;
import Model.Enemy;
import Model.Fightable;
import Model.Character;

import java.util.ArrayList;
import java.util.Stack;

public class RelicDependencies {
    Character character;
    ArrayList<Enemy> enemies;
    Stack<Effect> effectStack;
    RelicDependencies(Character character){
        this.character = character;
        effectStack = null;
        enemies = null;
    }

    RelicDependencies(Character c, Stack<Effect> s, ArrayList<Enemy> e){
        character = c;
        effectStack = s;
        enemies = e;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Stack<Effect> getEffectStack(){
        return effectStack;
    }

    public Character getCharacter() {
        return character;
    }
}

