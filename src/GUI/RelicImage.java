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
        Rectangle bg = new Rectangle(175,240);
        //Rectangle texts = new Rectangle(100,60);

        ImageView relicImg;
        setHeight(70);
        setWidth(70);

        try {
            is = Files.newInputStream(Paths.get("resources/images/card"+relic.getName()+".png"));
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
            relicImg = new ImageView(img);
            relicImg.setFitWidth(40);
            relicImg.setFitHeight(40);
        } catch (IOException e) {
            e.printStackTrace();
        } //get the image

        bg.setFill(new ImagePattern(img));

        Text cardName = new Text(relic.getName());
        cardName.setFill(Color.WHITE);
        cardName.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 16));
        cardName.setX(10);
        cardName.setY(10);

        Text cardDesc = new Text(relic.getDescription());
        cardDesc.setFill(Color.WHITE);
        cardDesc.setFont(Font.font("COMIC SANS MS", 10));
        cardDesc.setWrappingWidth(120);
        cardDesc.setX(10);
        cardDesc.setY(10);




        setRotate(-0.5);

        //Positions of texts on card image
        setAlignment(cardDesc, Pos.CENTER);
        setAlignment(cardName,Pos.TOP_CENTER);
        cardDesc.setTranslateY(50);
        cardDesc.setTranslateX(5);
        cardName.setTranslateY(16);


        getChildren().addAll(bg,cardDesc,cardName);

        DropShadow drop = new DropShadow(50, Color.WHITE);
        drop.setInput(new Glow());
        setOnMouseEntered(event -> {
            bg.setTranslateY(-50);
            cardDesc.setTranslateY(-10);
            cardName.setTranslateY(-34);

            setEffect(drop);
        });

        setOnMouseExited(event -> {
            bg.setTranslateY(0);
            cardDesc.setTranslateY(40);
            cardName.setTranslateY(16);

            setEffect(null);
        });



        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));

    }


}
