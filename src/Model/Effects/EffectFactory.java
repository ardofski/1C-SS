package Model.Effects;

import Model.Buff;
import Model.Buffs.BuffFactory;
import Model.Relics.Relic;

public class EffectFactory {

    public Effect cloneEffect(Effect e){
        if( e instanceof ApplyBuff)return cloneApplyBuff((ApplyBuff)e);
        if( e instanceof Block)return cloneBlock((Block)e);
        if( e instanceof ChangeEnergy)return cloneChangeEnergy((ChangeEnergy)e);
        if( e instanceof Damage)return cloneDamage((Damage)e);
        if( e instanceof DrawCard)return cloneDrawCard((DrawCard)e);
        if( e instanceof Heal)return cloneHeal((Heal)e);
        if( e instanceof MoveCard)return cloneMoveCard((MoveCard)e);
        if( e instanceof RemoveBlock)return cloneRemoveBlock((RemoveBlock)e);
        if( e instanceof UpgradeCard)return cloneUpgradeCard((UpgradeCard)e);
        return null;
    }

    public ApplyBuff cloneApplyBuff(ApplyBuff applyBuff){
        BuffFactory buffFactory = new BuffFactory();
        Buff newBuff = buffFactory.createBuff( applyBuff.getBuff().getName() , applyBuff.getBuff().getX());
        return new ApplyBuff( newBuff, applyBuff.getTarget() );
    }

    public Block cloneBlock(Block block){
        return new Block(block.getBlock(), block.getTarget() );
    }

    public ChangeEnergy cloneChangeEnergy(ChangeEnergy cE){
        return new ChangeEnergy(cE.getEnergy());
    }

    public Damage cloneDamage(Damage d){
        return new Damage(d.getDamage(),d.getTarget(),d.getSource());
    }

    public DrawCard cloneDrawCard(DrawCard d){
        return new DrawCard();
    }

    public Heal cloneHeal(Heal h){
        return new Heal( h.getHealAmount() );
    }

    public MoveCard cloneMoveCard(MoveCard mC){
        return new MoveCard(mC.getSourcePile(),mC.getDestPile(),mC.getCard() );
    }

    public RemoveBlock cloneRemoveBlock(RemoveBlock rB){
        return new RemoveBlock(rB.getTarget());
    }

    public UpgradeCard cloneUpgradeCard(UpgradeCard uC){
        return new UpgradeCard( uC.getCard() );
    }
}
