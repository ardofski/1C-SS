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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
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
    GridPane relicGrid;

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

        cards = ((MerchantController)controller).getCards();
        relics = ((MerchantController)controller).getRelics();
        potions = ((MerchantController)controller).getPotions();

        merchGrid = new MerchantRoomGridPane();
        for(int i = 0; i < cards.size(); i++){
            Card c = cards.get(i);
            StackPane cardPane = new CardImage(c.getName(), c.getType(), ""+ c.getEnergy(), c.getDescription());
            int price = (int) (Math.random() * 10 );
            GridPane product = new CardProduct(cardPane, "" + price , i);
            merchGrid.add(product, i, 0);
        }
        root.getChildren().add(1,merchGrid);

        relicGrid = new GridPane();
        this.setLayoutX(350);
        this.setLayoutY((200));
        for(int i = 0; i < relics.size(); i++){
            System.out.println(relics.get(i).getName());
            //StackPane relicPane = new RelicImage(relics.get(i));
            //int price = (int) (Math.random() * 10 );
            //GridPane product = new CardProduct(relicPane, "" + price , i);
            //merchGrid.add(product, i, 0);
        }

        StackPane deleteBtn = new deleteCardButton();
        root.getChildren().add(deleteBtn);

    }
    private static class MerchantRoomGridPane extends GridPane{

        public MerchantRoomGridPane(){
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
                else {
                    updateGrid(index);
                }
            });
        }
    }

    private class deleteCardButton extends StackPane{
        InputStream is;
        Image img;
        Text text;
        public deleteCardButton(){
            this.setLayoutX(1000);
            this.setLayoutY(380);
            Rectangle rect = new Rectangle(175,240);

            ImageView btnBG;
            setHeight(210);
            setWidth(150);

            try {
                is = Files.newInputStream(Paths.get("resources/images/deleteCard.png"));
                img = new Image(is);
                is.close(); //this is to give access other programs to that image as well.
                btnBG = new ImageView(img);
                btnBG.setFitWidth(40);
                btnBG.setFitHeight(40);
            } catch (IOException e) {
                e.printStackTrace();
            } //get the image

            rect.setFill(new ImagePattern(img));


            getChildren().add(rect);

            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());

            setOnMouseEntered(event -> {

                setEffect(drop);
            });

            setOnMouseExited(event -> {

                setEffect(null);
            });



            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));
        }
    }

    private void updateGrid(int index){
        root.getChildren().remove(merchGrid);

        merchGrid = new MerchantRoomGridPane();

        for(int i = 0; i < cards.size(); i++){
            Card c = cards.get(i);
            StackPane cardPane = new CardImage(c.getName(), c.getType(), ""+ c.getEnergy(), c.getDescription());
            int price = (int) (Math.random() * 10 );
            GridPane product = new CardProduct(cardPane, "" + price , i);
            merchGrid.add(product, i, 0);
        }

        root.getChildren().add(merchGrid);
    }


}

