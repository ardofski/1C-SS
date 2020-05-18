package Model.Buffs;

/**
 * The type Buff factory.
 */
public class BuffFactory {

    /**
     * Create buff buff.
     *
     * @param buffName the buff name
     * @param x        the x
     * @return the buff
     */
    public Buff createBuff(String buffName, int x){
        if( buffName.equals("Artifact"))return new Artifact(x);
        if( buffName.equals("Barricade"))return new Barricade();
        if( buffName.equals("Buffer"))return new Buffer(x);
        if( buffName.equals("Dexterity"))return new Dexterity(x);
        if( buffName.equals("DexterityDown"))return new DexterityDown(x);
        if( buffName.equals("DrawCard"))return new DrawCard(x);
        if( buffName.equals("Energized"))return new Energized(x);
        if( buffName.equals("Frail"))return new Frail(x);
       // if( buffName.equals("Intangible"))return new Intangible(x);
        if( buffName.equals("Metallicize"))return new Metallicize(x);
       // if( buffName.equals("NextTurnBlock"))return new NextTurnBlock(x);
        if( buffName.equals("NoDraw"))return new NoDraw(x);
        if( buffName.equals("PlatedArmor"))return new PlatedArmor(x);
        if( buffName.equals("Poison"))return new Poison(x);
        if( buffName.equals("Ritual"))return new Ritual(x);
        if( buffName.equals("Slow"))return new Slow(x);
        if( buffName.equals("Strength"))return new Strength(x);
        if( buffName.equals("Thorns"))return new Thorns(x);
        if( buffName.equals("Vigor"))return new Vigor(x);
        if( buffName.equals("Vulnerable"))return new Vulnerable(x);
        if( buffName.equals("Weak"))return new Weak(x);
        return null;

    }
}
