package DBConnection;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import Controller.*;
import Model.Card;
import Model.Character;
import Model.Map;
import Model.Player;
import Model.Relics.RelicFactory;
import Model.Reward;
import Model.Room.EnemyRoom;
import Model.Room.RestRoom;
import Model.Room.Room;
import Model.Room.TreasureRoom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Test {
    public static void main(String [] args){

        ImageView relicImg;

        InputStream is;
        Image img;

        try {
            is = Files.newInputStream(Paths.get("resources/images/relic-icons/Akabeko.png"));
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
            relicImg = new ImageView(img);
            relicImg.setFitWidth(40);
            relicImg.setFitHeight(40);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
