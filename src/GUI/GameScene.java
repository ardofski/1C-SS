package GUI;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import Controller.Fight.FightController;
import Model.Card;
import Model.Character;
import Model.Enemy;
import Model.Pile;
import Model.Room.EnemyRoom;
import Model.Room.RoomFactory;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
	
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double x = screenBounds.getWidth(); //gets the screen width
    double y = screenBounds.getHeight(); //gets the screen height
    AudioClip menuSound = new AudioClip(new File("resources/sounds/menuMusic.wav").toURI().toString());
    private Pane root ;
	HealthBar charHP;
	HealthBar enemyHP;
	Character character;
	RoomFactory roomFactory;
	EnemyRoom room;
	FightController fightController;
	Pile handPile;
	Pile characterPile;
	Enemy enemy;
	Text energyNum;
	HBox CardContainer;
	ImageView monsterImage;
   public GameScene(FightController fightController) {
   	    this.fightController = fightController;
   	    character = fightController.getCharacter();

	    //fightController.setRoom(room);

	   handPile = fightController.getHandPile();
	   ArrayList<Card> cards = handPile.getCards();
   	 
  	  Rectangle bg = new Rectangle(x,y);
       bg.setOpacity(0.1);
 
  	   //in form of vertical box and horizontal box.
        HBox LeftUpperLevel = new HBox(70);
        HBox RightUpperLevel = new HBox(30);
        HBox UpperLevelContainer = new HBox(600);

        VBox LeftFightLevel = new VBox(2);
	    VBox RightFightLevel = new VBox(2);
        HBox FightLevel = new HBox(280);

        HBox LeftLowerLevel = new HBox(30);
        VBox RightLowerLevel = new VBox(50);

		HBox LowerLevelContainer= new HBox(60);
		CardContainer = new HBox(-35);


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
        FightLevel.setTranslateY(315);

        //LeftLowerLevel.setTranslateX(80);
        //LeftLowerLevel.setTranslateY(550);

        //RightLowerLevel.setTranslateX(600);
	    //RightLowerLevel.setTranslateY(550);

	    InputStream is;
	    Image img;


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

		Text drawPileCardNum = new Text(Integer.toString(this.fightController.getDrawPile().getCards().size()));
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
	   Text discardPileNum = new Text("0");

	   enemy = fightController.getEnemyRoom().getEnemies().get(0);
	   System.out.println("Enemy ROOm enemy :: : : : : : ");

	   for(int i = 0 ; i < cards.size() ; i++)
	   {
		   CardImage cardImage;
	   	   Card card = cards.get(i);
		   cardImage = new CardImage(cards.get(i).getName(),cards.get(i).getType()
				   ,Integer.toString(cards.get(i).getEnergy()),cards.get(i).getDescription());

		   cardImage.setOnMouseClicked(event -> {
		   	//CARD CLICKED
			   //System.out.println(fightController.);
			   boolean isPlayable = this.fightController.playCard( card , enemy);
			   if(isPlayable) {
				   if(this.fightController.isGameOver())
				   {
					   Text endGame = new Text("GAME FINISHED");
					   endGame.setFill(Color.WHITE);
					   endGame.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 50));
					   endGame.setX(420);
					   endGame.setY(350);
					   getChildren().addAll(endGame);
				   }
				   System.out.println("CARD ---------->>>>>>>>>>\n\n\n   "+card);
				   System.out.println("ENEMY IS ---->>"+enemy);
				   System.out.println("ENEMY IS ---->>"+fightController.getEnemyRoom().getEnemies().get(0));
				   enemyHP.setValue((enemy.getHp() / (enemy.getMaxHp() * 1.0)), enemy.getHp());
				   CardContainer.getChildren().remove(cardImage);
				   energyNum.setText(Integer.toString(this.fightController.getEnergy() ) );
				   discardPileNum.setText(Integer.toString(this.fightController.getDiscardPile().getCards().size() ) );
				   drawPileCardNum.setText(Integer.toString(this.fightController.getDrawPile().getCards().size()));
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

		MenuButton btnEndTurn = new MenuButton("End Turn");

		btnEndTurn.setOnMouseClicked(event -> {
		   //CONTROLLER END TURN
			  //	monsterImage.setTranslateX(-400);
			   CardContainer.getChildren().removeAll(CardContainer.getChildren());
			   this.fightController.endTurn();
			   charHP.setValue((character.getHp() / (character.getMaxHp() * 1.0)), character.getHp());
			   if(this.fightController.isGameOver())
			   {
				   Text endGame = new Text("GAME FINISHED");
				   endGame.setFill(Color.WHITE);
				   endGame.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 50));
				   endGame.setX(420);
				   endGame.setY(350);
				   getChildren().addAll(endGame);

			   }
			   dealtCards();

	   	});



        //UPPER-LEVEL IMPLEMENTATION

        //HP
        ImageView hp = null;
        
		try {
			   is = Files.newInputStream(Paths.get("resources/images/hpIcon.png"));
			   img = new Image(is);
	         is.close(); //this is to give access other programs to that image as well.
	         hp = new ImageView(img); 
	         hp.setFitWidth(40); 
	         hp.setFitHeight(40);	        
		} catch (IOException e) {
			e.printStackTrace();
		} //get the image  
		
		Text hpDesc = new Text("Health Point.\nYou die if HP=0");
		hpDesc.setFill(Color.WHITE);
		hpDesc.setFont(Font.font ("Verdana", 15));
		
		 hp.setOnMouseEntered(event -> { 
		 	Robot robot = new Robot();
            int y = (int) (robot.getMouseY() +30);
            int x = (int) (robot.getMouseX() -15);

			 hpDesc.setX(x);
  			 hpDesc.setY(y);
			 hpDesc.setVisible(true);
          getChildren().add(hpDesc);
          
      });
		 
		 hp.setOnMouseExited(event -> {
			 hpDesc.setVisible(false);
			 getChildren().remove(hpDesc);
      });
        
		 
		 
        //Gold
		 ImageView gold= null;
        try {
			   is = Files.newInputStream(Paths.get("resources/images/goldIcon.png"));
			   img = new Image(is);
	         is.close(); //this is to give access other programs to that image as well.
	         gold = new ImageView(img); 
	         gold.setFitWidth(40); 
	         gold.setFitHeight(40);
		} catch (IOException e) {
			e.printStackTrace();
		} //get the image 
        Text goldDesc = new Text("MONEY POUCH\nShows how money gold you have.");
  		  goldDesc.setFill(Color.WHITE);
  		  goldDesc.setFont(Font.font ("Verdana", 15));
  		
  		 gold.setOnMouseEntered(event -> {


  			 Robot robot = new Robot();
          int y = (int) (robot.getMouseY() +30);
          int x = (int) (robot.getMouseX() -15);

  			 goldDesc.setX(x);
  			 goldDesc.setY(y);
  			 goldDesc.setVisible(true);
            getChildren().add(goldDesc);
            
        });
  		 
  		 gold.setOnMouseExited(event -> {
  			 goldDesc.setVisible(false);
  			 getChildren().remove(goldDesc);
        });
        
  		 
        
        //Potion
        ImageView potion = null;
        try {
			   is = Files.newInputStream(Paths.get("resources/images/potionIcon.png"));
			   img = new Image(is);
	         is.close(); //this is to give access other programs to that image as well.
	         potion = new ImageView(img); 
	         potion.setFitWidth(50); 
	         potion.setFitHeight(50);
		} catch (IOException e) {
			e.printStackTrace();
		} //get the image 
  
        
        Text potionDesc = new Text("POTION SLOT\nDuring fights,gain bonuses\nBlock enemies.");
  		  potionDesc.setFill(Color.WHITE);
  		  potionDesc.setFont(Font.font ("Verdana", 15));
  		
  		 potion.setOnMouseEntered(event -> {
  		 	Robot robot = new Robot();

         int y = (int) (robot.getMouseY() +30);
         int x = (int) (robot.getMouseX() -15);
  			 potionDesc.setX(x);
 			 potionDesc.setY(y);
  			 potionDesc.setVisible(true);
            getChildren().add(potionDesc);
            
        });
  		 
  		 potion.setOnMouseExited(event -> {
  			 potionDesc.setVisible(false);
  			 getChildren().remove(potionDesc);
        });

  		 
  		 
       //Map
        ImageView map = null;
        try {
			   is = Files.newInputStream(Paths.get("resources/images/mapIcon.png"));
			   img = new Image(is);
	         is.close(); //this is to give access other programs to that image as well.
	         map = new ImageView(img); 
	         map.setFitWidth(40); 
	         map.setFitHeight(40);
		} catch (IOException e) {
			e.printStackTrace();
		} //get the image 
        Text mapDesc = new Text("MAP SLOT\nCheck the current\ndungeon map");
  		  mapDesc.setFill(Color.WHITE);
  		  mapDesc.setFont(Font.font ("Verdana", 15));
  		
  		 map.setOnMouseEntered(event -> {

  		 	Robot robot = new Robot();
          int y = (int) (robot.getMouseY() +30);
          int x = (int) (robot.getMouseX() -15);
  			 mapDesc.setX(x);
 			 mapDesc.setY(y);
  			 mapDesc.setVisible(true);
            getChildren().add(mapDesc);
            
        });
  		 
  		 map.setOnMouseExited(event -> {
  			 mapDesc.setVisible(false);
  			 getChildren().remove(mapDesc);
        });
        
        
        //Deck
        ImageView deck = null;
        try {
			   is = Files.newInputStream(Paths.get("resources/images/deckIcon.png"));
			   img = new Image(is);
	         is.close(); //this is to give access other programs to that image as well.
	         deck = new ImageView(img); 
	         deck.setFitWidth(40); 
	         deck.setFitHeight(40);
		} catch (IOException e) {
			e.printStackTrace();
		} //get the image 
        Text deckDesc = new Text("DECK SLOT\nView all the cards in your deck.");
  		  deckDesc.setFill(Color.WHITE);
  		  deckDesc.setFont(Font.font ("Verdana", 15));
  		
  		 deck.setOnMouseEntered(event -> {
			 Robot robot = new Robot();

         int y = (int) (robot.getMouseY() +30);
         int x = (int) (robot.getMouseX() -55);
  			 deckDesc.setX(x);
 			 deckDesc.setY(y);
  			 deckDesc.setVisible(true);
            getChildren().add(deckDesc);
            
        });
  		 
  		 deck.setOnMouseExited(event -> {
  			 deckDesc.setVisible(false);
  			 getChildren().remove(deckDesc);
        });
        
  		 
        //Settings
        ImageView settings = null;
        try {
			   is = Files.newInputStream(Paths.get("resources/images/optionsIcon.png"));
			   img = new Image(is);
	         is.close(); //this is to give access other programs to that image as well.
	         settings = new ImageView(img); 
	         settings.setFitWidth(40); 
	         settings.setFitHeight(40);
		} catch (IOException e) {
			e.printStackTrace();
		} //get the image 
        Text settingsDesc = new Text("OPTION SLOT\nOpens the game menu");
  		  settingsDesc.setFill(Color.WHITE);
  		  settingsDesc.setFont(Font.font ("Verdana", 15));
  		
  		 settings.setOnMouseEntered(event -> {
			 Robot robot = new Robot();

          int y = (int) (robot.getMouseY() +30);
          int x = (int) (robot.getMouseX() -155);

  			 settingsDesc.setX(x);
 			 settingsDesc.setY(y);
  			 settingsDesc.setVisible(true);
            getChildren().add(settingsDesc);
            
        });
  		 
  		 settings.setOnMouseExited(event -> {
  			 settingsDesc.setVisible(false);
  			 getChildren().remove(settingsDesc);
        });
  		 
  		 
  		 
  		 //CHARACTER AND ENEMIES
  		 ImageView characterImage = null;
  		try {
		   is = Files.newInputStream(Paths.get("resources/images/characterImage.png"));
		   img = new Image(is);
         is.close(); //this is to give access other programs to that image as well.
         characterImage = new ImageView(img); 
         characterImage.setFitWidth(375);
         characterImage.setFitHeight(260);
  		} catch (IOException e) {
  			e.printStackTrace();
  		} //get the image  
        
        
	   monsterImage = null;
  		try {
		   is = Files.newInputStream(Paths.get("resources/images/monsterImage.png"));
		   img = new Image(is);
         is.close(); //this is to give access other programs to that image as well.
         monsterImage = new ImageView(img); 
         monsterImage.setFitWidth(275); 
         monsterImage.setFitHeight(200);	        
  		} catch (IOException e) {
  			e.printStackTrace();
  		} //get the image  

	    charHP = new HealthBar(character.getHp());
  		enemyHP = new HealthBar(enemy.getHp());

	    LeftFightLevel.getChildren().addAll(characterImage,charHP);
        RightFightLevel.getChildren().addAll(monsterImage,enemyHP);

  		  Text characterName = new Text("   Ironclad");
 		  characterName.setFill(Color.WHITE);
 		  characterName.setFont(Font.font ("COMIC SANS MS", 18));
 		  FightLevel.getChildren().addAll(LeftFightLevel,RightFightLevel);
 		  LeftUpperLevel.getChildren().addAll(characterName,hp,gold,potion);
 		  RightUpperLevel.getChildren().addAll(map,deck,settings);
 		  UpperLevelContainer.getChildren().addAll(LeftUpperLevel,RightUpperLevel);
 		  LeftLowerLevel.getChildren().addAll(overlapDrawPile,overlapEnergy);
 		  RightLowerLevel.getChildren().addAll(btnEndTurn,overlapDiscardPile);
 		  LowerLevelContainer.getChildren().addAll(LeftLowerLevel,CardContainer);

 		  getChildren().addAll(UpperLevelContainer,FightLevel,LowerLevelContainer,RightLowerLevel);

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
			 boolean isPlayable = fightController.playCard( card , enemy);
			 if(isPlayable) {
				 enemyHP.setValue((enemy.getHp() / (enemy.getMaxHp() * 1.0)), enemy.getHp());
				 CardContainer.getChildren().remove(cardImage);
				 energyNum.setText(Integer.toString(fightController.getEnergy() ) );
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
