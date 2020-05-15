package GUI;

import Controller.Fight.FightController;
import Model.Reward;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LootPane extends StackPane {
    Pane lootPane,cardPane;
    InputStream is;
    InputStream inputStream;
    Image img;
    ImageView buttonImg,lootBG;
    //ImageView card1,card2,card3;
    CardImage card1,card2,card3;
    StackPane[] buttons;
    StackPane lootButton ;
    //Text[] texts;
    String[] lootDescs;
    Text text,cardLootText,lootText;
    VBox loots;
    VBox lootsCard;
    HBox cardContainer;
    MainMenu.MenuButton skipButton;
    Background bg;
    public LootPane(FightController fightController)
    {
        lootText = new Text("LOOTS!");
        lootText.setFill(Color.WHITE);
        lootText.setFont(Font.font("COMIC SANS MS", 45));
        lootText.setX(175);


        cardLootText = new Text("Choose a Card");
        cardLootText.setFill(Color.WHITE);
        cardLootText.setFont(Font.font("COMIC SANS MS", 45));
        cardLootText.setX(170);

        cardPane = new Pane();
        lootPane = new Pane();

        /*try {
            inputStream = Files.newInputStream(Paths.get("resources/images/lootBG.png"));
            img = new Image(inputStream);
            inputStream.close();
            lootBG = new ImageView(img);
        }catch (IOException e) {
            e.printStackTrace();
        }
        BackgroundImage mapBG = new BackgroundImage(img,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1, 1, true, true, false, false));
        Background bg2 = new Background(mapBG);
        lootPane.setBackground(bg2);*/

        lootPane.setPrefSize(500, 800);
        cardPane.setPrefSize(500,800);

        loots = new VBox(10);
        loots.setTranslateY(60);

        Reward reward = fightController.getRewards();
        int rewardSize = 0;
        lootDescs = new String[5];
       if(reward.getGold() > 0)
       {
           lootDescs[rewardSize] = reward.getGold()+" Gold";
           rewardSize++;
       }
       if(reward.getRelic() != null)
       {
           lootDescs[rewardSize] = reward.getRelic().getName()+" Relic";
           rewardSize++;
       }
       if(reward.getPot() != null )
       {
           lootDescs[rewardSize] = reward.getPot().getName();
           rewardSize++;
       }
       lootDescs[rewardSize] = "Add a card to your deck";
       rewardSize++;

        int finalRewardSize = rewardSize;


        buttons = new StackPane[rewardSize];

        skipButton = new MainMenu.MenuButton("Return Loots");
        skipButton.setTranslateX(150);
        skipButton.setTranslateY(350);
        skipButton.setOnMouseClicked(event -> {
            getChildren().remove(cardPane);
            getChildren().add(lootPane);
        });


        card1 = new CardImage("Strike","Attack","1","desc");
        card1.setOnMouseClicked(event -> {
            loots.getChildren().remove(buttons[finalRewardSize -1]);
            getChildren().remove(cardPane);
            getChildren().add(lootPane);
        });

        card2 = new CardImage("Strike","Attack","1","desc");
        card2.setOnMouseClicked(event -> {
            loots.getChildren().remove(buttons[finalRewardSize -1]);
            getChildren().remove(cardPane);
            getChildren().add(lootPane);
        });

        card3 = new CardImage("Strike","Attack","1","desc");
        card3.setOnMouseClicked(event -> {
            loots.getChildren().remove(buttons[finalRewardSize -1]);
            getChildren().remove(cardPane);
            getChildren().add(lootPane);
        });

        cardContainer = new HBox(30);
        cardContainer.getChildren().addAll(card1,card2,card3);
        cardContainer.setTranslateY(80);

        cardPane.getChildren().addAll(cardLootText,cardContainer,skipButton);

        System.out.println("FINAL REWARD SIZE IS: "+finalRewardSize);
        for (int i = 0; i < rewardSize; i++)
        {
            lootButton = new StackPane();
            lootButton.setId(Integer.toString(i+1));
            lootButton.setPrefWidth(400);
            lootButton.setPrefHeight(55);
            lootButton.setTranslateX(40);

            text = new Text(lootDescs[i]);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("COMIC SANS MS", 15));
            text.setX(50);
            text.setY(15);


            try {
                is = Files.newInputStream(Paths.get("resources/images/lootImageRelic.jpg"));
                img = new Image(is);
                is.close(); //this is to give access other programs to that image as well.
                buttonImg = new ImageView(img);
                buttonImg.setFitWidth(400);
                buttonImg.setFitHeight(55);
            } catch (IOException e) {
                e.printStackTrace();
            }

            lootButton.getChildren().addAll(buttonImg, text);
            buttons[i] = lootButton;
            loots.getChildren().addAll(buttons[i]);

            lootButton.setOnMouseClicked(event -> {
                System.out.println("YOU CLICKED BUTTON WITH ID: "+((Node)event.getSource()).getId() );
                //System.out.println("loot button clicked.");

                if( ((Node)event.getSource()).getId().equals(""+finalRewardSize) )
                {
                    getChildren().remove(lootPane);
                    getChildren().add(cardPane);
                }
                else {

                    Integer k = Integer.valueOf(((Node) event.getSource()).getId());
                    String check = lootDescs[k-1];
                    if(check.contains("Gold"))
                    {
                        Boolean b = fightController.takeGoldReward();
                        System.out.println("GOLD REWARD TAKEN. = "+b);
                    }
                    if(check.contains("Potion"))
                    {
                        //TODO takePotReward
                    }
                    if(check.contains("Relic"))
                    {
                        //TODO fightController.takeRelicReward();
                    }
                    System.out.println("LOOT OPTION REMOVED");
                    loots.getChildren().remove((Node)event.getSource());
                }
            });


        }


        lootPane.getChildren().addAll(lootText,loots);
        getChildren().addAll(lootPane);
    }

}
