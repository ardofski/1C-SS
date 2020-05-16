package GUI;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import Controller.Fight.FightController;

import Model.Card;

import Controller.MenuController;
import Model.*;
import Model.Cards.Anger;
import Model.Cards.Bash;
import Model.Cards.Defend;

import Model.Character;
import Model.Room.EnemyRoom;
import Model.Room.RoomFactory;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;


class GameScene extends Parent {
	private MapScene mapScene;
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double x = screenBounds.getWidth(); //gets the screen width
    double y = screenBounds.getHeight(); //gets the screen height
    AudioClip menuSound = new AudioClip(new File("resources/sounds/menuMusic.wav").toURI().toString());
    private Pane root ;
	HealthBar charHP;
	HealthBar enemyHP;
	Character character;

	FightController fightController;
	Pile handPile;

	MenuButton btnEndTurn;
	Enemy enemies[];
	Enemy enemyToHit;
	Text energyNum;
	Text blockNum;
	HBox CardContainer;
	ImageView monsterImage;
	ImageView charImage;
	ImageView blockIconChar;
	ImageView blockIconEnemy;
	StackPane overlapBlock;
	ArrayList<Card> cards ;
	Text discardPileNum;
	Text drawPileCardNum;
	Text floorText;
	VBox LeftFightLevel;
	VBox RightFightLevel;
	HBox FightLevel;
	HBox charBuffs;
	HBox enemyBuffs;
	HBox[] enemiesBuffs;
	Pane pane ;
	HUDPane hudPane;
	int enemyNum ;
	HBox enemyStats;
	HBox[] enemiesStats;
	HealthBar[] enemyHPs;
	ImageView[] monsterImages;
   public GameScene(FightController fightController, MapScene mapScene, int floorNumber) {
		this.mapScene = mapScene;
   		this.fightController = fightController;
   	    character = fightController.getCharacter();
   	    hudPane = new HUDPane(character);
   	    hudPane.enableFloor(floorNumber);

	   enemyNum = fightController.getEnemyRoom().getEnemies().size();
	   enemies = new Enemy[enemyNum];
	   for (int i = 0 ; i < enemyNum ; i++)
		   enemies[i] = fightController.getEnemyRoom().getEnemies().get(i);

	   enemyToHit = enemies[0];

	    //fightController.setRoom(room);

	   handPile = fightController.getHandPile();
	   cards = handPile.getCards();
   	 
  	   Rectangle bg = new Rectangle(x,y);
       bg.setOpacity(0.1);
 
  	   //in form of vertical box and horizontal box.
        HBox LeftUpperLevel = new HBox(20);
        HBox RightUpperLevel = new HBox(30);
        HBox UpperLevelContainer = new HBox(290);

        LeftFightLevel = new VBox(1);


        FightLevel = new HBox(260);

        HBox LeftLowerLevel = new HBox(30);
        VBox RightLowerLevel = new VBox(50);

        HBox charStats = new HBox(5);

        enemiesStats= new HBox[enemyNum];

        for (int i =0 ; i < enemyNum ; i++)
		{
			enemyStats = new HBox(3);
			enemiesStats[i] = enemyStats;
		}

		charBuffs = new HBox(2);

        enemiesBuffs = new HBox[enemyNum];
        for (int i = 0 ; i < enemyNum ; i++)
		{
			enemyBuffs = new HBox(2);
			enemiesBuffs[i] = enemyBuffs;
		}


		HBox LowerLevelContainer= new HBox(60);
		CardContainer = new HBox(-35);

		pane = new Pane();

        UpperLevelContainer.setPrefWidth(x);
        UpperLevelContainer.setStyle("-fx-background-color: #808080;"+"-fx-opacity: 0.85;");
        //UpperLevel.setSpacing(400);

	    RightLowerLevel.setTranslateX(1100);
		RightLowerLevel.setTranslateY(480);


	   	CardContainer.setTranslateY(50);


	    LowerLevelContainer.setPrefWidth(x);
	    LowerLevelContainer.setTranslateX(50);
	    LowerLevelContainer.setTranslateY(480);

        FightLevel.setTranslateX(180);
        FightLevel.setTranslateY(265);

        //LeftLowerLevel.setTranslateX(80);
        //LeftLowerLevel.setTranslateY(550);

        //RightLowerLevel.setTranslateX(600);
	    //RightLowerLevel.setTranslateY(550);

	    InputStream is;
	    Image img;

	    BackgroundImage fightBG = null;

	   try {
		   is = Files.newInputStream(Paths.get("resources/images/bg"+Integer.toString((int)(Math.random()*9+1))+".png"));
		   img = new Image(is);
		   is.close(); //this is to give access other programs to that image as well.
		   fightBG = new BackgroundImage(img,
				   BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				   new BackgroundSize(1.0, 1.0, true, true, false, false));
	   } catch (IOException e) {
		   e.printStackTrace();
	   }

	   Background bag = new Background(fightBG);
	   pane.setBackground(bag);
	   pane.setPrefSize(x, y);

	    //LowerLevel of Game Scene Implementations
		//draw pile

	   ImageView drawPileIcon = null;
	   try {
		   is = Files.newInputStream(Paths.get("resources/images/drawPileIcon.png"));
		   img = new Image(is);
		   is.close(); //this is to give access other programs to that image as well.
		   drawPileIcon = new ImageView(img);
		   drawPileIcon.setFitWidth(70);
		   drawPileIcon.setFitHeight(150);
	   } catch (IOException e) {
		   e.printStackTrace();
	   } //get the image

		drawPileCardNum = new Text(Integer.toString(this.fightController.getDrawPile().getCards().size()));
	    System.out.println(this.fightController.getDrawPile().getCards());
	    drawPileCardNum.setFill(Color.WHITE);
	    drawPileCardNum.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 20));
		StackPane overlapDrawPile = new StackPane();
		overlapDrawPile.getChildren().addAll(drawPileIcon,drawPileCardNum);
	   	//energy
	    ImageView energyIcon = null;
	    try {
		   is = Files.newInputStream(Paths.get("resources/images/energyIcon.png"));
		   img = new Image(is);
		   is.close(); //this is to give access other programs to that image as well.
		   energyIcon = new ImageView(img);
		   energyIcon.setFitWidth(100);
		   energyIcon.setFitHeight(100);
	    } catch (IOException e) {
		   e.printStackTrace();
	    } //get the image
	   energyNum = new Text("3");
	   energyNum.setFill(Color.WHITE);
	   energyNum.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 20));
	   StackPane overlapEnergy= new StackPane();
	   overlapEnergy.getChildren().addAll(energyIcon,energyNum);

	    //cards
	   discardPileNum = new Text("0");



	   //enemy = fightController.getEnemyRoom().getEnemies().get(0);

	   System.out.println("ENEMY SIZE IS :"+enemyNum);
	   //System.out.println("Enemy ROOm enemy :: : : : : : ");

	   for(int i = 0 ; i < cards.size() ; i++)
	   {
		   CardImage cardImage;
	   	   Card card = cards.get(i);
		   cardImage = new CardImage(cards.get(i).getName(),cards.get(i).getType()
				   ,Integer.toString(cards.get(i).getEnergy()),cards.get(i).getDescription());

		   cardImage.setOnMouseClicked(event -> {
			   boolean isPlayable = this.fightController.playCard( card , enemyToHit);
			   //System.out.println("********************!!!!!!*****isPlayable: " + isPlayable );
			   if(isPlayable) {
				   if(this.fightController.isGameOver())
				   {
					   /*Text endGame = new Text("GAME FINISHED");
					   endGame.setFill(Color.WHITE);
					   endGame.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 50));
					   endGame.setX(420);
					   endGame.setY(350);*/
					   //pane.getChildren().addAll(endGame);


					   LootPane lootPane = new LootPane(fightController, hudPane);
					   lootPane.setTranslateX(400);
					   lootPane.setTranslateY(120);
					   pane.getChildren().addAll(lootPane);


					   MenuButton returnButton = new MenuButton("Return");
					   pane.getChildren().addAll(returnButton);
					   returnButton.setTranslateX(880);
					   returnButton.setTranslateY(480);


					   returnButton.setOnMouseClicked(event2 -> {
						   getChildren().remove(pane);



						   if(fightController.getCharacter().getHp() <= 0){
							   MainMenu.GameMenu menuScene = new MainMenu().new GameMenu();
							   InputStream as;
							   try {
								   as = Files.newInputStream(Paths.get("resources/images/background.jpg"));
								   Image img1 = new Image(as);
								   as.close(); //this is to give access other programs to that image as well.
								   BackgroundImage myBI= new BackgroundImage(img1,
										   BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
										   new BackgroundSize(1.0, 1.0, true, true, false, false));
								   //then you set to your node
								   pane.setBackground(new Background(myBI));
							   } catch (IOException e) {
								   // TODO Auto-generated catch block
								   e.printStackTrace();
							   } //get the image of background

							   getChildren().remove(mapScene);
							   getChildren().add(menuScene);
						   }
						   else
							   getChildren().add(mapScene);
					   });
					   btnEndTurn.setVisible(false);
				   }

				   for (int j = 0 ; j < enemyNum ; j++)
				   {
				   		if(enemies[j].getHp() <= 0)
						{
							monsterImages[j].setVisible(false);
							enemiesBuffs[j].setVisible(false);
							enemiesStats[j].setVisible(false);
						}
				   }

				   //System.out.println("CARD ---------->>>>>>>>>>\n\n\n   "+card);
				   //System.out.println("ENEMY IS ---->>"+enemy);
				   //System.out.println("ENEMY IS ---->>"+fightController.getEnemyRoom().getEnemies().get(0));
				   for(int j = 0 ; j < enemyNum ; j++)
				   {
					   enemyHPs[j].setValue((enemies[j].getHp() / (enemies[j].getMaxHp() * 1.0)), enemies[j].getHp());
				   }

				   CardContainer.getChildren().remove(cardImage);
				   energyNum.setText(Integer.toString(this.fightController.getEnergy() ) );
				   manageBuffs(character);
				   for(int j = 0 ; j < enemyNum ; j++)
				   {
					   manageBuffs(enemies[j]);
				   }
				   discardPileNum.setText(Integer.toString(this.fightController.getDiscardPile().getCards().size() ) );
				   drawPileCardNum.setText(Integer.toString(this.fightController.getDrawPile().getCards().size()));
				   blockNum.setText( Integer.toString(this.fightController.getBlock() ) );
				   //System.out.println("-------------------------BLOCK TEST--------------------");
				   //System.out.println("Block Size : "+ this.fightController.getBlock());
				   if(this.fightController.getBlock() != 0 )
				   {
				   	 //System.out.println("Setting visibility true");
				   	 overlapBlock.setVisible(true);
				   }

			   }
	  	 	});

		   CardContainer.getChildren().add(cardImage);
	   }



	    //discard pile
	   ImageView discardPileIcon = null;
	   try {
		   is = Files.newInputStream(Paths.get("resources/images/discardPileIcon.png"));
		   img = new Image(is);
		   is.close(); //this is to give access other programs to that image as well.
		   discardPileIcon = new ImageView(img);
		   discardPileIcon.setFitWidth(70);
		   discardPileIcon.setFitHeight(150);
	   } catch (IOException e) {
		   e.printStackTrace();
	   } //get the image

	   discardPileNum.setFill(Color.WHITE);
	   discardPileNum.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 20));
	   StackPane overlapDiscardPile= new StackPane();
	   overlapDiscardPile.getChildren().addAll(discardPileIcon,discardPileNum);

		btnEndTurn = new MenuButton("End Turn");

		btnEndTurn.setOnMouseClicked(event -> {
		   //CONTROLLER END TURN
			  //	monsterImage.setTranslateX(-400);
			   CardContainer.getChildren().removeAll(CardContainer.getChildren());
			   this.fightController.endTurn();
			   charHP.setValue((character.getHp() / (character.getMaxHp() * 1.0)), character.getHp());
			   if(this.fightController.isGameOver())
			   {
				   /*Text endGame = new Text("GAME FINISHED");
				   endGame.setFill(Color.WHITE);
				   endGame.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 50));
				   endGame.setX(420);
				   endGame.setY(350);
				   pane.getChildren().addAll(endGame);*/

				   LootPane lootPane = new LootPane(fightController, hudPane);
				   lootPane.setTranslateX(400);
				   lootPane.setTranslateY(120);
				   pane.getChildren().addAll(lootPane);

				   MenuButton returnButton = new MenuButton("Return");
				   pane.getChildren().addAll(returnButton);

				   returnButton.setTranslateX(880);
				   returnButton.setTranslateY(480);

				   returnButton.setOnMouseClicked(event2 -> {
					   getChildren().remove(pane);
					   if(fightController.getCharacter().getHp() <= 0){
						   MainMenu.GameMenu menuScene = new MainMenu().new GameMenu();
						   InputStream as;
						   try {
							   as = Files.newInputStream(Paths.get("resources/images/background.jpg"));
							   Image img1 = new Image(as);
							   as.close(); //this is to give access other programs to that image as well.
							   BackgroundImage myBI= new BackgroundImage(img1,
									   BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
									   new BackgroundSize(1.0, 1.0, true, true, false, false));
							   //then you set to your node
							   pane.setBackground(new Background(myBI));
						   } catch (IOException e) {
							   // TODO Auto-generated catch block
							   e.printStackTrace();
						   } //get the image of background

						   getChildren().remove(mapScene);
						   getChildren().add(menuScene);
					   }
					   else
						   getChildren().add(mapScene);
				   });
				   btnEndTurn.setVisible(false);
			   }
			    manageBuffs(character);

				for(int i = 0 ; i < enemyNum ; i++)
				{
					manageBuffs(enemies[i]);
				}

			   	hudPane.updateHP();
				drawPileCardNum.setText(Integer.toString(this.fightController.getDrawPile().getCards().size()));
				discardPileNum.setText(Integer.toString(this.fightController.getDiscardPile().getCards().size() ) );
			    blockNum.setText( Integer.toString(this.fightController.getBlock() ) );
				energyNum.setText(Integer.toString(this.fightController.getEnergy() ) );
				if(this.fightController.getBlock() == 0 )
				{
					overlapBlock.setVisible(false);
				}
			   dealtCards();

	   	});
  		 
  		 //CHARACTER AND ENEMIES
  		  charImage = null;
  		try {
		   is = Files.newInputStream(Paths.get("resources/images/character"+character.getName()+".png"));
		   img = new Image(is);
         is.close(); //this is to give access other programs to that image as well.
         charImage = new ImageView(img);
         charImage.setFitWidth(275);
         charImage.setFitHeight(200);
  		} catch (IOException e) {
  			e.printStackTrace();
  		} //get the image  


	   DropShadow drop = new DropShadow(50, Color.WHITE);
	   drop.setInput(new Glow());

	   monsterImages = new ImageView[enemyNum];
	   monsterImage = null;
	   for(int i = 0 ; i < enemyNum ; i++) {

		   try {
			   is = Files.newInputStream(Paths.get("resources/images/monsterImage.png"));
			   img = new Image(is);
			   is.close(); //this is to give access other programs to that image as well.
			   monsterImage = new ImageView(img);
			   monsterImage.setFitWidth(275);
			   monsterImage.setFitHeight(200);
			   monsterImage.setId(""+i);
			   int cnt = i ;
				//System.out.println("MONSTER IMAGE ID: "+monsterImage.getId());
			   monsterImage.setOnMouseClicked(event -> {
				   enemyToHit = enemies[cnt];
				   //monsterImage.setEffect(drop);
				   //System.out.println("MONSTER IMAGE ID: "+monsterImage.getId());
				   //System.out.println("*******Adding effect to "+monsterImage.getEffect().toString());
				   for(int j = 0; j < enemyNum ; j++)
				   {
					   if(enemies[j] == enemyToHit) {
						   monsterImages[j].setEffect(drop);
						   System.out.println("*******Adding effect of ID "+monsterImages[j].getId());
					   }
				   }

				   for(int j = 0; j < enemyNum ; j++)
				   {
					   if(enemies[j] != enemyToHit) {
						   monsterImages[j].setEffect(null);
						   System.out.println("*******Removing effect of ID "+monsterImages[j].getId());
					   }
				   }

			   });

			   monsterImages[i] = monsterImage;
		   } catch (IOException e) {
			   e.printStackTrace();
		   } //get the image
	   }

	   // Fixed enemy to hit is enemy 0.
	   monsterImages[0].setEffect(drop);

	   charHP = new HealthBar(character.getHp());
	   charHP.setValue((character.getHp() / (character.getMaxHp() * 1.0)), character.getHp());
	   enemyHPs = new HealthBar[enemyNum];
	   for (int i = 0 ; i < enemyNum ; i++)
	   {
		   enemyHP = new HealthBar(enemies[i].getHp());
		   enemyHPs[i] = enemyHP;
	   }


	   blockIconChar = null;
	   try {
		   is = Files.newInputStream(Paths.get("resources/images/blockIcon.png"));
		   img = new Image(is);
		   is.close(); //this is to give access other programs to that image as well.
		   blockIconChar = new ImageView(img);
		   blockIconChar.setFitWidth(25);
		   blockIconChar.setFitHeight(25);
	   } catch (IOException e) {
		   e.printStackTrace();
	   } //get the image

	   blockNum = new Text();
	   blockNum.setText(Integer.toString(0) );
	   blockNum.setFill(Color.WHITE);
	   blockNum.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 12));

	   overlapBlock = new StackPane();
	   overlapBlock.getChildren().addAll(blockIconChar,blockNum);
	   overlapBlock.setVisible(false);

  		charStats.getChildren().addAll(overlapBlock,charHP);

  		for(int i = 0 ; i < enemyNum ; i++) {
  			enemiesStats[i].getChildren().addAll(enemyHPs[i]);
			if(i == 0)
				enemiesStats[i].setTranslateX(150);
		}


  		charStats.setTranslateX(120);
  		enemyStats.setTranslateX(120);

  		  Text characterName = new Text(character.getClass().toString());
 		  characterName.setFill(Color.WHITE);
 		  characterName.setFont(Font.font ("COMIC SANS MS", 18));






	  	  floorText = new Text();
	  	  floorText.setText("Floor "+  floorNumber);
	  	  floorText.setFill(Color.YELLOW);
	  	  floorText.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 22));
	  	  floorText.setTranslateY(5);
	  	  floorText.setTranslateX(-130);


	  	  manageBuffs(character);

	  	  for(int i = 0 ; i < enemyNum ; i++)
		  {
			  manageBuffs(enemies[i]);
		  }


	  	  LeftFightLevel.getChildren().addAll(charImage,charStats,charBuffs);

	  	  HBox RightFightLevelContainer = new HBox(5);

	  	  for (int i = 0 ; i < enemyNum ; i++)
		  {
			  RightFightLevel = new VBox(1);
			  RightFightLevel.getChildren().addAll(monsterImages[i],enemiesStats[i],enemiesBuffs[i]);
			  RightFightLevelContainer.getChildren().addAll(RightFightLevel);
		  }

	  	  FightLevel.getChildren().addAll(LeftFightLevel,RightFightLevelContainer);

 		  /*LeftUpperLevel.getChildren().addAll(characterName,hp,hpText,gold,goldText,potion);
 		  RightUpperLevel.getChildren().addAll(map,overlapDeck,settings);
 		  UpperLevelContainer.getChildren().addAll(LeftUpperLevel,floorText,RightUpperLevel);*/

 		  //UpperLevelContainer.getChildren().addAll(new HUDPane(fightController));

 		  LeftLowerLevel.getChildren().addAll(overlapDrawPile,overlapEnergy);
 		  RightLowerLevel.getChildren().addAll(btnEndTurn,overlapDiscardPile);
 		  LowerLevelContainer.getChildren().addAll(LeftLowerLevel,CardContainer);

 		  //DELETE AFTER TRYOUT
	   	  /*String[] a = new String[1];
	   	  a[0] = "Leave";
	      EventImage ei = new EventImage("Mind Bloom","Hail the King!",a);
          */
 		  pane.getChildren().addAll(hudPane,FightLevel,LowerLevelContainer,RightLowerLevel);

 		  getChildren().addAll(pane);


   }

   public void manageBuffs( Character ch )
	{
		charBuffs.getChildren().clear();
		ArrayList<Buff> buffs = ch.getBuffs().getBuffs();
		for(int i = 0 ; i < buffs.size() ; i++)
		{
			//System.out.println("BUFF NAME IN MANAGE BUFF(character) METHOD: "+buffs.get(i).getName());
			String buffName = buffs.get(i).getName();
			InputStream is;
			Image img;
			ImageView buffIcon = null;
			try {
				is = Files.newInputStream(Paths.get("resources/images/buff"+buffName+".png"));
				img = new Image(is);
				is.close(); //this is to give access other programs to that image as well.
				buffIcon = new ImageView(img);
				buffIcon.setFitWidth(25);
				buffIcon.setFitHeight(25);
			} catch (IOException e) {
				e.printStackTrace();
			} //get the image

			Text buffDesc = new Text(buffs.get(i).getName());
			buffDesc.setFill(Color.WHITE);
			buffDesc.setFont(Font.font ("Verdana", 12));
			buffIcon.setOnMouseEntered(event -> {
				//System.out.println("BUFF IS PRINTED********");
				Robot robot = new Robot();
				int y = (int) (robot.getMouseY() -25);
				int x = (int) (robot.getMouseX() +25);
				buffDesc.setX(x);
				buffDesc.setY(y);
				buffDesc.setVisible(true);
				getChildren().add(buffDesc);

			});

			buffIcon.setOnMouseExited(event -> {
				Robot robot = new Robot();
				buffDesc.setVisible(false);
				getChildren().remove(buffDesc);
			});

			charBuffs.setTranslateX(260);
			charBuffs.setTranslateY(-75);

			charBuffs.getChildren().add(buffIcon);

		}
	}

	public void manageBuffs( Enemy ch )
	{
		enemyBuffs.getChildren().clear();
		ArrayList<Buff> buffs = ch.getBuffs().getBuffs();
		for(int i = 0 ; i < buffs.size() ; i++)
		{
			//System.out.println("BUFF NAME IN MANAGE BUFF(enemy) METHOD: "+buffs.get(i).getName());
			String buffName = buffs.get(i).getName();
			InputStream is;
			Image img;
			ImageView buffIcon = null;
			try {
				is = Files.newInputStream(Paths.get("resources/images/buff"+buffName+".png"));
				img = new Image(is);
				is.close(); //this is to give access other programs to that image as well.
				buffIcon = new ImageView(img);
				buffIcon.setFitWidth(25);
				buffIcon.setFitHeight(25);
			} catch (IOException e) {
				e.printStackTrace();
			} //get the image
			//System.out.println("**********BUFF DESCRIPTION : "+buffs.get(i).getDescription());

			Text buffDesc = new Text(buffs.get(i).getDescription());
			buffDesc.setFill(Color.WHITE);
			buffDesc.setFont(Font.font ("Verdana", 12));
			buffIcon.setOnMouseEntered(event -> {
				//System.out.println("BUFF IS PRINTED********");
				Robot robot = new Robot();
				int y = (int) (robot.getMouseY() -25);
				int x = (int) (robot.getMouseX() +15);
				buffDesc.setX(x);
				buffDesc.setY(y);
				buffDesc.setVisible(true);
				getChildren().add(buffDesc);

			});

			buffIcon.setOnMouseExited(event -> {
				buffDesc.setVisible(false);
				getChildren().remove(buffDesc);
			});

			//enemyBuffs.setTranslateX(100);
			//enemyBuffs.setTranslateY(0);
			enemyBuffs.setTranslateX(110);
			enemyBuffs.setTranslateY(-250);
			enemyBuffs.getChildren().add(buffIcon);

		}

	}

 public void dealtCards(){
   	 ArrayList<Card> cards = handPile.getCards();
	 for(int i = 0 ; i < cards.size() ; i++)
	 {
		 CardImage cardImage;
		 Card card = cards.get(i);
		 cardImage = new CardImage(cards.get(i).getName(),cards.get(i).getType()
				 ,Integer.toString(cards.get(i).getEnergy()),cards.get(i).getDescription());

		 cardImage.setOnMouseClicked(event -> {
			 //CONTROLLER CARD CLICKED
			 boolean isPlayable = fightController.playCard( card , enemyToHit);
			 if(isPlayable) {
				 if(this.fightController.isGameOver())
				 {
					 /*Text endGame = new Text("GAME FINISHED");
					 endGame.setFill(Color.WHITE);
					 endGame.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 50));
					 endGame.setX(420);
					 endGame.setY(350);
					 pane.getChildren().addAll(endGame);*/

					 LootPane lootPane = new LootPane(fightController, hudPane);
					 pane.getChildren().addAll(lootPane);
					 lootPane.setTranslateX(400);
					 lootPane.setTranslateY(120);

					 MenuButton returnButton = new MenuButton("Return");
					 pane.getChildren().addAll(returnButton);

					 System.out.println("REMOVING BTN END TURN");
					 returnButton.setTranslateX(880);
					 returnButton.setTranslateY(480);
					 returnButton.setOnMouseClicked(event2 -> {
						 getChildren().remove(pane);
						 if(fightController.getCharacter().getHp() <= 0){
							 MainMenu.GameMenu menuScene = new MainMenu().new GameMenu();
							 InputStream as;
							 try {
								 as = Files.newInputStream(Paths.get("resources/images/background.jpg"));
								 Image img1 = new Image(as);
								 as.close(); //this is to give access other programs to that image as well.
								 BackgroundImage myBI= new BackgroundImage(img1,
										 BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
										 new BackgroundSize(1.0, 1.0, true, true, false, false));
								 //then you set to your node
								 pane.setBackground(new Background(myBI));
							 } catch (IOException e) {
								 // TODO Auto-generated catch block
								 e.printStackTrace();
							 } //get the image of background

							 getChildren().remove(mapScene);
							 getChildren().add(menuScene);
						 }
						 else
						 	getChildren().add(mapScene);
					 });
					 btnEndTurn.setVisible(false);
				 }

				 for (int j = 0 ; j < enemyNum ; j++)
				 {
					 if(enemies[j].getHp() <= 0)
					 {
						 monsterImages[j].setVisible(false);
						 enemiesBuffs[j].setVisible(false);
						 enemiesStats[j].setVisible(false);
					 }
				 }

				 //System.out.println("CARD ---------->>>>>>>>>>\n\n\n   "+card);
				 //System.out.println("ENEMY IS ---->>"+enemy);
				 //System.out.println("ENEMY IS ---->>"+fightController.getEnemyRoom().getEnemies().get(0));
				 for (int j = 0 ; j < enemyNum ; j++ )
				 {
					 enemyHPs[j].setValue((enemies[j].getHp() / (enemies[j].getMaxHp() * 1.0)), enemies[j].getHp());
				 }

				 CardContainer.getChildren().remove(cardImage);
				 manageBuffs(character);

				 for (int j = 0 ; j < enemyNum ; j++ )
				 {
				 	manageBuffs(enemies[j]);
				 }

				 energyNum.setText(Integer.toString(this.fightController.getEnergy() ) );
				 discardPileNum.setText(Integer.toString(this.fightController.getDiscardPile().getCards().size() ) );
				 drawPileCardNum.setText(Integer.toString(this.fightController.getDrawPile().getCards().size()));
				 blockNum.setText( Integer.toString(this.fightController.getBlock() ) );
				 //System.out.println("-------------------------BLOCK TEST--------------------");
				 //System.out.println("Block Size : "+ this.fightController.getBlock());
				 if(this.fightController.getBlock() != 0 )
				 {
					 //System.out.println("Setting visibility true");
					 overlapBlock.setVisible(true);
				 }
			 }
		 });

		 CardContainer.getChildren().add(cardImage);
	 }

 }



	public static class MenuButton extends StackPane {
		private Text text;

		public MenuButton(String name) {
			text = new Text(name);
			text.setFont(text.getFont().font(20));
			text.setFill(Color.WHITE);

			Rectangle bg = new Rectangle(200, 30);
			bg.setOpacity(0.6);
			bg.setFill(Color.BLACK);
			bg.setEffect(new GaussianBlur(3.5));

			setAlignment(Pos.CENTER_LEFT);
			setRotate(-0.5);
			getChildren().addAll(bg, text);

			setOnMouseEntered(event -> {
				bg.setTranslateX(10);
				text.setTranslateX(10);
				bg.setFill(Color.WHITE);
				text.setFill(Color.BLACK);
			});

			setOnMouseExited(event -> {
				bg.setTranslateX(0);
				text.setTranslateX(0);
				bg.setFill(Color.BLACK);
				text.setFill(Color.WHITE);
			});

			DropShadow drop = new DropShadow(50, Color.WHITE);
			drop.setInput(new Glow());

			setOnMousePressed(event -> setEffect(drop));
			setOnMouseReleased(event -> setEffect(null));
		}
	}

}
