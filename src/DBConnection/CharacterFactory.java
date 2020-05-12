package DBConnection;

import Model.Cards.CardFactory;
import Model.Character;
import Model.Pile;
import Model.Relics.*;

import java.util.ArrayList;

public class CharacterFactory {
    public static Character getCharacter(String name){
        Character character = new Character(name);
        Pile pile = new Pile();
        ArrayList<Relic> relics = new ArrayList<>();

        switch (name){
            case "Ironclad":
                character.setMaxHp(80);
                character.setHp(80);
                character.setColor("red");
                for(int i = 0; i < 4; i++)
                    pile.addCard(CardFactory.getCard("Defend"));
                for(int i = 0; i < 6; i++)
                    pile.addCard(CardFactory.getCard("Strike"));
                pile.addCard(CardFactory.getCard("Bash"));
                character.setDeck(pile);
                relics.add(RelicFactory.getRelic("BurningBlood"));
                character.setRelics(relics);
                break;
            case "Silent":
                character.setMaxHp(70);
                character.setHp(70);
                character.setColor("green");
                for(int i = 0; i < 4; i++)
                    pile.addCard(CardFactory.getCard("Defend"));
                for(int i = 0; i < 6; i++)
                    pile.addCard(CardFactory.getCard("Strike"));
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
                    pile.addCard(CardFactory.getCard("Defend"));
                for(int i = 0; i < 4; i++)
                    pile.addCard(CardFactory.getCard("Strike"));
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
