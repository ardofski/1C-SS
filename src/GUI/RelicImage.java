package GUI;

import Model.Relics.Relic;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

/**
 * The type Relic ımage.
 */
public class RelicImage extends StackPane {

    /**
     * The Is.
     */
    InputStream is;
    /**
     * The Img.
     */
    Image img;

    /**
     * Instantiates a new Relic ımage.
     *
     * @param relic the relic
     */
    public RelicImage(Relic relic)
    {
        Rectangle rect = new Rectangle(77,60);

        ImageView relicImg;
        setHeight(200);
        setWidth(200);

        try {
            is = Files.newInputStream(Paths.get("resources/images/relic-icons/"+relic.getName()+".png"));
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
            relicImg = new ImageView(img);
            //relicImg.setFitWidth(40);
            //relicImg.setFitHeight(40);
        } catch (IOException e) {
            e.printStackTrace();
        } //get the image

        rect.setFill(new ImagePattern(img));

        VBox info = new VBox(5);

        Text name = new Text(relic.getName());
        name.setFill(Color.BLACK);
        name.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 11));
        name.setWrappingWidth(77);
        name.setX(10);
        //name.setY(10);

        Text descr = new Text(relic.getDescription());
        descr.setFill(Color.BLACK);
        descr.setFont(Font.font("COMIC SANS MS", 10));
        descr.setWrappingWidth(77);
        //descr.setLayoutY(120);
        descr.setX(50);

        //info.getChildren().addAll(name, descr);
        info.getChildren().addAll(descr);
        info.setTranslateY(77);
        info.setMaxHeight(40);
        info.setStyle("-fx-background-color: rgb(133, 116, 69, .9);" +
                " -fx-border-color: black;  ");

        getChildren().addAll(rect);

        DropShadow drop = new DropShadow(50, Color.WHITE);
        drop.setInput(new Glow());

        setOnMouseEntered(event -> {
            System.out.println("Mouse entered relic.");
            getChildren().add(info);
            setEffect(drop);
        });

        setOnMouseExited(event -> {
            System.out.println("Mouse exited relic.");
            getChildren().remove(info);
            setEffect(null);
        });

        //setOnMousePressed(event -> setEffect(drop));
        //setOnMouseReleased(event -> setEffect(null));

    }


}
