package GUI;

import Controller.EventController;
import Model.Options.Option;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * The type Event scene.
 */
public class EventScene extends Parent {

    /**
     * The Main pane.
     */
    private Pane mainPane;
    /**
     * The Hud pane.
     */
    HUDPane hudPane;

    /**
     * The Screen bounds.
     */
    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    /**
     * The Width.
     */
    final double width = screenBounds.getWidth(); //gets the screen width
    /**
     * The Height.
     */
    final double height = screenBounds.getHeight();

    /**
     * The Input stream.
     */
    InputStream inputStream, /**
     * The Is.
     */
    is;
    /**
     * The Img.
     */
    Image img;
    /**
     * The Img view.
     */
    ImageView imgView;
    /**
     * The Name.
     */
    String name;
    /**
     * The Description.
     */
    String description;
    /**
     * The Choices.
     */
    ArrayList<Option> choices;

    /**
     * Instantiates a new Event scene.
     *
     * @param hudpane         the hudpane
     * @param eventController the event controller
     * @param mapScene        the map scene
     */
    public EventScene(HUDPane hudpane, EventController eventController, MapScene mapScene){

        // Initializing required values
        this.hudPane = hudPane;
        name = eventController.getEventName();
        description = eventController.getEventDescription();
        choices = eventController.getOptions();
        mainPane = new Pane();
        mainPane.setPrefSize(width, height);
        StackPane eventPane = new StackPane();


        Rectangle bg = new Rectangle(920,550);

        // Declaring and setting properties of interface elements
        ImageView eventBG, eventImg, buttonImg;
        eventImg = null;
        buttonImg = null;
        eventPane.setPrefHeight(550);
        eventPane.setPrefWidth(920);

        VBox choiceContainer = new VBox();
        choiceContainer.setSpacing(15);

        DropShadow drop = new DropShadow(50, Color.BLACK);
        drop.setInput(new Glow());
        // Creating and adding event room's unique image
        try {
            is = Files.newInputStream(Paths.get("resources/images/eventBackground.jpg"));
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
            eventBG = new ImageView(img);
            eventBG.setFitWidth(400);
            eventBG.setFitHeight(240);
        } catch (IOException e) {
            e.printStackTrace();
        } //get the image

        bg.setFill(new ImagePattern(img));

        try {
            is = Files.newInputStream(Paths.get("resources/images/eventImage"+(int)(Math.random()*6)+".jpg"));
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
            eventImg = new ImageView(img);
            eventImg.setFitWidth(250);
            eventImg.setFitHeight(300);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Text object for event name
        Text nameText = new Text(name);
        nameText.setFill(Color.YELLOW);
        nameText.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 20));
        nameText.setX(10);
        nameText.setY(10);

        // Text object for event description
        Text descriptionText = new Text(description);
        descriptionText.setFill(Color.WHITE);
        descriptionText.setFont(Font.font("COMIC SANS MS", 15));
        descriptionText.setWrappingWidth(500);
        descriptionText.setX(10);
        descriptionText.setY(10);


        // Storing all choices as array and initiliazing their GUI elements.
        Text choice;
        Text[] choicesText = new Text[choices.size()];
        System.out.println( "IN EVENT ROOM, CHOICE SIZE IS   : " + choices.size());
        System.out.println( "DIALOGUE OF EVENT IS : " + description );
        for(int i = 0 ; i < choices.size() ; i++){
            System.out.println( "IN EVENT ROOM, CHOICE IS   : " + choices.get(i) );
            choice = new Text(choices.get(i).getDescription());
            choice.setFill(Color.WHITE);
            choice.setFont(Font.font("COMIC SANS MS", 15));
            choice.setX(20);
            choice.setY(20);
            choicesText[i] = choice;
            StackPane button = new StackPane();
            try {
                is = Files.newInputStream(Paths.get("resources/images/eventButtonImage.jpg"));
                img = new Image(is);
                is.close(); //this is to give access other programs to that image as well.
                buttonImg = new ImageView(img);
                buttonImg.setFitWidth(500);
                buttonImg.setFitHeight(25);
            } catch (IOException e) {
                e.printStackTrace();
            }

            button.setId(Integer.toString(i));
            button.getChildren().addAll(buttonImg, choicesText[i]);

            button.setPrefWidth(500);
            button.setPrefHeight(25);

            int finalIndex = i;
            // Mouse Listeners for button
            button.setOnMouseClicked(event -> {
                //System.out.println(eventController.getCharacter().getDeck().getCards());
                eventController.applyOption(Integer.parseInt(button.getId()));
                //System.out.println(eventController.getCharacter().getDeck().getCards());
                getChildren().remove(mainPane);
                getChildren().add(mapScene);

            });
            button.setOnMouseEntered( event ->{
                button.setTranslateY(-3);

            });
            button.setOnMouseExited( event -> {
                button.setTranslateY(3);
            });
            choiceContainer.getChildren().addAll(button);

        }

        //Positions of texts on event scene
        eventPane.setAlignment(eventImg, Pos.TOP_LEFT);
        eventPane.setAlignment(descriptionText, Pos.TOP_CENTER);
        eventPane.setAlignment(nameText,Pos.TOP_LEFT);
        eventPane.setAlignment(choiceContainer,Pos.BOTTOM_CENTER);
        eventPane.setAlignment(buttonImg,Pos.BOTTOM_CENTER);

        eventImg.setTranslateX(20);
        eventImg.setTranslateY(95);

        nameText.setTranslateX(45);
        nameText.setTranslateY(25);

        descriptionText.setTranslateX(180);
        descriptionText.setTranslateY(150);

        choiceContainer.setTranslateX(80);
        choiceContainer.setTranslateY(350);
        /*
        cardDesc.setTranslateY(50);
        cardDesc.setTranslateX(5);
        cardName.setTranslateY(16);

         */

        // Adding all elements to event pane
        eventPane.getChildren().addAll(bg,nameText,descriptionText,eventImg, choiceContainer);


        // Creating and setting background
        try {
            inputStream = Files.newInputStream(Paths.get("resources/images/eventRoomBackground.jpg"));
            img = new Image(inputStream);
            inputStream.close();
            imgView = new ImageView(img);
        }catch (IOException e) {
            e.printStackTrace();
        }

        BackgroundImage mapBG = new BackgroundImage(img,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1, 1, true, true, false, false));
        Background bg2 = new Background(mapBG);
        mainPane.setBackground(bg2);
        mainPane.getChildren().addAll(eventPane, hudpane);
        eventPane.setTranslateX(250);
        eventPane.setTranslateY(80);

        getChildren().add(mainPane);
    }


}
