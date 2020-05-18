package GUI;

import Controller.RestSiteController;
import Controller.TreasureController;
import Model.Potion;
import Model.Relics.Relic;
import Model.Reward;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TreasureScene extends Parent {

    InputStream inputStream;
    HUDPane hudPane;
    Image img;
    ImageView imgView;
    Pane mainPane;
    TreasureController controller;
    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    final double width = screenBounds.getWidth(); //gets the screen width
    final double height = screenBounds.getHeight();

    public TreasureScene(TreasureController controller, HUDPane hudPane, int floorNumber, MapScene mapScene){
        System.out.println("-------TREASURE " + controller.getRewards());
        mainPane = new Pane();
        this.hudPane = hudPane;
        hudPane.enableFloor(floorNumber);
        mainPane.setPrefSize(width, height);
        this.controller = controller;

        BackgroundImage img = createBG("resources/images/treasureBG.jpg");
        Background bg = new Background(img);
        mainPane.setBackground(bg);

        ReturnButton returnButton = new ReturnButton();
        returnButton.setOnMouseClicked(event -> {
            getChildren().remove(mainPane);
            getChildren().add(mapScene);
        });

        ImageView chest = createImage("resources/images/chest.png");
        ImageView chestOpen = createImage("resources/images/chestOpen.png");
        chest.setTranslateX(720);
        chest.setTranslateY(380);
        chestOpen.setTranslateX(740);
        chestOpen.setTranslateY(360);

        chest.setFitHeight(180);
        chest.setFitWidth(300);
        chestOpen.setFitWidth(300);
        chestOpen.setFitHeight(180);

        ImageView charImage = null;
        InputStream is;
        Image img2;
        try {
            is = Files.newInputStream(Paths.get("resources/images/character"+controller.getCharacter().getName()+".png"));
            img2 = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
            charImage = new ImageView(img2);
            charImage.setFitWidth(275);
            charImage.setFitHeight(200);
            charImage.setTranslateX(200);
            charImage.setTranslateY(340);
        } catch (IOException e) {
            e.printStackTrace();
        } //get the image

        mainPane.getChildren().addAll(chest, returnButton, charImage, hudPane);
        chest.setOnMouseClicked(event->{
            mainPane.getChildren().remove(chest);
            LootPane lootPane = new LootPane(controller, hudPane);
            lootPane.setTranslateX(400);
            lootPane.setTranslateY(120);
            mainPane.getChildren().addAll(chestOpen, lootPane);
        });

        getChildren().add(mainPane);
    }

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


    private class ReturnButton extends StackPane{
        InputStream is;
        Image img;
        Text text;
        public ReturnButton(){
            this.setTranslateX(1115);
            this.setTranslateY(500);
            this.setPrefSize(110, 40);

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
            getChildren().addAll(bg, text);

            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());

            this.setOnMouseEntered(event -> {
                //bg.setTranslateX(10);
                bg.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
                setEffect(drop);
            });

            this.setOnMouseExited(event -> {
                bg.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
                setEffect(null);
            });


        }
    }

}
