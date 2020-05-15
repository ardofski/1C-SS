package Model.Options;


import Model.Character;

public abstract class Option
{
    protected String description;
    public abstract void applyOption(Character character);
}
