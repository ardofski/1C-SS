package GUI;

import Controller.Fight.FightController;
import Model.Character;
import Model.Potion;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.robot.Robot;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class HUDPane extends StackPane {
    //UPPER-LEVEL IMPLEMENTATION

    // Model
    Character character;

    StackPane hudPane;

    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double width = screenBounds.getWidth(); //gets the screen width
    double height = screenBounds.getHeight();

    //Image Views
    ImageView hp = null;
    ImageView gold= null;
    ImageView deck = null;
    ImageView map = null;
    ImageView settings = null;

    //Texts
    Text hpText, goldText, totalCardNum, floorText;

    //Containers
    HBox leftUpperLevel, rightUpperLevel, upperLevelContainer, potions, relics;



    Image img;
    InputStream is;
    public HUDPane(Character character){
        this.character = character;
         hudPane = new StackPane();
        relics = new HBox(20);
        try {
            is = Files.newInputStream(Paths.get("resources/images/hpIcon.png"));
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
            hp = new ImageView(img);
            hp.setFitWidth(40);
            hp.setFitHeight(40);
        }catch(IOException e){
            e.printStackTrace();
        }

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

        try {
            is = Files.newInputStream(Paths.get("resources/images/goldIcon.png"));
            img = new Image(is);
            is.close();
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
        potions = updatePotions();





        //Map

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
        totalCardNum = new Text();
        totalCardNum.setText(Integer.toString(character.getDeck().getCards().size() ) );
        totalCardNum.setFill(Color.WHITE);
        totalCardNum.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 20));


        StackPane overlapDeck = new StackPane();
        overlapDeck.getChildren().addAll(deck,totalCardNum);


        //Settings

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

        hpText = new Text();
        hpText.setText(Integer.toString( character.getHp())+"/"+Integer.toString( character.getMaxHp()));
        hpText.setFill(Color.RED);
        hpText.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 18));
        hpText.setTranslateY(10);
        hpText.setTranslateX(-10);

        goldText = new Text();
        goldText.setText(Integer.toString(character.getGold()) );
        goldText.setFill(Color.YELLOW);
        goldText.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 18));
        goldText.setTranslateY(10);
        goldText.setTranslateX(-10);

        Text name = new Text(character.getName());
        name.setFill(Color.YELLOW);
        name.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 18));
        name.setTranslateY(10);
        name.setTranslateX(-10);

        // Initializing relics
        updateRelics();


        leftUpperLevel = new HBox(20);
        rightUpperLevel = new HBox(40);
        upperLevelContainer = new HBox(770);
        leftUpperLevel.getChildren().addAll(name,hp,hpText,gold,goldText,potions);
        rightUpperLevel.getChildren().addAll(map,overlapDeck,settings);
        upperLevelContainer.getChildren().addAll(leftUpperLevel,rightUpperLevel);

        leftUpperLevel.setTranslateX(40);
        upperLevelContainer.setPrefWidth(width);
        upperLevelContainer.setStyle("-fx-background-color: #808080;"+"-fx-opacity: 0.85;");
        VBox topContainer = new VBox(5);

        topContainer.getChildren().addAll(upperLevelContainer, relics);
        relics.setTranslateX(20);

        hudPane.getChildren().add(topContainer);
        getChildren().add(hudPane);
    }
    public void updateHP(){
        hpText.setText(Integer.toString( character.getHp())+"/"+Integer.toString( character.getMaxHp()));
    }
    public void updateGold(){
        goldText.setText(Integer.toString(character.getGold()));
    }
    public void updateTotalCards(){
        totalCardNum.setText(Integer.toString(character.getDeck().getCards().size()));
    }
    public void updateRelics(){
        relics.getChildren().clear();
        for(int i = 0 ; i < character.getRelics().size(); i++) {
            String path = "resources/images/relic-icons/";
            path = path + character.getRelics().get(i).getName() + ".png";
            System.out.println(path + " for " + character.getRelics().get(i).getName() );
            ImageView relic = createImage(path);
            relics.getChildren().add(relic);
        }

    }
    public HBox updatePotions(){
        ArrayList<Potion> potions = character.getPotions();
        HBox potionsContainer = new HBox(5);
        for(int i = 0; i < potions.size(); i++) {
            Text potionDesc = new Text(potions.get(i).getDescription());
            potionDesc.setFill(Color.WHITE);
            potionDesc.setFont(Font.font("Verdana", 15));
            ImageView potion = createImage("resources/images/"+potions.get(i).getName()+".png");
            potion.setFitWidth(50);
            potion.setFitHeight(50);
            potion.setOnMouseEntered(event -> {
                Robot robot = new Robot();
                int y = (int) (robot.getMouseY() + 30);
                int x = (int) (robot.getMouseX() - 15);
                potionDesc.setX(x);
                potionDesc.setY(y);
                potionDesc.setVisible(true);
                getChildren().add(potionDesc);

            });
            potion.setOnMouseExited(event -> {
                potionDesc.setVisible(false);
                getChildren().remove(potionDesc);
            });

            potionsContainer.getChildren().add(potion);

        }
        return potionsContainer;
    }

    public void enableFloor(int floorNumber){
        floorText = new Text();
        floorText.setText("Floor "+ floorNumber );
        floorText.setFill(Color.YELLOW);
        floorText.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 22));
        floorText.setTranslateY(5);

        upperLevelContainer.getChildren().removeAll(leftUpperLevel,rightUpperLevel);
        upperLevelContainer.setSpacing(345);
        upperLevelContainer.getChildren().addAll(leftUpperLevel,floorText,rightUpperLevel);


    }
    public void disableFloor(){


        upperLevelContainer.getChildren().removeAll(leftUpperLevel,floorText,rightUpperLevel);
        upperLevelContainer.setSpacing(770);
        System.out.println("FLOOR IS DISABLING-----------");
        upperLevelContainer.getChildren().addAll(leftUpperLevel,rightUpperLevel);

    }
    public ImageView createImage(String path){
        ImageView imgV;
        try {
            is = Files.newInputStream(Paths.get(path));
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
            imgV = new ImageView(img);
            imgV.setFitWidth(40);
            imgV.setFitHeight(40);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return imgV;
    }
}