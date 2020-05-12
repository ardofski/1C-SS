package Controller.Fight;

import Model.*;
import Model.Character;
import Model.Effects.Effect;

import java.util.ArrayList;

public class CardEffectManager {

    //instances
    private ArrayList<Enemy> enemies;
    private Integer turn, currentEnergy;
    private Integer block;
    private PileCollection piles;
    private Character character;

    public CardEffectManager(ArrayList<Enemy> enemies,
                         Integer turn, Integer currentEnergy,
                         PileCollection piles,
                         Character character
    ){
        this.enemies = enemies;
        this.turn = turn;
        this.currentEnergy = currentEnergy;
        this.piles = piles;
        this.character = character;
    }

    public ArrayList<Effect> getEffects(Card card, Enemy target){
        CardDependencies dependencies = new CardDependencies(target,piles,character,enemies);
        return card.play( dependencies );
        /*
        if(card instanceof Anger){
            Anger castedCard = (Anger)card;
            return castedCard.getEffects(target, handPile);
        }
        else if( card instanceof Armaments){
            Armaments castedCard = (Armaments)card;
            return castedCard.getEffects(target,handPile);
        }
        else if( card instanceof Bash){
            Bash castedCard = (Bash)card;
            return castedCard.getEffects(target);
        }
        else if( card instanceof BodySlam){
            BodySlam castedCard = (BodySlam)card;
            return castedCard.getEffects(target,block);
        }
        else if(card instanceof Defend){
            Defend castedCard = (Defend)card;
            return castedCard.getEffects(target);
        }
        else if(card instanceof Strike){
            Strike castedCard = (Strike)card;
            return castedCard.getEffects(target);
        }
        return null;
        */

    }


}
