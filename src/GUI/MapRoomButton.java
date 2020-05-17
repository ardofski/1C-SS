package GUI;

import Controller.GameController;
import Model.Room.*;
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
    public MapRoomButton(GameController gameController, int i , int j, int roomNum, MapScene mapScene) {
        this.roomNum = roomNum;
        Room room = gameController.getLocations()[i][j];
        image = new ImageView( getRoomImage(room,false,gameController.isVisited(i,j)) );
        getChildren().addAll( image );

        Rectangle bg = new Rectangle(MapScene.ROOM_BUTTON_SIZE, MapScene.ROOM_BUTTON_SIZE);
        bg.setOpacity(0);
        //bg.setFill(Color.BLACK);
        bg.setEffect(new GaussianBlur(3.5));

        getChildren().addAll( bg );


        DropShadow drop = new DropShadow(50, Color.WHITE);
        drop.setInput(new Glow());

        setOnMouseEntered(event -> {
            image.setImage( getRoomImage(room,true,gameController.isVisited(i,j)) );
            setEffect(drop);
        });

        setOnMouseExited(event -> {
            image.setImage( getRoomImage(room,false,gameController.isVisited(i,j)) );
            setEffect(null);
        });

        setOnMouseClicked(event -> {
            if(gameController.isAccesible(i,j) ){
                image.setImage( getRoomImage(room,false,gameController.isAccesible(i,j) ) );
                mapScene.visit(i,j,room);
            }
        });




        //setOnMousePressed(event -> setEffect(drop));
        //setOnMouseReleased(event -> setEffect(null));
    }

    private Image getRoomImage(Room room,boolean onMouse,boolean isVisited ){
        InputStream is = null;
        Image img = null;

        try {
            System.out.println(onMouse);
            if( room == null){
                is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/empty.png"));
            }
            else if(isVisited){
                System.out.println(room.getClass());
                if(room instanceof EnemyRoom)
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/enemy2.png"));
                else if(room instanceof UnknownRoom || room instanceof EventRoom)
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/unknown2.png"));
                else if(room instanceof MerchantRoom)
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/merchant2.png"));
                else if(room instanceof TreasureRoom)
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/treasure2.png"));
                else if(room instanceof RestRoom)
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/rest2.png"));
            }
            //else if(onMouse){
              //  is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/enemy3.png"));
            //}
            else{
                System.out.println(room.getClass());
                if(room instanceof EnemyRoom){
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/enemy.png"));
                    System.out.println("room is enemy room");
                }
                else if(room instanceof UnknownRoom || room instanceof EventRoom){
                    System.out.println( "EVENT ROOM TYPE IS : " + room.getClass() );
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/unknown.png"));
                }
                else if(room instanceof MerchantRoom)
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/merchant.png"));
                else if(room instanceof TreasureRoom)
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/treasure.png"));
                else if(room instanceof RestRoom)
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/rest.png"));
            }
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
