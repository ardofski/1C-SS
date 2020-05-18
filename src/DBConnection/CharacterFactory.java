package DBConnection;

import Model.Cards.CardFactory;
import Model.Character;
import Model.Pile;
import Model.Potion;
import Model.Relics.*;

import java.util.ArrayList;

public class CharacterFactory {
    public static Character getCharacter(String name){
        Character character = new Character(name);
        Pile pile = new Pile();
        ArrayList<Relic> relics = new ArrayList<>();

        switch (name){
            case "Ironclad":
                character.setMaxHp(20);
                character.setHp(20);
                character.setColor("red");


                for(int i = 0; i < 4; i++)
                    pile.addCard(CardFactory.getCard("Defend",false));

                for(int i = 0; i < 6; i++)
                    pile.addCard(CardFactory.getCard("Strike",false));

                pile.addCard(CardFactory.getCard("Bash",false));

                character.setDeck(pile);
                /*
                relics.add(RelicFactory.getRelic("BurningBlood"));
                relics.add(RelicFactory.getRelic("BronzeScales"));
                relics.add(RelicFactory.getRelic("LizardTail"));
                relics.add(RelicFactory.getRelic("RedSkull"));
                relics.add(RelicFactory.getRelic("Vajra"));
                 */
                relics.add(RelicFactory.getRelic("Akabeko"));

                character.setRelics(relics);
                break;
            case "Silent":
                character.setMaxHp(270);
                character.setHp(270);
                character.setColor("green");
                for(int i = 0; i < 4; i++)
                    pile.addCard(CardFactory.getCard("Defend",false));
                for(int i = 0; i < 6; i++)
                    pile.addCard(CardFactory.getCard("Strike",false));
                //pile.addCard(CardFactory.getCard("Survivor"));
                //pile.addCard(CardFactory.getCard("Neutralize"));
                character.setDeck(pile);
                relics.add(RelicFactory.getRelic("RingOfTheSnake"));
                character.setRelics(relics);
                break;
            case "Defect":
                character.setMaxHp(75);
                character.setHp(75);
                character.setColor("blue");
                for(int i = 0; i < 4; i++)
                    pile.addCard(CardFactory.getCard("Defend",false));
                for(int i = 0; i < 4; i++)
                    pile.addCard(CardFactory.getCard("Strike",false));
                //pile.addCard(CardFactory.getCard("Zap"));
                //pile.addCard(CardFactory.getCard("Dualcast"));
                character.setDeck(pile);
                relics.add(RelicFactory.getRelic("CrackedCore"));
                character.setRelics(relics);
                break;
        }
        return character;
    }
}
