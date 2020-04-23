package Test;

import Controller.Fight.FightController;
import Model.Card;
import Model.Cards.*;
import Model.Character;
import Model.Enemy;
import Model.Pile;
import Model.Room.EnemyRoom;
import Model.Room.Room;
import Model.Room.RoomFactory;

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

        RoomFactory roomFactory = new RoomFactory();
        EnemyRoom room = roomFactory.getMonsterRooms().get(0);
        room.initialize();

        ArrayList<Enemy> enemies = room.getEnemies();
        System.out.println( "enemies are : " + enemies );

        FightController fightController = new FightController(character,room);
        Pile handPile = fightController.getHandPile();
        Pile drawPile = fightController.getDrawPile();
        Pile discardPile = fightController.getDiscardPile();
        ArrayList<Card> cards;

        printPile("hand", handPile );
        printPile("discard", discardPile );
        printPile("draw", drawPile );
        cards = fightController.getHandPile().getCards();

        System.out.println( "play card : " + cards.get(1).getName() );
        fightController.playCard( cards.get(1), enemies.get(0) );
        System.out.println( "block : " + fightController.getBlock() );

        System.out.println( "play card : " + cards.get(1).getName() );
        fightController.playCard( cards.get(1), enemies.get(0));
        System.out.println( "block : " + fightController.getBlock() );

        System.out.println( "play card : " + cards.get(1).getName() );
        fightController.playCard( cards.get(1), enemies.get(0));
        System.out.println( "block : " + fightController.getBlock() );

        System.out.println( "current energy : " + fightController.getEnergy() );

        System.out.println(( "END TURN..."));
        fightController.endTurn();

        printPile("hand", handPile );
        printPile("discard", discardPile );
        printPile("draw", drawPile );


        System.out.println( character );
    }

    public static void printPile( String s, Pile p){
        ArrayList<Card> cards;
        if( s.equals("hand") ){
            System.out.println( "HandPile : ");
            cards = p.getCards();
            for( int i = 0 ; i < cards.size() ; i++ ){
                System.out.println( cards.get(i).getName() );
            }
        }
        else if(s.equals("draw") ){
            System.out.println( "DrawPile : ");
            cards = p.getCards();
            for( int i = 0 ; i < cards.size() ; i++ ){
                System.out.println( cards.get(i).getName() );
            }
        }
        else if( s.equals("discard")){
            System.out.println( "DiscardPile : ");
            cards = p.getCards();
            for( int i = 0 ; i < cards.size() ; i++ ){
                System.out.println( cards.get(i).getName() );
            }
        }
    }
}
