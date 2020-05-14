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
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

class EventImage extends StackPane {
    InputStream is;
    Image img;

    public EventImage(String name,String description,String[] choices )
    {
        Rectangle bg = new Rectangle(400,240);
        //Rectangle texts = new Rectangle(100,60);

        ImageView eventBG, eventImg, buttonImg;
        eventImg = null;
        buttonImg = null;
        setHeight(210);
        setWidth(150);


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
            is = Files.newInputStream(Paths.get("resources/images/eventImage.jpg"));
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
            eventImg = new ImageView(img);
            eventImg.setFitWidth(120);
            eventImg.setFitHeight(140);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            is = Files.newInputStream(Paths.get("resources/images/eventButtonImage.jpg"));
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
            buttonImg = new ImageView(img);
            buttonImg.setFitWidth(180);
            buttonImg.setFitHeight(30);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Text nameText = new Text(name);
        nameText.setFill(Color.YELLOW);
        nameText.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 16));
        nameText.setX(10);
        nameText.setY(10);

        Text descriptionText = new Text(description);
        descriptionText.setFill(Color.WHITE);
        descriptionText.setFont(Font.font("COMIC SANS MS", 15));
        descriptionText.setWrappingWidth(120);
        descriptionText.setX(10);
        descriptionText.setY(10);

        Text choice;
        Text[] choicesText = new Text[choices.length];
        for(int i = 0 ; i < choices.length ; i++){
            choice = new Text(choices[i]);
            choice.setFill(Color.WHITE);
            choice.setFont(Font.font("COMIC SANS MS", 15));
            choice.setX(20);
            choice.setY(20);
            choice.setOnMouseClicked(event -> {

                // IMPLEMENT THIS PART
            });
            choicesText[i] = choice;
        }


        setRotate(-0.5);

        //Positions of texts on card image
        setAlignment(eventImg,Pos.TOP_LEFT);
        setAlignment(descriptionText, Pos.CENTER);
        setAlignment(nameText,Pos.TOP_LEFT);
        setAlignment(choicesText[0],Pos.BOTTOM_CENTER);
        setAlignment(buttonImg,Pos.BOTTOM_CENTER);

        eventImg.setTranslateX(8);
        eventImg.setTranslateY(40);

        nameText.setTranslateX(20);
        nameText.setTranslateY(5);

        descriptionText.setTranslateX(20);
        descriptionText.setTranslateY(-40);

        buttonImg.setTranslateX(50);
        buttonImg.setTranslateY(-60);

        choicesText[0].setTranslateY(-70);
        /*
        cardDesc.setTranslateY(50);
        cardDesc.setTranslateX(5);
        cardName.setTranslateY(16);

         */


        getChildren().addAll(bg,nameText,descriptionText,eventImg, buttonImg, choicesText[0]);



    }


}
