package Controller.Fight;

import Model.Character;
import Model.Effects.Effect;
import Model.Enemy;
import Model.Pile;

import java.util.ArrayList;
import java.util.Stack;

public class BuffEffectManager {

    //instances
    private ArrayList<Enemy> enemies;
    private Integer turn, currentEnergy;
    private Integer block;
    private Pile handPile, drawPile, exhaustPile, discardPile;
    private Character character;
    private Stack<Effect> effectStack;

    public BuffEffectManager(ArrayList<Enemy> enemies,
                             Integer turn, Integer currentEnergy,
                             Pile handPile, Pile drawPile, Pile exhaustPile, Pile discardPile,
                             Character character,Stack<Effect> effectStack
    ){
        this.enemies = enemies;
        this.turn = turn;
        this.currentEnergy = currentEnergy;
        this.handPile = handPile;
        this.drawPile = drawPile;
        this.exhaustPile = exhaustPile;
        this.discardPile = discardPile;
        this.character = character;
        this.effectStack = effectStack;
    }

    public ArrayList<Effect> nextEffects(){
        Effect e = effectStack.peek();
        ArrayList<Effect> effects = new ArrayList<Effect>();



        return null;
    }

    public ArrayList<Effect> checkEnemyBuffs(Enemy enemy){

    }

    public ArrayLİst<Effect>
}
