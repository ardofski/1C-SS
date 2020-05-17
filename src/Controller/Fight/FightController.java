package Controller.Fight;

import Controller.RoomController;
import Model.*;
import Model.Buffs.BuffFactory;
import Model.Cards.CardFactory;
import Model.Character;
import Model.Effects.Block;
import Model.Effects.ChangeEnergy;
import Model.Room.EnemyRoom;
import Model.Room.Room;
import Model.Effects.Effect;

import java.util.ArrayList;
import java.util.Queue;

public class FightController extends RoomController {

    //instances
    private ArrayList<Enemy> enemies;
    private Integer turn;

    private PileCollection piles;
    private EffectHandler effectHandler;

    private EnemyController enemyController;

    Reward reward;

    boolean cardRewardGiven;
    boolean relicRewardGiven;
    boolean goldRewardGiven;
    boolean potRewardGiven;

    //Constructor
    public FightController(Character character, Room room) {
        super(character, room);
        cardRewardGiven = false;
        relicRewardGiven = false;
        goldRewardGiven = false;
        potRewardGiven = false;
        turn = 0;
        enemies = ((EnemyRoom)room).getEnemies();
        enemyController = new EnemyController(room,character);
        System.out.println( "number of cards in draw pile : " + character.getDeck().getCards().size() );
        piles = new PileCollection( new Pile(),character.getDeck().getClone() , new Pile( ) , new Pile());

        effectHandler = new EffectHandler(  enemies,enemyController,turn,3,piles,character);

        BuffFactory bF = new BuffFactory();
        //character.addBuff(bF.createBuff("Metallicize",5) );
        //character.getBuffs().getBuffs().get(0).setRemainingTurn(5);//character
     //   piles.getHandPile().addCard(CardFactory.getCard("PommelStrike"));
       // character.getBuffs().getBuffs().get(0).setX(5);

        start();

    }

    private void start(){
        character.fillEnergy();
        piles.getDrawPile().shuffle();
        for(int i = 1 ; i <= 5 ; i++ ){
            piles.drawCard();
        }

    }

    /**
    This function will apply the effects of given card to given enemy.
    card: card that is played to enemy.
    enemy: target of the played card.
     */
    public boolean playCard(Card card, Enemy enemy){
        if( isGameOver() )return false;
        if( card.isHasTarget() && !enemyController.hasEnemy(enemy) )return false;

        System.out.println("IN PLAYCARD METHOD");
        boolean b = effectHandler.playCard( card , enemy);
        System.out.println( "HAND CARDS : ");
        for( int i = 0 ; i < piles.getHandPile().getCards().size() ; i++ ){
            System.out.println( piles.getHandPile().getCards().get(i).getName() );
        }

        return b;
    }

    /**
    Apply the effects of given card.
    card: played card
     */
    public boolean playCard(Card card){
        boolean b = effectHandler.playCard( card , null);
        return b;
    }

    /**
     Finishes the current turn.
     */
    public void endTurn(){
        int block = character.getBlock();
        int energy = character.getEnergy();
        if( !isGameOver() ){
            effectHandler.endPlayerTurn();
            piles.handToDiscard();
            playEnemy();
            effectHandler.applyBlockEffect(new Block(-block,character));
            effectHandler.applyEnergyEffect(new ChangeEnergy(-energy+3));
            turn++;
            //character.fillEnergy();
            //TODO change draw cards system

            for(int i = 1 ; i <= 5 ; i++ ){
                piles.drawCard();
            }
        }
     //   System.out.println("haaaaaaaaaaaaaaaaaaaaaaaa"+character.getBlock());
    }

    /**
     Plays the enemies one by one in order and applies the effect of them.
     */
    public void playEnemy(){
      //  System.out.println("staaaaaaaaaaaaaaaaaaaaar"+character.getBlock());
        for(int i = 0 ; i < enemyController.getSize() ; i++){
            if (! enemies.get(i).isDead())
                effectHandler.playEnemy( i );
        }
        for( int i = 0 ; i < enemyController.getSize() ; i++){
            if (! enemies.get(i).isDead())
                effectHandler.endEnemyTurn(i);
        }

    }


    /**
     Returns the hand pile of the character.
     */
    public Pile getHandPile(){
        return piles.getHandPile();
    }

    public void setRoom(EnemyRoom eR)
    {
      room = eR;
    }

    /**
     Returns the discard pile of the character.
     */
    public Pile getDiscardPile(){
        return piles.getDiscardPile();
    }

    /**
     Returns the exhaust pile of the character.
     */
    public Pile getExhaustPile(){
        return piles.getExhaustPile();
    }

    /**
     Returns the draw pile of the character.
     */
    public Pile getDrawPile(){
        return piles.getDrawPile();
    }

    public EnemyRoom getEnemyRoom(){
        return (EnemyRoom) room;
    }

    public Character getCharacter(){
        return character;
    }

    /**
     Returns wether game is over or not.
     */
    public boolean isGameOver(){
        if( character.getHp() <= 0  ){
            System.out.println("GAME IS OVER BECAUSE CHARACTER DEAD");
            return true;
        }
        for (int i = 0 ; i < enemies.size() ; i++)
        {
            if(!enemies.get(i).isDead()){
                System.out.println("NOT ALL ENEMIES DEAD");
                return false;
            }
        }
        System.out.println("GAME IS OVER BECAUSE ALL ENEMIES ARE DEAD");
        return true;
    }

    public Reward getRewards(){
        reward = ((EnemyRoom)room).giveReward();
        return reward;
    }

    public boolean takeGoldReward(){
        if(!isGameOver() || character.getHp() <= 0 )return false;
        if( goldRewardGiven )return false;
        int gold = character.getGold();
        character.setGold( gold + reward.getGold() );
        goldRewardGiven=true;
        return true;
    }

    public boolean takeCardReward(int i){
        if(!isGameOver() || character.getHp() <= 0 )return false;
        if( cardRewardGiven )return false;
        Pile cPile = character.getDeck();
        Card c = reward.getCards().get(i);
        System.out.println("**IN takeCardReward, card name is : " + c.getName());
        character.getDeck().addCard(c);
        cardRewardGiven=true;
        return true;
    }

    public boolean takePotionReward(){
        if(!isGameOver() || character.getHp() <= 0 )return false;
        if( potRewardGiven )return false;
        boolean isAdded = character.addPotion( reward.getPot() );
        potRewardGiven=true;
        return isAdded;
    }

    public boolean takeRelicReward(){
        if(!isGameOver() || character.getHp() <= 0 )return false;
        if( relicRewardGiven )return false;
        character.getRelics().add( reward.getRelic() );
        relicRewardGiven=true;
        return true;
    }


    public void endGame(){

    }

    public boolean applyPotion( Potion potion){
        if(potion.isHasTarget() )return false;
        if(!character.hasPotion(potion))return false;
        effectHandler.playPotion( potion ,null);
        character.getPotions().remove(potion);
        return true;
    }

    public boolean applyPotion( Potion potion, Enemy enemy){
        if(!character.hasPotion(potion))return false;
        if( potion.isHasTarget() && !enemyController.hasEnemy(enemy) )return false;
        effectHandler.playPotion( potion ,enemy);
        character.getPotions().remove(potion);
        return true;

    }

    public ArrayList<Effect> getEnemyEffects(int index){
        return enemyController.seeEnemyEffect(index);
    }

    /**
     * applys the given effects in the fight
     * @param effects list of effects
     */
    private void applyEffect( Effect effects){
        //can be depracated, seems not neccesery.
    }

    public int getBlock(){
        return character.getBlock();
    }

    public int getEnergy(){
        return character.getEnergy();
    }


}
