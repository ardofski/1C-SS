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
import java.util.ArrayList;

public class EventScene extends Parent {

    private Pane mainPane;
    HUDPane hudPane;

    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    final double width = screenBounds.getWidth(); //gets the screen width
    final double height = screenBounds.getHeight();

    InputStream inputStream,is;
    Image img;
    ImageView imgView;
    String name;
    String description;
    ArrayList<Option> choices;
    public EventScene(HUDPane hudpane, EventController eventController, MapScene mapScene){
        this.hudPane = hudPane;
        name = eventController.getEventName();
        description = eventController.getEventDescription();
        choices = eventController.getOptions();
        mainPane = new Pane();
        mainPane.setPrefSize(width, height);
        StackPane eventPane = new StackPane();
        // Creating and adding event room's unique image

/*
        EventImage event = new EventImage(name, description, choices, effects);

        event.setTranslateX(230);
        event.setTranslateY(100);
        mainPane.getChildren().add(event);*/

        Rectangle bg = new Rectangle(920,550);
        //Rectangle texts = new Rectangle(100,60);

        ImageView eventBG, eventImg, buttonImg;
        eventImg = null;
        buttonImg = null;
        eventPane.setPrefHeight(550);
        eventPane.setPrefWidth(920);

        VBox choiceContainer = new VBox();
        choiceContainer.setSpacing(15);

        DropShadow drop = new DropShadow(50, Color.BLACK);
        drop.setInput(new Glow());

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
            is = Files.newInputStream(Paths.get("resources/images/eventImage"+(int)(Math.random()*5)+".jpg"));
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
            eventImg = new ImageView(img);
            eventImg.setFitWidth(250);
            eventImg.setFitHeight(300);
        } catch (IOException e) {
            e.printStackTrace();
        }



        Text nameText = new Text(name);
        nameText.setFill(Color.YELLOW);
        nameText.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 20));
        nameText.setX(10);
        nameText.setY(10);

        Text descriptionText = new Text(description);
        descriptionText.setFill(Color.WHITE);
        descriptionText.setFont(Font.font("COMIC SANS MS", 15));
        descriptionText.setWrappingWidth(600);
        descriptionText.setX(10);
        descriptionText.setY(10);

        Text choice;
        Text[] choicesText = new Text[choices.size()];
        for(int i = 0 ; i < choices.size() ; i++){
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





        setRotate(-0.5);

        //Positions of texts on card image
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


        eventPane.getChildren().addAll(bg,nameText,descriptionText,eventImg, choiceContainer);



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
