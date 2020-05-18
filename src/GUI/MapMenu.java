package GUI;

import Controller.MapController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


public class MapMenu extends Pane {
    public final static int WIDTH = 200;
    public final static int HEIGHT = 400;
    public final static int MARGIN = 10;
    private Text text;
    InputStream is;
    Image img;

    public MapMenu(MapController gameControler) {
        setPrefSize(WIDTH,HEIGHT);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double SCREEN_X = screenBounds.getWidth(); //gets the screen width
        double SCREEN_Y = screenBounds.getHeight(); //gets the screen height


        setTranslateX( SCREEN_X - WIDTH - 20);
        setTranslateY( HEIGHT/3 );

        Button gameSaveButton = new Button("Save Game");
        getStylesheets().add(getClass().getResource("lisStyles.css").toExternalForm());
        gameSaveButton.setOnAction(event -> {
            gameControler.saveGame();
        });
        VBox verticalBox = new VBox( gameSaveButton );
        verticalBox.setPrefSize(WIDTH,HEIGHT);
        verticalBox.setAlignment(Pos.TOP_CENTER);
        verticalBox.setMargin(gameSaveButton,new Insets(10,10,10,10));

        Rectangle legendRect = new Rectangle(200, 250);
        try {
            is = Files.newInputStream(Paths.get("resources/images/legend.png"));
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
        } catch (IOException e) {
            e.printStackTrace();
        } //get the image
        legendRect.setFill(new ImagePattern(img));
        verticalBox.getChildren().add(legendRect);

        Rectangle bg = new Rectangle(WIDTH, HEIGHT);
        bg.setOpacity(0);
        //bg.setFill(Color.BLACK);
        bg.setStyle("-fx-background-color: transparent;");
        //bg.setEffect(new GaussianBlur(3.5));



        //setRotate(-0.5);
        getChildren().addAll(bg, verticalBox);

        setOnMouseEntered(event -> {
            //bg.setTranslateX(10);
            //text.setTranslateX(10);
            //bg.setFill(Color.WHITE);
            //text.setFill(Color.BLACK);
        });

        setOnMouseExited(event -> {
            //bg.setTranslateX(0);
            //text.setTranslateX(0);
            //bg.setFill(Color.BLACK);
            //text.setFill(Color.WHITE);
        });

    }
}

