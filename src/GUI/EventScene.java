package GUI;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EventScene {
    private Scene eventScene;
    private Stage mainStage;
    private Pane mainPane;

    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    final double width = screenBounds.getWidth(); //gets the screen width
    final double height = screenBounds.getHeight();

    InputStream inputStream;
    Image img;
    ImageView imgView;

    public EventScene(){
        // Creating and adding event room's unique image
        try {
            inputStream = Files.newInputStream(Paths.get("resources/images/eventImage.png"));
            img = new Image(inputStream);
            inputStream.close();
            imgView = new ImageView(img);
        }catch (IOException e) {
            e.printStackTrace();
        }
        mainPane.getChildren().add(imgView);
        eventScene = new Scene(mainPane,width,height);
        mainStage = new Stage();
        mainStage.setScene(eventScene);
    }


}
