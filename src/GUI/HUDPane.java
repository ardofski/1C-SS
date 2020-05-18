package GUI;

import Controller.Fight.FightController;
import Model.Character;
import Model.Potion;
import javafx.geometry.Rectangle2D;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
    Potion chosen;

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
    VBox topContainer,potionsWithDesc = null;
    //Texts
    Text hpText, goldText, totalCardNum, floorText;

    Pane upperWithBackground = null;

    //Containers
    HBox leftUpperLevel, rightUpperLevel, upperLevelContainer, potions, relics;

    PotionImage[] potionImages;

    Image img;
    InputStream is;

    ArrayList<Potion> potionList ;
    public HUDPane(Character character){
        this.character = character;
         hudPane = new StackPane();

         hudPane.setPrefWidth(width-100);
         hudPane.setPrefHeight(225);

         chosen = null;
         potionImages = null;

        relics = new HBox(20);
        potions = new HBox(10);
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

        leftUpperLevel = new HBox(20);
        rightUpperLevel = new HBox(40);
        upperLevelContainer = new HBox(400);


        potionsWithDesc = new VBox(10);

        //Potion
        updatePotions();
        updateRelics();

        potionsWithDesc.getChildren().addAll(potions);


        leftUpperLevel.getChildren().addAll(name,hp,hpText,gold,goldText,potionsWithDesc);
        rightUpperLevel.getChildren().addAll(map,overlapDeck,settings);
        upperLevelContainer.getChildren().addAll(leftUpperLevel,rightUpperLevel);

        leftUpperLevel.setTranslateX(20);

        upperLevelContainer.setPrefWidth(width);
        upperLevelContainer.setSpacing(700);
        if (character.getPotions().size() > 0)
            upperLevelContainer.setSpacing(700 - (character.getPotions().size() * 35) );

        upperWithBackground = new Pane();
        upperWithBackground.getChildren().addAll(upperLevelContainer);
        upperWithBackground.setStyle("-fx-background-color: #808080;"+"-fx-opacity: 0.85;");
        upperWithBackground.setMinWidth(width);
        upperWithBackground.setPrefHeight(50);

        topContainer = new VBox(5);
        topContainer.getChildren().addAll(upperWithBackground, relics);

        relics.setTranslateX(30);

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

            Text relicDesc = new Text(character.getRelics().get(i).getDescription());
            relicDesc.setFill(Color.WHITE);
            relicDesc.setFont(Font.font("Verdana", 14));

            System.out.println(path + " for " + character.getRelics().get(i).getName() );
            ImageView relic = createImage(path);

            //RelicImage relicim = new RelicImage(character.getRelics().get(i));

            relic.setOnMouseEntered( event -> {
                System.out.println("BUFF IS PRINTED********");

                Robot robot = new Robot();
                int y = (int) (robot.getMouseY());
                int x = (int) (robot.getMouseX() +100);

                relicDesc.setX(x);
                relicDesc.setY(y);
                relicDesc.setVisible(true);
                topContainer.getChildren().add(relicDesc);
            });

            relic.setOnMouseExited( event -> {
                relicDesc.setVisible(false);
                topContainer.getChildren().remove(relicDesc);
            });
            relics.getChildren().add(relic);
        }

    }
    public void  updatePotions(){
        potionList = character.getPotions();
        System.out.println("POTION LIST: "+character.getPotions());
        potionImages = new PotionImage[character.getPotions().size()];
        potions.getChildren().clear();


        upperLevelContainer.setSpacing(340 -  30 * character.getPotions().size() );

        for(int i = 0; i < potionList.size(); i++) {

            PotionImage potionImage = new PotionImage(potionList.get(i), 40, 40);
            potionImages[i] = potionImage;
            potionImage.setId(Integer.toString(i));
            DropShadow drop = new DropShadow(25, Color.DARKRED);
            drop.setInput(new Glow());

            Text potionDesc = new Text(potionList.get(i).getDescription());
            potionDesc.setFill(Color.WHITE);
            potionDesc.setFont(Font.font("Verdana", 14));

            potionImage.setOnMouseClicked(event -> {
                for(int a = 0; a < potionList.size(); a++){
                    potionImages[a].setEffect(null);
                }
                potionImage.setEffect(drop);
                chosen = potionList.get(Integer.parseInt(potionImage.getId()));
                System.out.println("HUD PANE CHOSEN POTION->"+chosen);
            });

            potionImage.setOnMouseEntered( event -> {
                System.out.println("BUFF IS PRINTED********");

                Robot robot = new Robot();
                int y = (int) (robot.getMouseY()+40);
                int x = (int) (robot.getMouseX()+10);

                potionDesc.setX(x);
                potionDesc.setY(y);
                potionDesc.setVisible(true);
                upperWithBackground.getChildren().add(potionDesc);
            });

            potionImage.setOnMouseExited( event -> {
                potionDesc.setVisible(false);
                upperWithBackground.getChildren().remove(potionDesc);
            });
            potions.getChildren().add(potionImage);

        }

    }

    public Potion getChosenPotion(){
        return chosen;
    }

    public void enableFloor(int floorNumber){
        floorText = new Text();
        floorText.setText("Floor "+ floorNumber );
        floorText.setFill(Color.YELLOW);
        floorText.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 22));
        floorText.setTranslateY(5);

        upperLevelContainer.getChildren().removeAll(leftUpperLevel,rightUpperLevel);

        upperLevelContainer.setSpacing(340);

        if(character.getPotions().size() > 0)
            upperLevelContainer.setSpacing(340 - 30 * potionList.size());

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