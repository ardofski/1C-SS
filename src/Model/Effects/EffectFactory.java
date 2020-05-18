package Model.Effects;

import Model.Buffs.Buff;
import Model.Buffs.BuffFactory;

/**
 * The type Effect factory.
 */
public class EffectFactory {

    /**
     * Clone effect effect.
     *
     * @param e the e
     * @return the effect
     */
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

    /**
     * Clone apply buff apply buff.
     *
     * @param applyBuff the apply buff
     * @return the apply buff
     */
    public ApplyBuff cloneApplyBuff(ApplyBuff applyBuff){
        BuffFactory buffFactory = new BuffFactory();
        Buff newBuff = buffFactory.createBuff( applyBuff.getBuff().getName() , applyBuff.getBuff().getX());
        return new ApplyBuff( newBuff, applyBuff.getTarget() );
    }

    /**
     * Clone block block.
     *
     * @param block the block
     * @return the block
     */
    public Block cloneBlock(Block block){
        return new Block(block.getBlock(), block.getTarget() );
    }

    /**
     * Clone change energy change energy.
     *
     * @param cE the c e
     * @return the change energy
     */
    public ChangeEnergy cloneChangeEnergy(ChangeEnergy cE){
        return new ChangeEnergy(cE.getEnergy());
    }

    /**
     * Clone damage damage.
     *
     * @param d the d
     * @return the damage
     */
    public Damage cloneDamage(Damage d){
        return new Damage(d.getDamage(),d.getTarget(),d.getSource());
    }

    /**
     * Clone draw card draw card.
     *
     * @param d the d
     * @return the draw card
     */
    public DrawCard cloneDrawCard(DrawCard d){
        return new DrawCard();
    }

    /**
     * Clone heal heal.
     *
     * @param h the h
     * @return the heal
     */
    public Heal cloneHeal(Heal h){
        return new Heal( h.getHealAmount() );
    }

    /**
     * Clone move card move card.
     *
     * @param mC the m c
     * @return the move card
     */
    public MoveCard cloneMoveCard(MoveCard mC){
        return new MoveCard(mC.getSourcePile(),mC.getDestPile(),mC.getCard() );
    }

    /**
     * Clone remove block remove block.
     *
     * @param rB the r b
     * @return the remove block
     */
    public RemoveBlock cloneRemoveBlock(RemoveBlock rB){
        return new RemoveBlock(rB.getTarget());
    }

    /**
     * Clone upgrade card upgrade card.
     *
     * @param uC the u c
     * @return the upgrade card
     */
    public UpgradeCard cloneUpgradeCard(UpgradeCard uC){
        return new UpgradeCard( uC.getCard() );
    }
}
