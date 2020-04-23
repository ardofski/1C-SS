package Test;

import Controller.Fight.FightController;
import Model.Card;
import Model.Cards.*;
import Model.Character;
import Model.Pile;
import Model.Room.EnemyRoom;
import Model.Room.Room;

import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.logging.Handler;

public class Main {

    public static void main(String[] args){
        Main.test();
    }

    public static void test(){
        Character character = new Character("Arda");
        character.setHp( 50 );
        Pile characterPile = new Pile();

        System.out.println( character );
        for( int i = 0 ; i < 5 ; i++){
            characterPile.addCard( new Anger("Anger", "Common","Attack","Red","description",1, false) );
        }
        for( int i = 0 ; i < 5 ; i++){
            characterPile.addCard( new Defend("Defend", "Common","Attack","Red","description",1, false) );
        }

        for( int i = 0 ; i < 5 ; i++){
            characterPile.addCard( new Bash("Bash", "Common","Attack","Red","description",1, false) );
        }

        characterPile.shuffle();
        character.setDeck( characterPile );

        Room room = new EnemyRoom(1);
        FightController fightController = new FightController(character,room);
        Pile handPile = fightController.getHandPile();
        Pile drawPile = fightController.getDrawPile();
        Pile discardPile = fightController.getDiscardPile();
        ArrayList<Card> cards;

        System.out.println( "HandPile : ");
        cards = handPile.getCards();
        for( int i = 0 ; i < cards.size() ; i++ ){
            System.out.println( cards.get(i).getName() );
        }

        System.out.println( "DiscardPile : ");
        cards = discardPile.getCards();
        for( int i = 0 ; i < cards.size() ; i++ ){
            System.out.println( cards.get(i).getName() );
        }

        System.out.println( "DrawPile : ");
        cards = drawPile.getCards();
        for( int i = 0 ; i < cards.size() ; i++ ){
            System.out.println( cards.get(i).getName() );
        }

        cards = fightController.getHandPile().getCards();

        System.out.println( "play card : " + cards.get(1).getName() );
        fightController.playCard( cards.get(1), null);
        System.out.println( "block : " + fightController.getBlock() );

        System.out.println( "play card : " + cards.get(1).getName() );
        fightController.playCard( cards.get(1), null);
        System.out.println( "block : " + fightController.getBlock() );

        System.out.println( "play card : " + cards.get(1).getName() );
        fightController.playCard( cards.get(1), null);
        System.out.println( "block : " + fightController.getBlock() );

        System.out.println( "energy : " + fightController.getEnergy() );
        fightController.endTurn();

        System.out.println( "HandPile : ");
        cards = handPile.getCards();
        for( int i = 0 ; i < cards.size() ; i++ ){
            System.out.println( cards.get(i).getName() );
        }

        System.out.println( "DiscardPile : ");
        cards = discardPile.getCards();
        for( int i = 0 ; i < cards.size() ; i++ ){
            System.out.println( cards.get(i).getName() );
        }

        System.out.println( "DrawPile : ");
        cards = drawPile.getCards();
        for( int i = 0 ; i < cards.size() ; i++ ){
            System.out.println( cards.get(i).getName() );
        }

        System.out.println( character );
    }
}
