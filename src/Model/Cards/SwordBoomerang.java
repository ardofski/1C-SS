package Model.Cards;

import Controller.Fight.CardDependencies;
import Model.Card;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;

import java.util.ArrayList;

public class SwordBoomerang extends Card {
    public SwordBoomerang( boolean upgrade) {
        super(upgrade,false);
        name = "SwordBoomerang";
        rarity = "Common";
        type = "Attack";
        color = "Red";
        description = "Deal 3 damage to a random enemy 3 times.";
        energy = 1;
    }
    public void upgrade(){
        super.upgrade();
        description = "Deal 3 damage to a random enemy 4 times.";
    }

    /*
        Deal 3 damage to a random enemy 3(4) times.
    */
    public ArrayList<Effect> play(CardDependencies dep){
        ArrayList<Effect> effects = new ArrayList<Effect>();
        Effect effect;

        int enemyIndex = (int)( (Math.random())*(dep.getEnemies().size()) );
        Enemy enemy = dep.getEnemies().get(enemyIndex);

        if( upgrade ){
            for( int i = 1 ; i <= 4; i++){
                effect = new Damage(3,enemy,dep.getCharacter());
                effects.add(effect);
            }
        }
        else{
            for( int i = 1 ; i <= 3; i++){
                effect = new Damage(3,enemy,dep.getCharacter());
                effects.add(effect);
            }
        }

        return effects;
    }

}
