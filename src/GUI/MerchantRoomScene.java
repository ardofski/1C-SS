package GUI;

import java.util.ArrayList;

import Controller.Fight.FightController;
import Controller.MerchantController;
import Controller.RoomController;
import Model.Card;
import Model.Potion;
import Model.PotionFactory;
import Model.Relics.Relic;
import Model.Room.Room;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

class MerchantRoomScene extends Parent {
    public static final int ROOM_BUTTON_SIZE = 50;

    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    final double width = screenBounds.getWidth(); //gets the screen width
    final double height = screenBounds.getHeight();
    Pane mainPane;
    Pane elementsPane;
    HUDPane hudPane;

    InputStream inputStream,is;
    Image img;
    ImageView imgView;

    GridPane cardGrid;
    GridPane relicGrid;
    GridPane potionGrid;

    ArrayList<Card> cards;
    ArrayList<Relic> relics;
    ArrayList<Potion> potions;

    ArrayList<Integer> cardPrices, relicPrices, potionPrices;

    RoomController controller;

    StackPane deleteBtn;
    StackPane returnButton;
    HBox cardPane;
    Text goldText;

    public MerchantRoomScene(RoomController controller, MapScene mapScene) {
        this.controller = controller;


        hudPane = new HUDPane(((MerchantController)controller).getCharacter());
        mainPane = new Pane();
        elementsPane = new Pane();
        mainPane.setPrefSize(width, height);
        elementsPane.setPrefSize(width, height);


        cards = ((MerchantController)controller).getCards();
        relics = ((MerchantController)controller).getRelics();
        potions = ((MerchantController)controller).getPotions();

        cardPrices =  ((MerchantController)controller).getCardPrices();
        relicPrices =  ((MerchantController)controller).getRelicPrices();
        potionPrices =  ((MerchantController)controller).getPotionPrices();

        cardGrid = new MerchantRoomGridPane();
        for(int i = 0; i < cards.size(); i++){
            Card c = cards.get(i);
            StackPane cardPane = new CardImage(c.getName(), c.getType(), ""+ c.getEnergy(), c.getDescription());
            int price = cardPrices.get(i);
            GridPane product = new CardProduct(cardPane, "" + price , i);
            cardGrid.add(product, i, 0);
        }

        relicGrid = new GridPane();
        relicGrid.setLayoutX(400);
        relicGrid.setLayoutY((380));
        relicGrid.setHgap(25);
        for(int i = 0; i < relics.size() ; i++){
            StackPane relicPane = new RelicImage(relics.get(i));
            int price = relicPrices.get(i);
            GridPane product = new RelicProduct(relicPane, "" + price , i);
            relicGrid.add(product, i, 0);
        }

        System.out.println(potions);

        potionGrid = new GridPane();
        potionGrid.setLayoutX(400);
        potionGrid.setLayoutY(520);
        potionGrid.setHgap(40);
        for(int i = 0; i < 5; i++){
            StackPane potionPane = new PotionImage(potions.get(i));
            int price = potionPrices.get(i);
            GridPane product = new PotionProduct(potionPane, "" + price , i);
            potionGrid.add(product, i, 0);
        }

        deleteBtn = new deleteCardButton();
        returnButton = new ReturnButton();
        returnButton.setOnMouseClicked(event -> {
            //setEffect(drop);
            getChildren().remove(mainPane);
            getChildren().add(mapScene);
        });
        goldText = new Text("" +15+ " gold");
        goldText.setFill(Color.WHITE);
        goldText.setFont(Font.font ("Verdana", 12));
        goldText.setTranslateX(980);
        goldText.setTranslateY(650);

        setBackground();
        elementsPane.getChildren().addAll(cardGrid, relicGrid, potionGrid, deleteBtn, returnButton, hudPane, goldText);
        mainPane.getChildren().add(elementsPane);

        getChildren().add(mainPane);
    }
    private static class MerchantRoomGridPane extends GridPane{

        public MerchantRoomGridPane(){
            this.setLayoutX(350);
            this.setLayoutY((80));
            this.setHgap(10);
            this.setVgap(10);
        }
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
                    hudPane.updateTotalCards();
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

                System.out.println(relics);
                AlertPane alert = new AlertPane("relic", index, price);
                hudPane.updateRelics();
            });
            this.setHgap(5);
            Text goldT = new Text("    " +price+ " gold");
            goldT.setFill(Color.WHITE);
            goldT.setFont(Font.font ("Verdana", 12));
            this.add(goldT,0,1);

        }
    }

    private class PotionProduct extends GridPane{
        int index;
        int price;

        public PotionProduct(StackPane potionPane, String gold, int index){
            this.price = Integer.parseInt(gold);
            this.index = index;

            this.add(potionPane,0,0);

            this.setOnMouseClicked(event -> {
                AlertPane alert = new AlertPane("potion", index, price);
                hudPane.updatePotions();
            });
            this.setHgap(5);
            Text goldT = new Text("    " +price+ " gold");
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
                            //TODO  potion merchant room
                            //canBuy = true; break;
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
                    updateGrid(productType);
                    hudPane.updateGold();
                }
            });
        }
    }

    private class deleteCardButton extends StackPane{
        InputStream is;
        Image img;
        Text text;
        public deleteCardButton(){
            this.setLayoutX(910);
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
            setOnMousePressed(event -> {
                    setEffect(drop);
                    showDeleteCardPane();
            });
            setOnMouseReleased(event -> setEffect(null));
        }
    }

    private class ReturnButton extends StackPane{
        InputStream is;
        Image img;
        Text text;
        public ReturnButton(){
            this.setLayoutX(1115);
            this.setLayoutY(480);

            text = new Text("Return");
            text.setFont(text.getFont().font(20));
            text.setFill(Color.WHITE);
            text.setTranslateX(15);

            Rectangle bg = new Rectangle(100, 30);
            bg.setOpacity(0.6);
            //bg.setFill(Color.BLACK);
            bg.setStyle("-fx-background-color: rgb(94, 35, 23);");
            bg.setEffect(new GaussianBlur(3.5));

            setAlignment(Pos.CENTER_LEFT);
            setRotate(-0.5);
            getChildren().addAll(bg, text);

            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());

            setOnMouseEntered(event -> {
                //bg.setTranslateX(10);
                bg.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
                setEffect(drop);
            });

            setOnMouseExited(event -> {
                bg.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
                setEffect(null);
            });


        }
    }

    private void updateGrid(String type){
        if( type.equals("card")){
            elementsPane.getChildren().remove(cardGrid);

            cardGrid = new MerchantRoomGridPane();
            System.out.println("SIZEE -------> " + cards.size());
            for(int i = 0; i < cards.size(); i++){
                Card c = cards.get(i);
                StackPane cardPane = new CardImage(c.getName(), c.getType(), ""+ c.getEnergy(), c.getDescription());
                int price = cardPrices.get(i);
                GridPane product = new CardProduct(cardPane, "" + price , i);
                cardGrid.add(product, i, 0);
            }

            elementsPane.getChildren().add(cardGrid);
        }
        else if(type.equals("relic")){

            elementsPane.getChildren().remove(relicGrid);

            relicGrid = new GridPane();
            relicGrid.setLayoutX(395);
            relicGrid.setLayoutY((380));
            relicGrid.setHgap(16);
            System.out.println("SIZEE ============" + relics.size());
            System.out.println(relics);
            for(int i = 0; i < relics.size(); i++){
                System.out.println(relics.get(i).getName());
                StackPane relicPane = new RelicImage(relics.get(i));
                int price = relicPrices.get(i);
                GridPane product = new CardProduct(relicPane, "" + price , i);
                relicGrid.add(product, i, 0);
            }
            elementsPane.getChildren().add(relicGrid);
        }
        else if(type.equals("potion")){
            elementsPane.getChildren().remove(potionGrid);

            potionGrid = new GridPane();
            potionGrid.setLayoutX(400);
            potionGrid.setLayoutY(520);
            potionGrid.setHgap(40);
            for(int i = 0; i < 5; i++){
                StackPane potionPane = new PotionImage(potions.get(i));
                int price = potionPrices.get(i);
                GridPane product = new PotionProduct(potionPane, "" + price , i);
                potionGrid.add(product, i, 0);
            }

            elementsPane.getChildren().add(potionGrid);

        }
    }

    private void setBackground(){
        try {
            inputStream = Files.newInputStream(Paths.get("resources/images/merchantBG.jpg"));
            img = new Image(inputStream);
            inputStream.close();
            imgView = new ImageView(img);
        }catch (IOException e) {
            e.printStackTrace();
        }

        BackgroundImage mapBG = new BackgroundImage(img,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1, 1, true, true, false, false));
        Background bg2 = new Background(mapBG);
        mainPane.setBackground(bg2);
    }

    private void showDeleteCardPane(){
        ScrollPane scroll = new ScrollPane();
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.fitToHeightProperty().set(false);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(false);
        scroll.setMinHeight(550);
        scroll.setMaxHeight(550);
        getStylesheets().add(getClass().getResource("lisStyles.css").toExternalForm());
        scroll.setStyle("-fx-background-color:transparent;");
        scroll.setTranslateX(-75);
        scroll.setTranslateY(-300);

        GridPane cardCollection = new GridPane();
        cardCollection.setHgap(10);
        cardCollection.setVgap(10);
        cardCollection.setPadding(new Insets(0, 10, 0, 10));

        cardPane = new HBox(10);

        cardPane.setTranslateX(400);
        cardPane.setTranslateY(300);

        cardCollection.setTranslateY(50);

        ArrayList<Card> cards = ((MerchantController)controller).getAllCards();
        CardImage card;
        int horizontal = 5;
        for(int i = 0 ; i < cards.size() ; i++)
        {
            card = new CardImage(cards.get(i).getName(),cards.get(i).getType()
                    ,Integer.toString(cards.get(i).getEnergy()),cards.get(i).getDescription());
            CardPane cp = new CardPane(card, cards.get(i).getName());
            cardCollection.add(cp, i % horizontal,i / horizontal);
        }
        scroll.setContent(cardCollection);
        cardPane.getChildren().addAll(scroll);

        mainPane.getChildren().add(cardPane);

        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.2), elementsPane); //how fast is main menu gone.
        tt.setToX(elementsPane.getTranslateX() );

        TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), cardPane); //how fast is settings menu come.
        tt1.setToX(cardPane.getTranslateX() );

        tt1.play();
        mainPane.getChildren().remove(elementsPane);
    }

    private class CardPane extends StackPane{

        public CardPane(CardImage cardImage, String cardName){
            this.getChildren().add(cardImage);

            this.setOnMouseClicked(event -> {
                ((MerchantController)controller).deleteCard(cardName);
                mainPane.getChildren().remove(cardPane);
                mainPane.getChildren().add(elementsPane);
                hudPane.updateTotalCards();
            });
        }
    }

}

