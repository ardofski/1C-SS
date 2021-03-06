package GUI;

import Controller.RestSiteController;
import Model.Cards.Card;
import Model.Cards.CardFactory;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The type Rest scene.
 */
public class RestScene extends Parent {
    /**
     * The Input stream.
     */
    InputStream inputStream;
    /**
     * The Hud pane.
     */
    HUDPane hudPane;
    /**
     * The Img.
     */
    Image img;
    /**
     * The Img view.
     */
    ImageView imgView;
    /**
     * The Main pane.
     */
    StackPane mainPane, /**
     * The Upgrade pane.
     */
    upgradePane;
    /**
     * The Image container.
     */
    HBox imageContainer;
    /**
     * The Controller.
     */
    RestSiteController controller;
    /**
     * The Screen bounds.
     */
    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    /**
     * The Width.
     */
    final double width = screenBounds.getWidth(); //gets the screen width
    /**
     * The Height.
     */
    final double height = screenBounds.getHeight();

    /**
     * Instantiates a new Rest scene.
     *
     * @param controller  the controller
     * @param hudPane     the hud pane
     * @param floorNumber the floor number
     */
    public RestScene(RestSiteController controller, HUDPane hudPane, int floorNumber){
        mainPane = new StackPane();
        this.hudPane = hudPane;
        hudPane.enableFloor(floorNumber);
        mainPane.setPrefSize(width, height);

        this.controller = controller;

        ImageView rest = createImage("resources/images/restIcon.png");
        ImageView smith = createImage("resources/images/smithIcon.jpg");
        rest.setFitHeight(180);
        rest.setFitWidth(180);

        smith.setFitWidth(180);
        smith.setFitHeight(180);

        rest.setOnMouseClicked(event -> {
            controller.rest();
            getChildren().remove(mainPane);
        });

        smith.setOnMouseClicked(event -> {
            ArrayList<Card> cards = controller.getCharacter().getDeck().getCards();
            CardImage card;
            int horizontal = 5;
            GridPane cardCollection = new GridPane();

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.fitToHeightProperty().set(false);
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(false);
            scrollPane.setMinHeight(550);
            scrollPane.setMaxHeight(550);
            scrollPane.setTranslateX(200);
            scrollPane.setTranslateY(120);
            getStylesheets().add(getClass().getResource("lisStyles.css").toExternalForm());
            scrollPane.setStyle("-fx-background-color:transparent;");
            //scrollPane.setTranslateX(-75);
            //scrollPane.setTranslateY(-300);

            cardCollection.setHgap(10);
            cardCollection.setVgap(10);
            cardCollection.setPadding(new Insets(0, 10, 0, 10));
            for(int i = 0 ; i < cards.size() ; i++)
            {
                    card = new CardImage(cards.get(i));
                    card.setId(cards.get(i).getName());

                    card.setOnMouseClicked(event2 -> {
                        upgradePane = new StackPane();
                        Card add = CardFactory.getCard((((Node)event2.getSource()).getId()),true);
                        add.upgrade();
                        CardImage addImage = new CardImage(add);
                        upgradePane.getChildren().add(addImage);

                        addImage.setTranslateX(580);
                        addImage.setTranslateY(230);

                        GameScene.MenuButton returnB = new GameScene.MenuButton("Return");
                        returnB.setOnMouseClicked(event3 -> {
                            getChildren().remove(upgradePane);
                            getChildren().add(scrollPane);
                        });
                        GameScene.MenuButton upgradeB = new GameScene.MenuButton("Upgrade");
                        upgradeB.setOnMouseClicked(event3 -> {
                           controller.upgradeCard(add.getName());
                           getChildren().removeAll(mainPane, upgradePane);
                        });
                        upgradePane.getChildren().addAll(returnB, upgradeB);
                        returnB.setTranslateX(1000);
                        returnB.setTranslateY(500);

                        upgradeB.setTranslateX(100);
                        upgradeB.setTranslateY(500);


                        getChildren().remove(scrollPane);

                        getChildren().add(upgradePane);
                    });

                    cardCollection.add(card, i % horizontal, i / horizontal);
            }
            mainPane.getChildren().remove(imageContainer);

            BackgroundImage img = createBG("resources/images/eventRoomBackground.jpg");
            Background bg = new Background(img);
            mainPane.setBackground(bg);
            scrollPane.setContent(cardCollection);
            getChildren().add(scrollPane);
            //cardCollection.setTranslateX(180);
            //cardCollection.setTranslateY(120);


        });

        BackgroundImage mapBG = createBG("resources/images/rest"+controller.getCharacter().getName()+".jpg");
        Background bg2 = new Background(mapBG);
        mainPane.setBackground(bg2);
        imageContainer = new HBox(40);
        imageContainer.getChildren().addAll(smith, rest);
        mainPane.getChildren().addAll(hudPane, imageContainer);


        imageContainer.setTranslateX(465);
        imageContainer.setTranslateY(150);
        /*smith.setTranslateX(20);
        smith.setTranslateY(40);

        rest.setTranslateX(50);
        rest.setTranslateY(70);
        */
        getChildren().add(mainPane);
    }

    /**
     * Create ımage ımage view.
     *
     * @param path the path
     * @return the ımage view
     */
    public ImageView createImage(String path){
        ImageView imgV;
        try {
            inputStream = Files.newInputStream(Paths.get(path));
            img = new Image(inputStream);
            inputStream.close(); //this is to give access other programs to that image as well.
            imgV = new ImageView(img);
            imgV.setFitWidth(40);
            imgV.setFitHeight(40);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return imgV;
    }

    /**
     * Create bg background ımage.
     *
     * @param path the path
     * @return the background ımage
     */
    public BackgroundImage createBG(String path){
        Image img = null;
        try {
            InputStream inputStream = Files.newInputStream(Paths.get(path));
            img = new Image(inputStream);
            inputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        BackgroundImage bg = new BackgroundImage(img,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1, 1, true, true, false, false));
        return bg;
    }
}
