package Model.Options;

/**
 * The type Option factory.
 */
public class OptionFactory
{
    /**
     * Gets option.
     *
     * @param optionName the option name
     * @return the option
     */
    public static Option getOption(String optionName)
    {
        System.out.println(optionName);
        if(optionName.equals("HealHp"))
            return new HealHp();
        if(optionName.equals("LoseRandomHp"))
            return new LoseRandomHp();
        if(optionName.equals("LoseRandomGold"))
            return new LoseRandomGold();
        if(optionName.equals("Ignore"))
            return new Ignore();
        if(optionName.equals("IncrementMaxHp"))
            return new IncrementMaxHp();
        if(optionName.equals("TakeGiveRandomCard"))
            return new TakeGiveRandomCard();
        if(optionName.equals("DuplicateCard"))
            return new DuplicateCard();
        if(optionName.equals("GiveRelicRandom"))
            return new GiveRelicRandom();

        return null;
    }
}
