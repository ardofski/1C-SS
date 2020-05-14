package Model;

public class Ignore extends Option
{
    public Ignore()
    {
        this.description = " Do nothing and leave.";
    }

    @Override
    public void applyOption(Character character) {
        //Do nothing
    }
    @Override
    public String toString() {
        return "description: " + this.description;
    }
}
