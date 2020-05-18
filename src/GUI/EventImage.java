package GUI;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * The type Event ımage.
 */
class EventImage extends StackPane {
    /**
     * The Is.
     */
    InputStream is;
    /**
     * The Img.
     */
    Image img;


    /**
     * Instantiates a new Event ımage.
     *
     * @param name        the name
     * @param description the description
     * @param choices     the choices
     * @param effects     the effects
     */
    public EventImage(String name,String description,String[] choices, String[] effects)
    {
        Rectangle bg = new Rectangle(920,550);
        //Rectangle texts = new Rectangle(100,60);

        // Declaring required image views
        ImageView eventBG, eventImg, buttonImg;
        eventImg = null;
        buttonImg = null;
        setHeight(550);
        setWidth(920);

        // Vertical box that contains all the choices
        VBox choiceContainer = new VBox();
        choiceContainer.setSpacing(15);

        DropShadow drop = new DropShadow(50, Color.BLACK);
        drop.setInput(new Glow());

        // Creating a background that is stored in resources as an image
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
        // Creating event image that is stored in resources as an image
        try {
            is = Files.newInputStream(Paths.get("resources/images/eventImage.jpg"));
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
        descriptionText.setWrappingWidth(600);
        descriptionText.setX(10);
        descriptionText.setY(10);

        // Storing all choices as array and initiliazing their GUI elements.
        Text choice;
        Text[] choicesText = new Text[choices.length];
        for(int i = 0 ; i < choices.length ; i++){
            choice = new Text(choices[i]);
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
                System.out.println("This button is clicked " +effects[finalIndex]);

            });
            button.setOnMouseEntered( event ->{
                button.setTranslateY(-3);

            });
            button.setOnMouseExited( event -> {
                button.setTranslateY(3);
            });
            choiceContainer.getChildren().addAll(button);

        }

        // Creating leave button
        try {
            StackPane leaveB = new StackPane();
            is = Files.newInputStream(Paths.get("resources/images/eventButtonImage.jpg"));
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
            buttonImg = new ImageView(img);
            buttonImg.setFitWidth(500);
            buttonImg.setFitHeight(25);

            Text leave = new Text("Leave");
            leave.setFill(Color.WHITE);
            leave.setFont(Font.font("COMIC SANS MS", 15));
            leave.setX(20);
            leave.setY(20);

            leaveB.getChildren().addAll( buttonImg, leave);
            leaveB.setOnMouseClicked(event -> {
                getChildren().remove(this);
                //getChildren().add(mapScene);
            });
            choiceContainer.getChildren().addAll(leaveB);

        } catch (IOException e) {
            e.printStackTrace();
        }


        // Alignments for GUI elements
        setRotate(-0.5);

        //Positions of texts on card image
        setAlignment(eventImg,Pos.TOP_LEFT);
        setAlignment(descriptionText, Pos.TOP_CENTER);
        setAlignment(nameText,Pos.TOP_LEFT);
        setAlignment(choiceContainer,Pos.BOTTOM_CENTER);
        setAlignment(buttonImg,Pos.BOTTOM_CENTER);

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


        getChildren().addAll(bg,nameText,descriptionText,eventImg, choiceContainer);



    }


}
