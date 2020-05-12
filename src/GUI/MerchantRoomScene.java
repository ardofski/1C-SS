package GUI;

import Controller.GameController;
import java.util.ArrayList;
import Model.Map;
import Model.Room.Room;
import javafx.animation.FadeTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Screen;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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
import javafx.util.Duration;

class MerchantRoomScene extends Parent {
    public static final int ROOM_BUTTON_SIZE = 50;
    ArrayList<Product> chosenProducts;

    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double SCREEN_X = screenBounds.getWidth(); //gets the screen width
    double SCREEN_Y = screenBounds.getHeight(); //gets the screen height
    int mapLength;
    Pane root;
    GridPane merchGrid;
    public MerchantRoomScene(GameController gameController,Pane root) {
        this.root = root;
        this.chosenProducts=new ArrayList<Product>();
        Text title = new Text("Welcome to the merchant");
        title.setX(250);
        title.setY(400);
        title.setFill(Color.CHOCOLATE);
        title.setFont(Font.font ("Verdana", 30));
        root.getChildren().add(0,title);
        StackPane buyButton = new buyButton();
        buyButton.setLayoutX(100);
        buyButton.setLayoutY(800);
        root.getChildren().add(buyButton);



        merchGrid = new MerchantRoomGridPane();
        StackPane card1 = new CardImage("card1", "Attack", "3", "description");
        StackPane card2 = new CardImage("card2", "Attack", "1", "description");
        StackPane card3 = new CardImage("card3", "Attack", "0", "description");
        StackPane card4 = new CardImage("card4", "Attack", "2", "description");
        GridPane product1 = new Product(card1, "25");
        GridPane product2 = new Product(card2, "54");
        GridPane product3 = new Product(card3, "81");
        GridPane product4 = new Product(card4, "103");
        merchGrid.add(product1,0,0);
        merchGrid.add(product2,0,1);
        merchGrid.add(product3,1,0);
        merchGrid.add(product4,1,1);
        root.getChildren().add(1,merchGrid);


    }
    private static class MerchantRoomGridPane extends GridPane{

        public MerchantRoomGridPane(){
            this.setStyle("-fx-background-color:#0e2356; -fx-opacity:1;");
            this.setLayoutX(800);
            this.setLayoutY((100));
            this.setHgap(10);
            this.setVgap(10);
        }
    }
    private  class Product extends GridPane{

        public Product(StackPane card, String gold){

            this.add(card,0,0);

            this.setOnMouseClicked(event -> {
                if(chosenProducts.contains(this)) {
                    chosenProducts.remove(this);
                    this.setStyle("-fx-background-color:#0e2356; -fx-opacity:1;");
                }
                else{
                    chosenProducts.add(this);
                    this.setStyle("-fx-background-color:#000000; -fx-opacity:1;");
                }
            });
            this.setVgap(5);
            Text goldT = new Text("           " +gold+ "gold");
            goldT.setFill(Color.WHITE);
            goldT.setFont(Font.font ("Verdana", 12));
            this.add(goldT,0,1);

        }
    }
    private class buyButton extends StackPane{
        Text text;
        public buyButton(){
            text = new Text("buy chosen products");
            text.setFont(text.getFont().font(20));
            text.setFill(Color.BLACK);
            text.setStyle("-fx-background-color:#0e2356; -fx-opacity:1;");
            this.setOnMouseClicked(event ->{
                int size = chosenProducts.size();
                for(int i=0; i<size;i++){
                    //check if u got money
                    Product toBuy = chosenProducts.get(0);
                    merchGrid.getChildren().remove(toBuy);
                    chosenProducts.remove(toBuy);
                }
            });
            getChildren().add(text);
        }
    }
}

