package Model.Buffs;

import Controller.Fight.EffectHandler;
import Model.Buff;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Effects.MoveCard;

import java.util.Stack;

public class DrawCard extends Buff {
    int x;
    public DrawCard(String name, int x) {
        super(name);
        this.x = x;
    }

    public void run(Stack<Effect> s, Stack<Effect> ns ){
        MoveCard drawCard;
        while( x-- != 0 ){
            drawCard = new MoveCard(null ,null,null);
            ns.push();
        }
    }
}
