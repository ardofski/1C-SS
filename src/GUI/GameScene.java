package GUI;
import java.awt.MouseInfo;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.util.Duration;


class GameScene extends Parent {
	
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
   double x = screenBounds.getWidth(); //gets the screen width
   double y = screenBounds.getHeight(); //gets the screen height
   AudioClip menuSound = new AudioClip(new File("resources/sounds/menuMusic.wav").toURI().toString());
   private Pane root ;
   public GameScene() {
   	 
  	  Rectangle bg = new Rectangle(x,y);
       bg.setOpacity(0.1);
 
  	   //in form of vertical box and horizontal box.
        HBox LeftUpperLevel = new HBox(70);
        HBox RightUpperLevel = new HBox(30);
        HBox UpperLevelContainer = new HBox(600);
        HBox FightLevel = new HBox(280);
        
        UpperLevelContainer.setPrefWidth(x);
        UpperLevelContainer.setStyle("-fx-background-color: #808080;"+"-fx-opacity: 0.85;");
        //UpperLevel.setSpacing(400);
   
        FightLevel.setTranslateX(180);
        FightLevel.setTranslateY(345);
        //adjusting position of mainMenu on screen.
//        LeftUpperLevel.setTranslateX(60);
//        LeftUpperLevel.setTranslateY(15);
//
//        RightUpperLevel.setTranslateX(1000);
//        RightUpperLevel.setTranslateY(15);
        //adjusting position of settings menu.

        
        
        final int offset = 400;
//        settingsMenu.setTranslateX(offset);
//        soundMenu.setTranslateX(offset);
//        statisticsMenu.setTranslateX(offset);
//        statisticsInfo.setTranslateX(offset);
//        characterSelection.setTranslateX(offset);
        
        
        //UpperLevel of Game Scene Implementations
        InputStream is;
        Image img;
        
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
			 com.sun.glass.ui.Robot robot =
                com.sun.glass.ui.Application.GetApplication().createRobot();
          int y = robot.getMouseY() +30;     
          int x = robot.getMouseX() -15;
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
  			 com.sun.glass.ui.Robot robot =
               com.sun.glass.ui.Application.GetApplication().createRobot();
          int y = robot.getMouseY() +30;     
          int x = robot.getMouseX() -15;
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
  			com.sun.glass.ui.Robot robot =
               com.sun.glass.ui.Application.GetApplication().createRobot();
         int y = robot.getMouseY() +30;     
         int x = robot.getMouseX() -15;
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
  			 com.sun.glass.ui.Robot robot =
               com.sun.glass.ui.Application.GetApplication().createRobot();
          int y = robot.getMouseY() +30;     
          int x = robot.getMouseX() -15;
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
  			com.sun.glass.ui.Robot robot =
               com.sun.glass.ui.Application.GetApplication().createRobot();
         int y = robot.getMouseY() +30;     
         int x = robot.getMouseX() -55;
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
  			com.sun.glass.ui.Robot robot =
               com.sun.glass.ui.Application.GetApplication().createRobot();
          int y = robot.getMouseY() +30;     
          int x = robot.getMouseX() -155;
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
         characterImage.setFitWidth(275); 
         characterImage.setFitHeight(200);	        
  		} catch (IOException e) {
  			e.printStackTrace();
  		} //get the image  
        
        
  		ImageView monsterImage = null;
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
        
        
  		 Text characterName = new Text("   Ironclad");
 		  characterName.setFill(Color.WHITE);
 		  characterName.setFont(Font.font ("COMIC SANS MS", 18));
 		  
 		FightLevel.getChildren().addAll(characterImage,monsterImage);  
      LeftUpperLevel.getChildren().addAll(characterName,hp,gold,potion);
      RightUpperLevel.getChildren().addAll(map,deck,settings);
      UpperLevelContainer.getChildren().addAll(LeftUpperLevel,RightUpperLevel);
      
      getChildren().addAll(UpperLevelContainer,FightLevel);
   }

}
