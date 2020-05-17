package GUI;

import Model.Potion;
import Model.Relics.Relic;
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

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PotionImage extends StackPane {

    InputStream is;
    Image img;

    public PotionImage(Potion potion, int width, int height)
    {
        Rectangle rect = new Rectangle(width, height);//80 80

        ImageView relicImg;
        setHeight(height);//70
        setWidth(width);//70

        try {
            is = Files.newInputStream(Paths.get("resources/images/potion-icons/"+potion.getName()+".png"));
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
            relicImg = new ImageView(img);
            relicImg.setFitWidth(width);
            relicImg.setFitHeight(height);
        } catch (IOException e) {
            e.printStackTrace();
        } //get the image

        rect.setFill(new ImagePattern(img));
        //rect.setBackground(new Background(new BackgroundImage(img,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        //                new BackgroundSize(1.0, 1.0, true, true, false, false))));

        VBox info = new VBox(3);

        Text name = new Text(potion.getName());
        name.setFill(Color.BLACK);
        name.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 11));
        name.setWrappingWidth(width);
        //name.setX(10);

        Text descr = new Text(potion.getDescription());
        descr.setFill(Color.BLACK);
        descr.setFont(Font.font("COMIC SANS MS", 10));
        descr.setWrappingWidth(width);
        //descr.setY(77);
        //descr.setX(50);

        info.getChildren().addAll(name, descr);
        //info.setTranslateX(10);
        info.setTranslateY(87); //97
        info.setMaxWidth(width);
        //info.setLayoutX(10);
        info.setStyle("-fx-background-color: rgb(133, 116, 69, .9);" +
                " -fx-border-color: black;  ");

        getChildren().addAll(rect);

        DropShadow drop = new DropShadow(50, Color.WHITE);
        drop.setInput(new Glow());

        setOnMouseEntered(event -> {
            getChildren().add(info);
            //setEffect(drop);
        });

        setOnMouseExited(event -> {
            getChildren().remove(info);
            //setEffect(null);
        });

    }
}
