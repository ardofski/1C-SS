package GUI;

import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EventScene extends Parent {

    private Pane mainPane;

    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    final double width = screenBounds.getWidth(); //gets the screen width
    final double height = screenBounds.getHeight();

    InputStream inputStream;
    Image img;
    ImageView imgView;

    public EventScene(String name,String description,String[] choices, String[] effects){
        mainPane = new Pane();
        mainPane.setPrefSize(width, height);
        // Creating and adding event room's unique image
        try {
            inputStream = Files.newInputStream(Paths.get("resources/images/eventRoomBackground.jpg"));
            img = new Image(inputStream);
            inputStream.close();
            imgView = new ImageView(img);
        }catch (IOException e) {
            e.printStackTrace();
        }

        EventImage event = new EventImage(name, description, choices, effects);
        event.setTranslateX(230);
        event.setTranslateY(100);
        mainPane.getChildren().add(event);
        BackgroundImage mapBG = new BackgroundImage(img,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background bg = new Background(mapBG);
        mainPane.setBackground(bg);

        getChildren().add(mainPane);
    }


}
