package GUI;

import Controller.GameController;
import java.util.ArrayList;

import Controller.MerchantController;
import Controller.RoomController;
import Model.Card;
import Model.Map;
import Model.Potion;
import Model.Relics.Relic;
import Model.Room.Room;
import javafx.animation.FadeTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Screen;
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
    ArrayList<CardProduct> chosenProducts;

    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double SCREEN_X = screenBounds.getWidth(); //gets the screen width
    double SCREEN_Y = screenBounds.getHeight(); //gets the screen height
    int mapLength;
    Pane root;
    GridPane merchGrid;

    ArrayList<Card> cards;
    ArrayList<Relic> relics;
    ArrayList<Potion> potions;
    RoomController controller;

    public MerchantRoomScene(RoomController controller, Pane root) {
        this.controller = controller;
        this.root = root;
        this.chosenProducts=new ArrayList<CardProduct>();
        Text title = new Text("Welcome to the merchant");
        title.setX(50);
        title.setY(50);
        title.setFill(Color.CHOCOLATE);
        title.setFont(Font.font ("Verdana", 20));
        root.getChildren().add(0,title);
        StackPane buyButton = new buyButton();
        buyButton.setLayoutX(100);
        buyButton.setLayoutY(800);
        root.getChildren().add(buyButton);

        cards = ((MerchantController)controller).getCards();
        relics = ((MerchantController)controller).getRelics();
        potions = ((MerchantController)controller).getPotions();

        merchGrid = new MerchantRoomGridPane();

        System.out.println("HERE===============\n" + cards);
        for(int i = 0; i < cards.size(); i++){
            Card c = cards.get(i);
            StackPane cardPane = new CardImage(c.getName(), c.getType(), ""+ c.getEnergy(), c.getDescription());
            int price = (int) (Math.random() * 100 + 50);
            GridPane product = new CardProduct(cardPane, "" + price , i);
            merchGrid.add(product, i, 0);
        }


 

        root.getChildren().add(1,merchGrid);


    }
    private static class MerchantRoomGridPane extends GridPane{

        public MerchantRoomGridPane(){
            //this.setStyle("-fx-background-color:#0e2356; -fx-opacity:1;");
            this.setLayoutX(350);
            this.setLayoutY((80));
            this.setHgap(10);
            this.setVgap(10);
        }
    }
    private class Product{
        int index;
    }
    private  class CardProduct extends GridPane{
        int index;
        int price;

        public CardProduct(StackPane card, String gold, int index){
            this.price = Integer.parseInt(gold);
            this.index = index;

            this.add(card,0,0);

            this.setOnMouseClicked(event -> {
                    AlertPane alert = new AlertPane("card", index, price);
            });
            this.setVgap(5);
            Text goldT = new Text("            " +price+ " gold");
            goldT.setFill(Color.WHITE);
            goldT.setFont(Font.font ("Verdana", 12));
            this.add(goldT,0,1);

        }
    }

    private class RelicProduct extends GridPane{
        int index;
        int price;

        public RelicProduct(StackPane relicPane, String gold, int index){
            this.price = Integer.parseInt(gold);
            this.index = index;

            this.add(relicPane,0,0);

            this.setOnMouseClicked(event -> {
                AlertPane alert = new AlertPane("relic", index, price);
            });
            this.setVgap(5);
            Text goldT = new Text(" " +price+ " gold");
            goldT.setFill(Color.WHITE);
            goldT.setFont(Font.font ("Verdana", 12));
            this.add(goldT,0,1);

        }
    }

    private class AlertPane extends Alert{

        public AlertPane(String productType, int index, int price) {
            super(Alert.AlertType.NONE);
            setTitle("Merchant says that");
            setContentText("Do you want to buy product?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType cancelButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            getButtonTypes().setAll(yesButton, cancelButton);
            showAndWait().ifPresent(type -> {
                boolean canBuy = false ;
                if (type == yesButton) {
                    switch (productType){
                        case "card": canBuy = ((MerchantController)controller).buyCard(index, price); break;
                        case "relic": canBuy = ((MerchantController)controller).buyRelic(index, price); break;
                        case "potion": canBuy = ((MerchantController)controller).buyPotion(index, price); break;
                        default: canBuy = false;
                    }
                }
                else {
                    close();
                }
                if( !canBuy){
                    Alert error = new Alert(AlertType.ERROR);
                    error.setContentText("Cannot buy product");
                    ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.YES);
                    error.showAndWait();
                }
            });
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
                    CardProduct toBuy = chosenProducts.get(0);
                    merchGrid.getChildren().remove(toBuy);
                    chosenProducts.remove(toBuy);
                }
            });
            getChildren().add(text);
        }
    }
    private class deleteCardButton extends StackPane{
        Text text;
        public deleteCardButton(){
            text = new Text("Delete Card");
            text.setFont(text.getFont().font(20));
            text.setFill(Color.BLACK);
            text.setStyle("-fx-background-color:#0e2356; -fx-opacity:1;");
            this.setOnMouseClicked(event ->{
                int size = chosenProducts.size();
                for(int i=0; i<size;i++){
                    //check if u got money
                    CardProduct toBuy = chosenProducts.get(0);
                    merchGrid.getChildren().remove(toBuy);
                    chosenProducts.remove(toBuy);
                }
            });
            getChildren().add(text);
        }
    }

    update
}

