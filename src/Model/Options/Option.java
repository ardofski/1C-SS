package Model.Options;


import Model.Character;

public class Option
{
    protected String description;


    /**
     * this function is overwritten by each child option class
     * it basically uniqe tho the option, and applies the effects
     * of the option to the character
     * @param character
     */
    public void applyOption(Character character){

    }
    public String getDescription(){
        return description;
    }
}
