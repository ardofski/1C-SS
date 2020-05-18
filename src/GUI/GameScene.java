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
import Model.Effects.ApplyBuff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Room.EnemyRoom;
import Model.Room.RoomFactory;
import javafx.geometry.Insets;
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
	MenuButton btnUsePotion;
	Enemy enemies[];
	Enemy enemyToHit;
	Text energyNum;
	Text blockNum;
	Text blockNumEn;
	HBox CardContainer;
	ImageView monsterImage;
	ImageView charImage;
	ImageView blockIconChar;
	ImageView blockIconEnemy;
	StackPane overlapBlock;
	StackPane overlapBlockEnemy;
	StackPane[] overlapBlockEnemies;
	Text[] blockNumEnemies;
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
	boolean finalRoom;
	HBox enemyStats;
	HBox[] enemiesStats;
	HBox enemyPurpose;
	HBox[] enemiesPurposes;
	HealthBar[] enemyHPs;
	ImageView[] monsterImages;
	GridPane cardCollection;
   public GameScene(FightController fightController, MapScene mapScene, int floorNumber) {
		this.mapScene = mapScene;
		this.fightController = fightController;
	   	finalRoom = fightController.isFinalRoom();
   	    character = fightController.getCharacter();
   	    hudPane = new HUDPane(character);
   	    hudPane.enableFloor(floorNumber);

	   enemyNum = fightController.getEnemyRoom().getEnemies().size();
	   enemies = new Enemy[enemyNum];

	   for (int i = 0 ; i < enemyNum ; i++)
		   enemies[i] = fightController.getEnemyRoom().getEnemies().get(i);

	   if ( enemyNum > 0 )
	   	 enemyToHit = enemies[0];

	    //fightController.setRoom(room);

	   DropShadow drop = new DropShadow(50, Color.WHITE);
	   drop.setInput(new Glow());

	   handPile = fightController.getHandPile();
	   cards = handPile.getCards();
   	 
  	   Rectangle bg = new Rectangle(x,y);
       bg.setOpacity(0.1);

	   cardCollection = new GridPane();
	   cardCollection.setHgap(10);
	   cardCollection.setVgap(10);
	   cardCollection.setPadding(new Insets(0, 10, 0, 10));
 
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

        enemiesPurposes = new HBox[enemyNum];
	   for (int i = 0 ; i < enemyNum ; i++)
	   {
		   enemyPurpose = new HBox(-1);
		   enemiesPurposes[i] = enemyPurpose;
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
		   is = Files.newInputStream(Paths.get("resources/images/bg"+Integer.toString((int)(Math.random()*12))+".png"));
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


	   overlapDrawPile.setOnMouseEntered(event -> {
		   overlapDrawPile.setTranslateY(-30);
		   overlapDrawPile.setEffect(drop);
	   });

	   overlapDrawPile.setOnMouseExited(event -> {
		   overlapDrawPile.setTranslateY(0);
		   overlapDrawPile.setEffect(null);
	   });

	   overlapDrawPile.setOnMousePressed(event -> overlapDrawPile.setEffect(drop));

	   overlapDrawPile.setOnMouseReleased(event -> overlapDrawPile.setEffect(null));

		overlapDrawPile.setOnMouseClicked(event -> {
			cardCollection.getChildren().clear();
			ArrayList<Card> cards = fightController.getDrawPile().getCards();
			CardImage card;
			int horizontal = 6;
			for(int i = 0 ; i < fightController.getDrawPile().getCards().size() ; i++)
			{
				card = new CardImage(cards.get(i));
				if(cards.get(i).getUpgrade()) {
					card.cardEnergy.setTranslateX(15);
				}
				cardCollection.add(card, i % horizontal,i / horizontal);
			}
			cardCollection.setTranslateX(150);
			cardCollection.setTranslateY(70);
			getChildren().addAll(cardCollection);


			MainMenu.MenuButton returnFight = new MainMenu.MenuButton("Return Fight");
			returnFight.setTranslateY(450);
			returnFight.setTranslateX(5);
			getChildren().addAll(returnFight);
			returnFight.setOnMouseClicked(event2 -> {
				getChildren().removeAll(returnFight,cardCollection);

			});
		});
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




	   System.out.println("ENEMY SIZE IS :"+enemyNum);

		dealtCards();


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

	   discardPileNum = new Text("0");
	   discardPileNum.setFill(Color.WHITE);
	   discardPileNum.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 20));
	   StackPane overlapDiscardPile= new StackPane();
	   overlapDiscardPile.getChildren().addAll(discardPileIcon,discardPileNum);

	   overlapDiscardPile.setOnMouseEntered(event -> {
		   overlapDiscardPile.setTranslateY(-30);
		   overlapDiscardPile.setEffect(drop);
	   });

	   overlapDiscardPile.setOnMouseExited(event -> {
		   overlapDiscardPile.setTranslateY(0);
		   overlapDiscardPile.setEffect(null);
	   });

	   overlapDiscardPile.setOnMousePressed(event -> overlapDiscardPile.setEffect(drop));

	   overlapDiscardPile.setOnMouseReleased(event -> overlapDiscardPile.setEffect(null));

	   overlapDiscardPile.setOnMouseClicked(event-> {
	   	if(fightController.getDiscardPile().getCards().size() > 0) {
			cardCollection.getChildren().clear();
			ArrayList<Card> cards = fightController.getDiscardPile().getCards();
			CardImage card;
			int horizontal = 6;
			for (int i = 0; i < fightController.getDiscardPile().getCards().size(); i++) {
				card = new CardImage(cards.get(i));
				if(cards.get(i).getUpgrade()) {
					card.cardEnergy.setTranslateX(15);
				}
				cardCollection.add(card, i % horizontal, i / horizontal);
			}
			cardCollection.setTranslateX(150);
			cardCollection.setTranslateY(70);
			getChildren().addAll(cardCollection);


			MainMenu.MenuButton returnFight = new MainMenu.MenuButton("Return Fight");
			returnFight.setTranslateY(450);
			returnFight.setTranslateX(5);
			getChildren().addAll(returnFight);
			returnFight.setOnMouseClicked(event2 -> {
				getChildren().removeAll(returnFight, cardCollection);

			});
		}
	   });


		btnEndTurn = new MenuButton("End Turn");

		btnEndTurn.setOnMouseClicked(event -> {
		   //CONTROLLER END TURN
			   CardContainer.getChildren().removeAll(CardContainer.getChildren());
			   this.fightController.endTurn();
			   charHP.setValue((character.getHp() / (character.getMaxHp() * 1.0)), character.getHp());
			   if(this.fightController.isGameOver() || finalRoom)
			   {

			   	   if(fightController.getCharacter().getHp() > 0 ) {
			   	   		System.out.println("FIGHT IS OVER UPDATING HEALTHS");
			   	   		hudPane.updateHP();
					   charHP.setValue((character.getHp() / (character.getMaxHp() * 1.0)), character.getHp());
					   LootPane lootPane = new LootPane(fightController, hudPane);
					   lootPane.setTranslateX(400);
					   lootPane.setTranslateY(120);
					   pane.getChildren().addAll(lootPane);
				   }

					   MenuButton returnButton = new MenuButton("Return");
					   pane.getChildren().addAll(returnButton);

					   returnButton.setTranslateX(880);
					   returnButton.setTranslateY(480);

				   returnButton.setOnMouseClicked(event2 -> {
					   getChildren().remove(pane);
					   System.out.println(finalRoom + "---------------FINAL ROOM-------------------");
					   if(fightController.getCharacter().getHp() <= 0){
						   System.out.println(finalRoom + "---------------IN IF STMT-------------------");
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
					   else if(finalRoom){
						   MainMenu.GameMenu menuScene = new MainMenu().new GameMenu();
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
					manageBuffs(enemies[i],i);
				}

				for(int i = 0 ; i < enemyNum ; i++)
				{
					manageEnemyPurpose(enemies[i],i);
				}

			    for (int j = 0 ; j < enemyNum ; j++ )
				{
					enemyHPs[j].setValue((enemies[j].getHp() / (enemies[j].getMaxHp() * 1.0)), enemies[j].getHp());
				}

			   	hudPane.updateHP();
				drawPileCardNum.setText(Integer.toString(this.fightController.getDrawPile().getCards().size()));
				discardPileNum.setText(Integer.toString(this.fightController.getDiscardPile().getCards().size() ) );
			    blockNum.setText( Integer.toString(this.fightController.getBlock() ) );
				energyNum.setText(Integer.toString(this.fightController.getEnergy() ) );

				for (int j = 0 ; j < enemyNum ; j++)
				{
					if(enemies[j].getBlock() != 0 )
					{
						blockNumEnemies[j].setText( Integer.toString(enemies[j].getBlock() ) );
						overlapBlockEnemies[j].setVisible(true);
					}
				}

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


	   monsterImages = new ImageView[enemyNum];
	   monsterImage = null;
	   for(int i = 0 ; i < enemyNum ; i++) {

		   try {
			   is = Files.newInputStream(Paths.get("resources/images/enemy-images/"+enemies[i].getName()+".png"));
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

	   //Initialize block for char.
	   blockNum = new Text();
	   blockNum.setText(Integer.toString(0) );
	   blockNum.setFill(Color.WHITE);
	   blockNum.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 12));

	   overlapBlock = new StackPane();
	   overlapBlock.getChildren().addAll(blockIconChar,blockNum);
	   overlapBlock.setVisible(false);


	   //Initialize block for enemy
	   overlapBlockEnemies = new StackPane[enemyNum];
	   blockNumEnemies = new Text[enemyNum];

	   for (int i = 0 ; i < enemyNum ; i++)
	   {
		   blockNumEn = new Text();
		   blockNumEn.setText(Integer.toString(0) );
		   blockNumEn.setFill(Color.WHITE);
		   blockNumEn.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 12));
		   blockNumEnemies[i] = blockNumEn;
	   }

	   for (int i = 0 ; i < enemyNum ; i++)
	   {
		   blockIconEnemy = null;
		   try {
			   is = Files.newInputStream(Paths.get("resources/images/blockIcon.png"));
			   img = new Image(is);
			   is.close(); //this is to give access other programs to that image as well.
			   blockIconEnemy = new ImageView(img);
			   blockIconEnemy.setFitWidth(25);
			   blockIconEnemy.setFitHeight(25);
		   } catch (IOException e) {
			   e.printStackTrace();
		   } //get the image

		   overlapBlockEnemy = new StackPane();
		   overlapBlockEnemy.getChildren().addAll(blockIconEnemy,blockNumEnemies[i]);
		   overlapBlockEnemy .setVisible(false);
		   overlapBlockEnemies[i] = overlapBlockEnemy;
	   }



  		charStats.getChildren().addAll(overlapBlock,charHP);

  		for(int i = 0 ; i < enemyNum ; i++) {
  			enemiesStats[i].getChildren().addAll(overlapBlockEnemies[i],enemyHPs[i]);
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

	  	  btnUsePotion = new MenuButton("Use Potion");
	  	  btnUsePotion.setTranslateX(500);
	  	  btnUsePotion.setTranslateY(50);
	  	  btnUsePotion.setOnMouseClicked(event -> {
			 Potion p = hudPane.getChosenPotion();
			 boolean isTargetable = p.isHasTarget();

			 if(isTargetable) {
				 fightController.applyPotion(p, enemyToHit);
				 System.out.println("Potion " +p.getName() + " have used.");
			 }

			 else {
				 fightController.applyPotion(p);
				 System.out.println("Potion "+ p.getName() + " have used.");
			 }

			 hudPane.updatePotions();

			  for (int j = 0 ; j < enemyNum ; j++)
			  {
				  if(enemies[j].getHp() <= 0)
				  {
					  System.out.println("ONE ENEMY IS DEAD.");
					  monsterImages[j].setVisible(false);
					  enemiesBuffs[j].setVisible(false);
					  enemiesStats[j].setVisible(false);
					  enemiesPurposes[j].setVisible(false);
				  }
			  }

			  for (int j = 0 ; j < enemyNum ; j++ )
			  {
				  enemyHPs[j].setValue((enemies[j].getHp() / (enemies[j].getMaxHp() * 1.0)), enemies[j].getHp());
			  }

			  manageBuffs(character);

			  for (int j = 0 ; j < enemyNum ; j++ )
			  {
				  if(enemies[j].getHp() > 0)
					  manageBuffs(enemies[j],j);
			  }
			  charHP.setValue((character.getHp() / (character.getMaxHp() * 1.0)), character.getHp());
			  hudPane.updateHP();
			  energyNum.setText(Integer.toString(this.fightController.getEnergy() ) );
			  discardPileNum.setText(Integer.toString(this.fightController.getDiscardPile().getCards().size() ) );
			  drawPileCardNum.setText(Integer.toString(this.fightController.getDrawPile().getCards().size()));
			  blockNum.setText( Integer.toString(this.fightController.getBlock() ) );

			  for (int j = 0 ; j < enemyNum ; j++)
			  {
				  if(enemies[j].getBlock() != 0 )
				  {
					  blockNumEnemies[j].setText( Integer.toString(enemies[j].getBlock() ) );
					  overlapBlockEnemies[j].setVisible(true);
				  }
			  }

			  for (int j = 0 ; j < enemyNum ; j++)
			  {
				  if(enemies[j].getBlock() <= 0 )
				  {
					  blockNumEnemies[j].setText( Integer.toString(enemies[j].getBlock() ) );
					  overlapBlockEnemies[j].setVisible(false);
				  }
			  }

			  if(this.fightController.getBlock() != 0 )
			  {
				  //System.out.println("Setting visibility true");
				  overlapBlock.setVisible(true);
			  }
			  if(character.getPotions().size() == 0){
			  	pane.getChildren().remove(btnUsePotion);
			  }

		  });




	  	  manageBuffs(character);

	  	  for(int i = 0 ; i < enemyNum ; i++)
		  {
			  manageBuffs(enemies[i],i);
		  }

	  	 for (int j = 0 ; j < enemyNum ; j++ )
	  	 {
		  	 manageEnemyPurpose(enemies[j],j);
	  	 }

	  	  LeftFightLevel.getChildren().addAll(charImage,charStats,charBuffs);

	  	  HBox RightFightLevelContainer = new HBox(5);

	  	  for (int i = 0 ; i < enemyNum ; i++)
		  {
			  RightFightLevel = new VBox(1);
			  RightFightLevel.getChildren().addAll(monsterImages[i],enemiesStats[i],
					  								enemiesBuffs[i],enemiesPurposes[i]);
			  RightFightLevelContainer.getChildren().addAll(RightFightLevel);
		  }

	  	  FightLevel.getChildren().addAll(LeftFightLevel,RightFightLevelContainer);


 		  LeftLowerLevel.getChildren().addAll(overlapDrawPile,overlapEnergy);
 		  RightLowerLevel.getChildren().addAll(btnEndTurn,overlapDiscardPile);
 		  LowerLevelContainer.getChildren().addAll(LeftLowerLevel,CardContainer);



 		  //DELETE AFTER TRYOUT
	   	  /*String[] a = new String[1];
	   	  a[0] = "Leave";
	      EventImage ei = new EventImage("Mind Bloom","Hail the King!",a);
          */
	   	  if(character.getPotions().size() > 0) {
			  pane.getChildren().addAll(hudPane,btnUsePotion, FightLevel, LowerLevelContainer, RightLowerLevel);
		  }
	   	  else
			  pane.getChildren().addAll(hudPane, FightLevel, LowerLevelContainer, RightLowerLevel);
 		  getChildren().addAll(pane);
 		  //pane.setRotate(50);

   }

   public void manageBuffs( Character ch )
	{
		charBuffs.getChildren().clear();

		if (ch.getHp() > 0) {
			ArrayList<Buff> buffs = ch.getBuffs().getBuffs();

			if(buffs.size() > 0) {
				for (int i = 0; i < buffs.size(); i++) {
					//System.out.println("BUFF NAME IN MANAGE BUFF(character) METHOD: "+buffs.get(i).getName());
					String buffName = buffs.get(i).getName();
					InputStream is;
					Image img;
					ImageView buffIcon = null;
					try {
						is = Files.newInputStream(Paths.get("resources/images/buff-icons/" + buffName.toLowerCase() + ".png"));
						img = new Image(is);
						is.close(); //this is to give access other programs to that image as well.
						buffIcon = new ImageView(img);
						buffIcon.setFitWidth(25);
						buffIcon.setFitHeight(25);
					} catch (IOException e) {
						e.printStackTrace();
					} //get the image

					Text buffDesc = new Text(buffs.get(i).getDescription());
					buffDesc.setFill(Color.WHITE);
					buffDesc.setFont(Font.font("Verdana", 12));
					buffIcon.setOnMouseEntered(event -> {
						//System.out.println("BUFF IS PRINTED********");
						Robot robot = new Robot();
						int y = (int) (robot.getMouseY() - 25);
						int x = (int) (robot.getMouseX() + 25);
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

					charBuffs.setTranslateX(115);
					charBuffs.setTranslateY(-260);

					charBuffs.getChildren().add(buffIcon);

				}
			}
		}
	}


	public void manageEnemyPurpose(Enemy ch, int enemyIndex)
	{
		System.out.println("--IN MANAGE ENEMY PURPOSES for Enemy HP: "+ch.getHp());
		enemiesPurposes[enemyIndex].getChildren().clear();

		if (ch.getHp() > 0 ) {

			ArrayList<Effect> purposes = fightController.getEnemyEffects(enemyIndex);
			System.out.println("Size of purposes is :"+ purposes.size());

			if(purposes.size() > 0) {
				for (int i = 0; i < purposes.size(); i++) {
					String purposeType = "";
					Text purposeText = null;
					Text purposeDesc = null;

					if (purposes.get(i) instanceof Damage) {
						Damage d = (Damage) purposes.get(i);
						purposeType = "Attack";
						purposeText = new Text(Integer.toString(d.getDamage()));
						purposeDesc = new Text("Enemy wants to Attack this turn.");
					} else if (purposes.get(i) instanceof Block) {
						Block b = (Block) purposes.get(i);
						purposeType = "Block";
						purposeText = new Text(Integer.toString(b.getBlock()));
						purposeDesc = new Text("Enemy wants to use block on itself.");

					} else if (purposes.get(i) instanceof ApplyBuff) {
						System.out.println("IN APPYL BUFF - PURPOSE : "+purposes.get(i));
						ApplyBuff b = (ApplyBuff) purposes.get(i);

						if (b.getBuff().isDebuff()) {
							purposeType = "Strategic";
							purposeDesc = new Text("Enemy wants to use debuff on you.");
						}
						else {
							purposeType = "Buff";
							purposeDesc = new Text("Enemy wants to use buff on itself.");
						}

					}

					InputStream is;
					Image img;
					ImageView purposeIcon = null;
					try {
						is = Files.newInputStream(Paths.get("resources/images/purpose-icons/purpose" + purposeType + ".png"));
						img = new Image(is);
						is.close(); //this is to give access other programs to that image as well.
						purposeIcon = new ImageView(img);
						purposeIcon.setFitWidth(35);
						purposeIcon.setFitHeight(35);
					} catch (IOException e) {
						e.printStackTrace();
					}

					purposeDesc.setFill(Color.WHITE);
					purposeDesc.setFont(Font.font("Verdana", 12));


					Text finalPurposeDesc = purposeDesc;
					purposeIcon.setOnMouseEntered(event -> {
						//System.out.println("BUFF IS PRINTED********");
						Robot robot = new Robot();
						int y = (int) (robot.getMouseY() - 25);
						int x = (int) (robot.getMouseX() -5);
						finalPurposeDesc.setX(x);
						finalPurposeDesc.setY(y);
						finalPurposeDesc.setVisible(true);
						getChildren().add(finalPurposeDesc);

					});

					purposeIcon.setOnMouseExited(event -> {
						finalPurposeDesc.setVisible(false);
						getChildren().remove(finalPurposeDesc);
					});

					for (int k = 0; k < enemyNum; k++) {
						enemiesPurposes[k].setTranslateX(115);
						enemiesPurposes[k].setTranslateY(-260);
					}

					enemiesPurposes[enemyIndex].getChildren().addAll(purposeIcon);
					System.out.println("PURPOSE ICON ADDED");
				}
			}

		}

	}

	public void manageBuffs( Enemy ch, int enemyIndex )
	{
		enemiesBuffs[enemyIndex].getChildren().clear();
		if (ch.getHp() > 0) {

			ArrayList<Buff> buffs = ch.getBuffs().getBuffs();

			if (buffs.size() > 0) {
				for (int i = 0; i < buffs.size(); i++) {
					System.out.println("BUFF NAME IN MANAGE BUFF(enemy) METHOD: " + buffs.get(i).getName());
					String buffName = buffs.get(i).getName();
					InputStream is;
					Image img;
					ImageView buffIcon = null;
					try {
						is = Files.newInputStream(Paths.get("resources/images/buff-icons/" +  buffName.toLowerCase()+ ".png"));
						img = new Image(is);
						is.close(); //this is to give access other programs to that image as well.
						buffIcon = new ImageView(img);
						buffIcon.setFitWidth(25);
						buffIcon.setFitHeight(25);
					} catch (IOException e) {
						e.printStackTrace();
					}

					Text buffDesc = new Text(buffs.get(i).getDescription());
					buffDesc.setFill(Color.WHITE);
					buffDesc.setFont(Font.font("Verdana", 12));
					buffIcon.setOnMouseEntered(event -> {
						//System.out.println("BUFF IS PRINTED********");
						Robot robot = new Robot();
						int y = (int) (robot.getMouseY() - 25);
						int x = (int) (robot.getMouseX() + 15);
						buffDesc.setX(x);
						buffDesc.setY(y);
						buffDesc.setVisible(true);
						getChildren().add(buffDesc);

					});

					buffIcon.setOnMouseExited(event -> {
						buffDesc.setVisible(false);
						getChildren().remove(buffDesc);
					});

					for (int k = 0; k < enemyNum; k++) {
						enemiesBuffs[k].setTranslateX(45);
						enemiesBuffs[k].setTranslateY(-27);
					}

					enemiesBuffs[enemyIndex].getChildren().add(buffIcon);
					System.out.println("BUFF ICON ADDED");
				}
			}
		}

	}

 public void dealtCards(){
   	 System.out.println("IN DEALT CARDS METHOD");
	 CardContainer.getChildren().clear();
	 handPile = fightController.getHandPile();
   	 ArrayList<Card> cards = handPile.getCards();
   	 System.out.println("SIZE OF HANDPILE IS:  "+cards.size());
   	 for(int i = 0 ; i < cards.size() ; i++)
	 {
		 CardImage cardImage;
		 Card card = cards.get(i);
		 System.out.println("FIGHT CARD "+card.getName() + " upgrade: "+card.getUpgrade());
		 cardImage = new CardImage(cards.get(i));
		 if(cards.get(i).getUpgrade()) {
			 cardImage.cardEnergy.setTranslateX(15);
		 }
		 cardImage.setOnMouseClicked(event -> {
			 //CONTROLLER CARD CLICKED
			 boolean isPlayable = fightController.playCard( card , enemyToHit);
			 if(isPlayable) {
				 //dealtCards(); //TODO bu niye vardı bilmiyorum. bir şey bozulursa aç.
				 if(this.fightController.isGameOver())
				 {
					 System.out.println("FIGHT IS OVER UPDATING HEALTHS");
					 hudPane.updateHP();
					 charHP.setValue((character.getHp() / (character.getMaxHp() * 1.0)), character.getHp());
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
						 System.out.println(finalRoom + "---------------FINAL ROOM-------------------");
						 if(fightController.getCharacter().getHp() <= 0 || finalRoom){
							 System.out.println(finalRoom + "---------------IN IF STMT-------------------");
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
					 	System.out.println("ONE ENEMY IS DEAD.");
						 monsterImages[j].setVisible(false);
						 enemiesBuffs[j].setVisible(false);
						 enemiesStats[j].setVisible(false);
						 enemiesPurposes[j].setVisible(false);
					 }
				 }

				 for (int j = 0 ; j < enemyNum ; j++ )
				 {
					 enemyHPs[j].setValue((enemies[j].getHp() / (enemies[j].getMaxHp() * 1.0)), enemies[j].getHp());
				 }

				 CardContainer.getChildren().remove(cardImage);

				 manageBuffs(character);

				 for (int j = 0 ; j < enemyNum ; j++ )
				 {
				 	if(enemies[j].getHp() > 0)
				 		manageBuffs(enemies[j],j);
				 }

				 for (int j = 0 ; j < enemyNum ; j++ )
				 {
				 	if(enemies[j].getHp() > 0)
						 manageEnemyPurpose(enemies[j],j);
				 }

				 energyNum.setText(Integer.toString(this.fightController.getEnergy() ) );
				 discardPileNum.setText(Integer.toString(this.fightController.getDiscardPile().getCards().size() ) );
				 drawPileCardNum.setText(Integer.toString(this.fightController.getDrawPile().getCards().size()));
				 blockNum.setText( Integer.toString(this.fightController.getBlock() ) );

				 for (int j = 0 ; j < enemyNum ; j++)
				 {
				 	if(enemies[j].getBlock() != 0 )
					{
						blockNumEnemies[j].setText( Integer.toString(enemies[j].getBlock() ) );
						overlapBlockEnemies[j].setVisible(true);
					}
				 }

				 for (int j = 0 ; j < enemyNum ; j++)
				 {
					 if(enemies[j].getBlock() <= 0 )
					 {
						 blockNumEnemies[j].setText( Integer.toString(enemies[j].getBlock() ) );
						 overlapBlockEnemies[j].setVisible(false);
					 }
				 }

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
