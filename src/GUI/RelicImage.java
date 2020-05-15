package GUI;

import Model.Relics.Relic;
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

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RelicImage extends StackPane {

    InputStream is;
    Image img;

    public RelicImage(Relic relic)
    {
        Rectangle rect = new Rectangle(80,80);

        ImageView relicImg;
        setHeight(70);
        setWidth(70);

        try {
            is = Files.newInputStream(Paths.get("resources/images/relic-icons/"+relic.getName()+".png"));
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
            relicImg = new ImageView(img);
            relicImg.setFitWidth(40);
            relicImg.setFitHeight(40);
        } catch (IOException e) {
            e.printStackTrace();
        } //get the image

        rect.setFill(new ImagePattern(img));

       StackPane info = new StackPane();

        Text name = new Text(relic.getName());
        name.setFill(Color.BLACK);
        name.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 12));
        name.setX(10);
        name.setY(10);

        Text descr = new Text(relic.getDescription());
        descr.setFill(Color.BLACK);
        descr.setFont(Font.font("COMIC SANS MS", 8));
        descr.setWrappingWidth(40);
        descr.setX(10);
        descr.setY(10);

        info.getChildren().addAll(name, descr);
        info.setAlignment(descr, Pos.CENTER);
        info.setAlignment(name, Pos.TOP_CENTER);
        //cardDesc.setTranslateY(50);
        //cardDesc.setTranslateX(5);
        //cardName.setTranslateY(16);
        info.setTranslateX(10);
        info.setTranslateY(10);




        //setRotate(-0.5);

        //Positions of texts on card image
        //setAlignment(cardDesc, Pos.CENTER);
        //setAlignment(cardName,Pos.TOP_CENTER);


        getChildren().addAll(rect,descr,name);

        DropShadow drop = new DropShadow(50, Color.WHITE);
        drop.setInput(new Glow());

        setOnMouseEntered(event -> {
            //rect.setTranslateY(-10);
            getChildren().addAll(info);
            setEffect(drop);
        });

        setOnMouseExited(event -> {
            getChildren().remove(info);
            //rect.setTranslateY(0);
            setEffect(null);
        });

        //setOnMousePressed(event -> setEffect(drop));
        //setOnMouseReleased(event -> setEffect(null));

    }


}
