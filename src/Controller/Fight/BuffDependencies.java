package Controller.Fight;

import Model.Effects.Effect;
import Model.Enemy;
import Model.Character;
import Model.Fightable;

import java.util.Stack;

/**
 * The type Buff dependencies.
 */
public class BuffDependencies {
    /**
     * The Effect stack.
     */
    Stack<Effect> effectStack;
    /**
     * The Owner.
     */
    Fightable owner;

    /**
     * Instantiates a new Buff dependencies.
     *
     * @param owner the owner
     * @param s     the s
     */
    BuffDependencies(Fightable owner, Stack<Effect> s ){
        effectStack = s;
        this.owner = owner;
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
     * Gets owner.
     *
     * @return the owner
     */
    public Fightable getOwner() {
        return owner;
    }

}
