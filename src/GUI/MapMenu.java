package GUI;

import Controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;


public class MapMenu extends Pane {
    public final static int WIDTH = 200;
    public final static int HEIGHT = 300;
    public final static int MARGIN = 10;
    private Text text;

    public MapMenu(GameController gameControler) {
        setPrefSize(WIDTH,HEIGHT);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double SCREEN_X = screenBounds.getWidth(); //gets the screen width
        double SCREEN_Y = screenBounds.getHeight(); //gets the screen height


        setTranslateX( SCREEN_X - WIDTH - 100);
        setTranslateY( HEIGHT/3 );

        Button gameSaveButton = new Button("Save Game");
        gameSaveButton.setOnAction(event -> {
            gameControler.saveGame();
        });
        VBox verticalBox = new VBox( gameSaveButton );
        verticalBox.setPrefSize(WIDTH,HEIGHT);
        verticalBox.setAlignment(Pos.TOP_CENTER);
        verticalBox.setMargin(gameSaveButton,new Insets(10,10,10,10));



        Rectangle bg = new Rectangle(WIDTH, HEIGHT);
        bg.setOpacity(0.6);
        bg.setFill(Color.BLACK);
        bg.setEffect(new GaussianBlur(3.5));


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

