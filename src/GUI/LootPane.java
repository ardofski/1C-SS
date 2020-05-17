package GUI;

import Controller.Fight.FightController;
import Model.Card;
import Model.Reward;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LootPane extends StackPane {
    Pane lootPane,cardPane;
    InputStream is;
    Image img;
    ImageView buttonImg, buttonImg2;
    CardImage card1;
    StackPane[] buttons;
    StackPane lootTextPane;
    StackPane cardLootPane;
    StackPane lootButton ;
    String[] lootDescs;
    Text text,cardLootText,lootText;
    VBox loots;
    HBox cardContainer;
    MainMenu.MenuButton skipButton;
    public LootPane(FightController fightController, HUDPane hudPane)
    {
        lootText = new Text("Loots");
        lootText.setFill(Color.WHITE);
        lootText.setFont(Font.font("COMIC SANS MS", 25));
        lootText.setX(175);


        cardLootText = new Text("Choose a Card");
        cardLootText.setFill(Color.WHITE);
        cardLootText.setFont(Font.font("COMIC SANS MS", 25));
        cardLootText.setX(170);

        cardPane = new Pane();
        lootPane = new Pane();
        lootTextPane = new StackPane();
        cardLootPane = new StackPane();


        try {
            is = Files.newInputStream(Paths.get("resources/images/brand.png"));
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
            buttonImg = new ImageView(img);
            buttonImg2  = new ImageView(img);
            buttonImg.setFitWidth(500);
            buttonImg.setFitHeight(95);
            buttonImg2.setFitWidth(500);
            buttonImg2.setFitHeight(95);
        } catch (IOException e) {
            e.printStackTrace();
        }

        lootTextPane.getChildren().addAll(buttonImg, lootText);
        cardLootPane.getChildren().addAll(buttonImg2, cardLootText);
        lootText.setTranslateY(-10);
        cardLootText.setTranslateY(-10);


        lootPane.setPrefSize(500, 800);
        cardPane.setPrefSize(500,800);

        loots = new VBox(10);
        loots.setTranslateY(100);

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

        ArrayList<Card> cards = reward.getCards();

        cardContainer = new HBox(30);
        cardContainer.setTranslateY(80);

        for (int i = 0 ; i < reward.getCards().size() ; i++)
        {
            card1 = new CardImage(cards.get(i).getName(),cards.get(i).getType()
                    ,Integer.toString(cards.get(i).getEnergy()),cards.get(i).getDescription());

            card1.setId(Integer.toString(i));

            card1.setOnMouseClicked(event -> {
                Integer k = Integer.valueOf(((Node) event.getSource()).getId());
                fightController.takeCardReward(k);
                loots.getChildren().remove(buttons[finalRewardSize -1]);
                hudPane.updateTotalCards();
                getChildren().remove(cardPane);
                getChildren().add(lootPane);
            });

            cardContainer.getChildren().addAll(card1);
        }

        cardPane.getChildren().addAll(cardLootPane,cardContainer,skipButton);
        cardLootPane.setTranslateY(-10);

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
                        hudPane.updateGold();
                        System.out.println("GOLD REWARD TAKEN. = "+b);
                    }
                    if(check.contains("Potion"))
                    {
                        fightController.takePotionReward();
                        hudPane.updatePotions();
                        //TODO takePotReward
                    }
                    if(check.contains("Relic"))
                    {
                        fightController.takeRelicReward();
                        hudPane.updateRelics();
                    }
                    System.out.println("LOOT OPTION REMOVED");
                    loots.getChildren().remove((Node)event.getSource());
                }
            });


        }


        lootPane.getChildren().addAll(lootTextPane,loots);
        getChildren().addAll(lootPane);
    }

}
