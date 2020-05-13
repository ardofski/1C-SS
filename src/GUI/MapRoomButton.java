package GUI;

import Model.Room.Room;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MapRoomButton extends StackPane {
    private Text text;
    ImageView image;
    int roomNum;

    /*
           Image image = new Image(getClass().getResourceAsStream("play3.jpg"));
        button.setOnAction(new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            Button button = (Button) e.getSource();
            button.setGraphic(new ImageView(image));
        }
    });
    */
    public MapRoomButton(Room room, int i ,int j,int roomNum,MapScene mapScene) {
        this.roomNum = roomNum;
        image = new ImageView( getRoomImage(room,false) );
        getChildren().addAll( image );

        Rectangle bg = new Rectangle(MapScene.ROOM_BUTTON_SIZE, MapScene.ROOM_BUTTON_SIZE);
        bg.setOpacity(0);
        //bg.setFill(Color.BLACK);
        bg.setEffect(new GaussianBlur(3.5));

        getChildren().addAll( bg );


        setOnMouseEntered(event -> {
            image.setImage( getRoomImage(room,true) );
        });

        setOnMouseExited(event -> {
            image.setImage( getRoomImage(room,false) );
        });

        setOnMouseClicked(event -> {
            mapScene.visit(i,j,room);
        });


        DropShadow drop = new DropShadow(50, Color.WHITE);
        drop.setInput(new Glow());

        setOnMousePressed(event -> setEffect(drop));
        setOnMouseReleased(event -> setEffect(null));
    }

    private Image getRoomImage(Room room,boolean onMouse ){
        InputStream is = null;
        Image img = null;

        try {

            if( room == null){
                is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/empty.png"));
            }
            else if(!onMouse){
                System.out.println("--------Room is not null.---------");
                if( roomNum == 0) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/elite.png"));
                }
                if( roomNum == 1) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/enemy.png"));
                }
                if( roomNum == 2) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/merchant.png"));
                }
                if( roomNum == 3) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/rest.png"));
                }
                if( roomNum == 4) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/treaure.png"));
                }
                if( roomNum == 5) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/unknown.png"));
                }

            }
            else{
                System.out.println("--------Room is not null.---------");
                if( roomNum == 0) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/elite2.png"));
                }
                if( roomNum == 1) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/enemy2.png"));
                }
                if( roomNum == 2) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/merchant2.png"));
                }
                if( roomNum == 3) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/rest2.png"));
                }
                if( roomNum == 4) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/treaure2.png"));
                }
                if( roomNum == 5) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/unknown2.png"));
                }

            }


            /*
            if( room == null) {
                is = Files.newInputStream(Paths.get("resources/images/" + "empty-room.png"));
            }
            else if( room instanceof EnemyRoom)
                is = Files.newInputStream(Paths.get("resources/images/" + "monster-room.png"));
            else if(room instanceof MerchantRoom)
                System.out.println("---------------MERCHANT ROOM--------------");
            */
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
