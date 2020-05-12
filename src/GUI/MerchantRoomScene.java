package GUI;

import Controller.GameController;
import Model.Map;
import Model.Room.Room;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Screen;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import Controller.GameController;
import Model.Map;
import Model.Room.Room;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Screen;

class MerchantRoomScene extends Parent {
    public static final int ROOM_BUTTON_SIZE = 50;

    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double SCREEN_X = screenBounds.getWidth(); //gets the screen width
    double SCREEN_Y = screenBounds.getHeight(); //gets the screen height
    int mapLength;
    Pane root;

    public MerchantRoomScene(GameController gameController,Pane root) {
        this.root = root;
        root.getChildren().addAll( new Button("merchant room"));
    }
}

