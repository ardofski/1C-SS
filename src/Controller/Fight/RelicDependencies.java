package Controller.Fight;

import Model.Effects.Effect;
import Model.Enemy;
import Model.Fightable;
import Model.Character;

import java.util.ArrayList;
import java.util.Stack;

/**
 * The type Relic dependencies.
 */
public class RelicDependencies {
    /**
     * The Character.
     */
    Character character;
    /**
     * The Enemies.
     */
    ArrayList<Enemy> enemies;
    /**
     * The Effect stack.
     */
    Stack<Effect> effectStack;

    /**
     * Instantiates a new Relic dependencies.
     *
     * @param character the character
     */
    RelicDependencies(Character character){
        this.character = character;
        effectStack = null;
        enemies = null;
    }

    /**
     * Instantiates a new Relic dependencies.
     *
     * @param c the c
     * @param s the s
     * @param e the e
     */
    RelicDependencies(Character c, Stack<Effect> s, ArrayList<Enemy> e){
        character = c;
        effectStack = s;
        enemies = e;
    }

    /**
     * Gets enemies.
     *
     * @return the enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Get effect stack stack.
     *
     * @return the stack
     */
    public Stack<Effect> getEffectStack(){
        return effectStack;
    }

    /**
     * Gets character.
     *
     * @return the character
     */
    public Character getCharacter() {
        return character;
    }
}

