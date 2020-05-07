package Controller.Fight;

import Model.*;
import Model.Character;
import Model.Effects.Effect;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CardDependencies {
    Enemy target;
    Pile handPile,discardPile,drawPile,exhaustPile;
    Character character;
    ArrayList<Enemy> enemies;
    public CardDependencies(Enemy t, PileCollection piles, Character c, ArrayList<Enemy> en){
        target=t;
        handPile=piles.getHandPile();
        discardPile=piles.getDiscardPile();
        drawPile=piles.getDrawPile();
        exhaustPile=piles.getExhaustPile();
        character=c;
        enemies = en;
    }

    //getters
    public Enemy getTarget(){
        return target;
    }

    public Pile getHandPile(){
        return handPile;
    }
    public Pile getDiscardPile(){
        return discardPile;
    }
    public Pile getDrawPile(){
        return drawPile;
    }
    public Pile getExhaustPile(){
        return exhaustPile;
    }

    public Character getCharacter(){return character;}
    public ArrayList<Enemy> getEnemies(){return enemies;}
}
