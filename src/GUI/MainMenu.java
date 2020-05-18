
package GUI;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import Controller.Fight.FightController;
import Controller.GameController;
import Controller.MenuController;
import Model.Card;
import Model.Room.EnemyRoom;
import Model.Room.MerchantRoom;
import Model.Room.Room;
import Model.Room.RoomFactory;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;

public class MainMenu extends Application {
	
    private GameMenu gameMenu;
    private GameScene roomScene;
    private MapScene mapScene;
    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double x = screenBounds.getWidth(); //gets the screen width
    double y = screenBounds.getHeight(); //gets the screen height
    AudioClip menuSound = new AudioClip(new File("resources/sounds/menuMusic.wav").toURI().toString());
    private Pane root ;
    BackgroundImage fightRoomBG;
    BackgroundImage mapBG;
    BackgroundImage merchantBG;
    String chosenCharacter;
    MenuController menuController = new MenuController();

    @Override
    public void start(Stage primaryStage) throws Exception {  
        root = new Pane();
        root.setPrefSize(x, y);

        //play the music
        //menuSound.play(0.05);
        
        //get the background image.
        InputStream is = Files.newInputStream(Paths.get("resources/images/background.jpg")); //get the image of background
        Image img = new Image(is);
        is.close(); //this is to give access other programs to that image as well.
        BackgroundImage menuBG= new BackgroundImage(img,
              BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
              new BackgroundSize(1.0, 1.0, true, true, false, false));



        InputStream is3 = Files.newInputStream(Paths.get("resources/images/mapBG.png")); //get the image of background
        Image img3 = new Image(is3);
        is3.close(); //this is to give access other programs to that image as well.
        mapBG= new BackgroundImage(img3,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));


      //then you set to your node
      root.setBackground(new Background(menuBG));


        gameMenu = new GameMenu();

        //when program starts, menu is visible.
        //gameMenu.setVisible(true);
        //roomScene.setVisible(true);
        
        root.getChildren().addAll(gameMenu); //menu and background image are accessible by scene.
        Scene scene = new Scene(root);
        
        scene.setOnKeyPressed(event -> {
      	  
      	  	//when user press ESC, if menu isn't shown, show it; if it is already shown, hide it.
            if (event.getCode() == KeyCode.ESCAPE) {
                if (!gameMenu.isVisible()) {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    gameMenu.setVisible(true);
                    ft.play();
                    
                }
                else {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(evt -> gameMenu.setVisible(false));
                    ft.play();
                }
            }
        });

        primaryStage.setScene(scene); //set the scene of the stage.
        primaryStage.show(); //show the primary stage.
    }

    public class GameMenu extends Parent {
        public GameMenu() {

            Pane mainPane = new Pane();
            mainPane.setPrefSize(x,y);
            InputStream as;
            try {
                as = Files.newInputStream(Paths.get("resources/images/background.jpg"));
                Image img1 = new Image(as);
                as.close(); //this is to give access other programs to that image as well.
                BackgroundImage myBI= new BackgroundImage(img1,
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        new BackgroundSize(1.0, 1.0, true, true, false, false));
                //then you set to your node
                mainPane.setBackground(new Background(myBI));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } //get the image of background
      	  Rectangle bg = new Rectangle(x,y);
           bg.setOpacity(0.1);
      	  
      	   //All menus are in form of vertical box.
            VBox mainMenu = new VBox(10);
            VBox settingsMenu = new VBox(10);
            VBox soundMenu = new VBox(15);
            VBox statisticsMenu = new VBox(10);
            VBox statisticsInfo = new VBox(10);
            HBox characterSelection = new HBox(20);
            
            GridPane cardCollection = new GridPane();
            cardCollection.setHgap(10);
            cardCollection.setVgap(10);
            cardCollection.setPadding(new Insets(0, 10, 0, 10));
            
            HBox compendiumMenu = new HBox(10);
            
            /*ScrollPane sp = new ScrollPane(statisticsInfo);
            sp.setHbarPolicy(ScrollBarPolicy.NEVER);
            sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
            sp.setFitToWidth(true);*/
            
            compendiumMenu.setTranslateX(150);
            compendiumMenu.setTranslateY(300);
            
            cardCollection.setTranslateX(-75);
            cardCollection.setTranslateY(-250);
            
            //adjusting position of mainMenu on screen.
            mainMenu.setTranslateX(150);
            mainMenu.setTranslateY(300);

            //adjusting position of settings menu.
            settingsMenu.setTranslateX(150);
            settingsMenu.setTranslateY(300);
            
            //adjusting position of settings menu.
            soundMenu.setTranslateX(150);
            soundMenu.setTranslateY(300);
            
            //adjusting position of statistics menu.
            statisticsMenu.setTranslateX(150);
            statisticsMenu.setTranslateY(300);
            
            statisticsInfo.setTranslateX(530);
            statisticsInfo.setTranslateY(150);
            
            characterSelection.setTranslateX(500);
            characterSelection.setTranslateY(500);

            final int offset = 400;
            settingsMenu.setTranslateX(offset);
            soundMenu.setTranslateX(offset);
            statisticsMenu.setTranslateX(offset);
            statisticsInfo.setTranslateX(offset);
            characterSelection.setTranslateX(offset);
            
            //Design of 'New Game' button in menu
            MenuButton btnNewGame = new MenuButton("New Game");
            btnNewGame.setOnMouseClicked(event -> {
            	
               mainPane.getChildren().add(characterSelection);
               
               TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), mainMenu); //how fast is main menu gone.
               tt.setToX(mainMenu.getTranslateX() - offset);

               TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), characterSelection); //how fast is settings menu come.
               tt1.setToX(mainMenu.getTranslateX() );

               //play both animation of screens.
               tt.play();
               tt1.play();
                             
               tt.setOnFinished(evt -> {
                   getChildren().remove(mainMenu);
               });
           });
            
            
            MenuButton btnStart = new MenuButton("Start");
            btnStart.setVisible(false);
            btnStart.setOnMouseClicked(event -> {

                GameController gameController = menuController.createNewGame(1,chosenCharacter);

                mapScene = new MapScene( gameController );

                //roomScene = new GameScene((FightController)gameController.createController(room), mapScene);
            	getChildren().remove(mainPane);
            	System.out.println("---------------------------REMOVING MAIN MENU---------------------");
            	//root.getChildren().add(roomScene);
            	getChildren().add(mapScene);
                //root.getChildren().add(merchant);

            });

            MenuButton btnCh1 = new MenuButton("Ironclad");
            btnCh1.setOnMouseClicked(event -> {
            	btnStart.setVisible(true);
            	
            	Text IroncladDesc = new Text("GAME CHARACTER");
         		IroncladDesc.setFill(Color.WHITE);
         		IroncladDesc.setFont(Font.font ("Verdana", 15));
         		IroncladDesc.setX(150);
       			IroncladDesc.setY(200);
       			getChildren().add(IroncladDesc);

       			chosenCharacter = "Ironclad";
               InputStream is;
					try {
						is = Files.newInputStream(Paths.get("resources/images/Ironclad.jpg"));
						Image img = new Image(is);
	               is.close(); //this is to give access other programs to that image as well.
	               BackgroundImage myBI= new BackgroundImage(img,
	                     BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
	                     new BackgroundSize(1.0, 1.0, true, true, false, false));
	             //then you set to your node
	               mainPane.setBackground(new Background(myBI));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //get the image of background
               
				
               
           });
            
          //Design of 'New Game' button in menu
            MenuButton btnCh2 = new MenuButton("Silent");
            btnCh2.setOnMouseClicked(event -> {
                btnStart.setVisible(true);
                chosenCharacter = "Silent";
            	 InputStream is;
 					try {
 						is = Files.newInputStream(Paths.get("resources/images/Silent.jpg"));
 						Image img = new Image(is);
 	               is.close(); //this is to give access other programs to that image as well.
 	               BackgroundImage myBI= new BackgroundImage(img,
 	                     BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
 	                     new BackgroundSize(1.0, 1.0, true, true, false, false));
 	             //then you set to your node
 	               mainPane.setBackground(new Background(myBI));
 					} catch (IOException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					} //get the image of background
               
           });
            
          //Design of 'New Game' button in menu
            MenuButton btnCh3 = new MenuButton("Defect");
            btnCh3.setOnMouseClicked(event -> {
            	btnStart.setVisible(true);
                chosenCharacter = "Defect";
            	InputStream is;
 					try {
 						is = Files.newInputStream(Paths.get("resources/images/defect.jpg"));
 						Image img = new Image(is);
 	               is.close(); //this is to give access other programs to that image as well.
 	               BackgroundImage myBI= new BackgroundImage(img,
 	                     BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
 	                     new BackgroundSize(1.0, 1.0, true, true, false, false));
 	             //then you set to your node
 	               mainPane.setBackground(new Background(myBI));
 					} catch (IOException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					} //get the image of background
               
           });
            
            MenuButton btnChSelectionReturn = new MenuButton("Return");
            btnChSelectionReturn.setOnMouseClicked(event -> {
            	btnStart.setVisible(false);
            	InputStream is;
					try {
						is = Files.newInputStream(Paths.get("resources/images/background.jpg"));
						Image img = new Image(is);
	               is.close(); //this is to give access other programs to that image as well.
	               BackgroundImage myBI= new BackgroundImage(img,
	                     BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
	                     new BackgroundSize(1.0, 1.0, true, true, false, false));
	             //then you set to your node
	               mainPane.setBackground(new Background(myBI));
					} catch (IOException e) {
						e.printStackTrace();
					} 
					
               getChildren().add(mainMenu);
         
               TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), characterSelection); //how fast is main menu gone.
               tt.setToX(characterSelection.getTranslateX() + offset);

               TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), mainMenu); //how fast is settings menu come.
               tt1.setToX(mainMenu.getTranslateX()+offset);

               //play both animation of screens.
               tt.play();
               tt1.play();
                             
               tt.setOnFinished(evt -> {
                   getChildren().remove(characterSelection);
               });
           });
            
            
            
            //Design of 'Load Run' button in menu
            StackPane loadGamePane = new StackPane();
            MenuButton btnLoadGame = new MenuButton("Load Game");
            btnLoadGame.setOnMouseClicked(event -> {

                //create the list view that contains the file names.
                ArrayList<String> fileNames = menuController.getSavedGamesNames();
                Collections.reverse(fileNames);
                ListView listView = new ListView();
                for( String name: fileNames){
                    listView.getItems().add(name);
                }

                //put image in the background of the listview.
                InputStream is = null;
                getStylesheets().add(getClass().getResource("lisStyles.css").toExternalForm());
                try {
                    is = Files.newInputStream(Paths.get("resources/images/background.jpg"));
                    Image img = new Image(is);
                    is.close();
                    listView.setBackground(new Background(new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                listView.setMaxHeight(270);
                listView.setMaxWidth(170);

                //put the load game button
                Button button = new Button("Load Game");
                button.setOnAction(buttonEvent -> {
                    ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();
                    if(selectedIndices.get(0) != null) {
                        String filename = fileNames.get((Integer) selectedIndices.get(0));
                        System.out.println(filename);
                        GameController gameController = menuController.loadGame(filename);
                        MapScene mapScene = new MapScene(gameController);
                        root.getChildren().remove(gameMenu);
                        root.getChildren().add(mapScene);
                    }
                });
                button.setMaxWidth(130);
                button.setMaxHeight(30);

                loadGamePane.getChildren().add(button);
                loadGamePane.getChildren().add(listView);
                button.setTranslateX(1000);
                button.setTranslateX(170);

                getChildren().add(loadGamePane);
                loadGamePane.setTranslateX(610);
                loadGamePane.setTranslateY(200);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), mainMenu); //how fast is main menu gone.
                tt.setToX(mainMenu.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.4), loadGamePane); //how fast is settings menu come.
                tt1.setToX(510);

                //play both animation of screens.
                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(mainMenu);
                });

                bg.setOpacity(0.2);
            });

            MenuButton btnLoadGameReturn = new MenuButton("Return");
            btnLoadGameReturn.setTranslateY(220);
            btnLoadGameReturn.setTranslateX(-380);
            btnLoadGameReturn.setOnMouseClicked(event -> {
                bg.setOpacity(0.1);
                getChildren().add(mainMenu);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), loadGamePane);
                tt.setToX(300+offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), mainMenu);
                tt1.setToX(150);

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(loadGamePane);
                });
            });


            
            //Initilize cards for compendium
            
            ArrayList<Card> cards = menuController.getAllCards();
            CardImage card;
            int horizontal = 5;
            for(int i = 0 ; i < 10 ; i++)
            {
            	card = new CardImage(cards.get(i));
            	cardCollection.add(card, i % horizontal,i / horizontal);
            }
            
            
            
            //Design of 'Compendium' button in menu
            MenuButton btnCompendium = new MenuButton("Compendium");
            btnCompendium.setOnMouseClicked(event -> {
            	bg.setOpacity(0.5);
               getChildren().add(compendiumMenu);
               
               TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), mainMenu); //how fast is main menu gone.
               tt.setToX(mainMenu.getTranslateX() - offset);

               TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), compendiumMenu); //how fast is settings menu come.
               tt1.setToX(mainMenu.getTranslateX() );

               //play both animation of screens.
               tt.play();
               tt1.play();
                             
               tt.setOnFinished(evt -> {
                   getChildren().remove(mainMenu);
               });
           });
            
            MenuButton btnCompendiumReturn = new MenuButton("Return");
            btnCompendiumReturn.setTranslateY(100);
            btnCompendiumReturn.setTranslateX(-80);
            btnCompendiumReturn.setOnMouseClicked(event -> {
            	bg.setOpacity(0.1);
               getChildren().add(mainMenu);

             

               TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), compendiumMenu);
               tt.setToX(compendiumMenu.getTranslateX() + offset);
               
               TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), mainMenu);
               tt1.setToX(compendiumMenu.getTranslateX());

               tt.play();
               tt1.play();
               
               tt.setOnFinished(evt -> {
                   getChildren().remove(compendiumMenu);
            
                   //getChildren().remove(sp);
               });
           });
            
            
            
            //STATISTICS INFO TEXTS.
            Text stats = new Text();
            stats.setText("Overall Statistics");
            stats.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 30));
            stats.setFill(Color.YELLOW);
            
            Text stat1 = new Text("Total Play Time: ");
            stat1.setFill(Color.WHITE);
            Text stat2 = new Text("Total Victorie: ");
            stat2.setFill(Color.WHITE);
            Text stat3 = new Text("Total Deaths: ");
            stat3.setFill(Color.WHITE);
            Text stat4 = new Text("Total Floors Climbed: ");
            stat4.setFill(Color.WHITE);
            Text stat5 = new Text("Total Bosses Slain: ");
            stat5.setFill(Color.WHITE);
            Text stat6 = new Text("Total Enemies Slain: ");
            stat6.setFill(Color.WHITE);
            Text stat7 = new Text("Cards Discovered: ");
            stat7.setFill(Color.WHITE);
            Text stat8 = new Text("Cards Unlocked: ");
            stat8.setFill(Color.WHITE);
            Text stat9 = new Text("Relics Discovered: ");
            stat9.setFill(Color.WHITE);
            Text stat10 = new Text("Relics Unlocked: ");
            stat10.setFill(Color.WHITE);
            Text stat11 = new Text("Heighest Ascend: ");
            stat11.setFill(Color.WHITE);
            Text stat12 = new Text("Fastest Victory: ");
            stat12.setFill(Color.WHITE);
            
            
            //Design of 'Statistics' button in menu
            MenuButton btnStatistics = new MenuButton("Statistics");
            btnStatistics.setOnMouseClicked(event -> {
                getChildren().add(statisticsMenu);
                getChildren().add(statisticsInfo);
                //getChildren().add(sp);

                bg.setOpacity(0.75);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), mainMenu); //how fast is main menu gone.
                tt.setToX(mainMenu.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), statisticsMenu); //how fast is settings menu come.
                tt1.setToX(mainMenu.getTranslateX());

                TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.5), statisticsInfo); //how fast is settings menu come.
                tt2.setToX(mainMenu.getTranslateX() + 350);
                //play both animation of screens.
                tt.play();
                tt1.play();
                tt2.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(mainMenu);
                });
            });
           
            
            MenuButton btnStatisticsReturn = new MenuButton("Return");
            btnStatisticsReturn.setOnMouseClicked(event -> {
            	bg.setOpacity(0.1);
               getChildren().add(mainMenu);

               TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), statisticsMenu);
               tt.setToX(statisticsMenu.getTranslateX() + offset);

               TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.25), statisticsInfo);
               tt1.setToX(statisticsInfo.getTranslateX() + offset);
               
               TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.5), mainMenu);
               tt2.setToX(statisticsMenu.getTranslateX());

               tt.play();
               tt1.play();
               tt2.play();
               
               tt.setOnFinished(evt -> {
                   getChildren().remove(statisticsMenu);
                   getChildren().remove(statisticsInfo);
                   //getChildren().remove(sp);
               });
           });

            //Design of 'Settings' button in menu.It opens new menu screen called settingsMenu
            MenuButton btnSettings = new MenuButton("Settings");
            btnSettings.setOnMouseClicked(event -> {
                getChildren().add(settingsMenu);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), mainMenu); //how fast is main menu gone.
                tt.setToX(mainMenu.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), settingsMenu); //how fast is settings menu come.
                tt1.setToX(mainMenu.getTranslateX());

                //play both animation of screens.
                tt.play();
                tt1.play();

                
                tt.setOnFinished(evt -> {
                    getChildren().remove(mainMenu);
                });
            });
            
            //Design of 'Return' button in menu.Do the reverse of continue button.
            MenuButton btnReturn = new MenuButton("Return");
            btnReturn.setOnMouseClicked(event -> {
                getChildren().add(mainMenu);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), settingsMenu);
                tt.setToX(settingsMenu.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), mainMenu);
                tt1.setToX(settingsMenu.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(settingsMenu);
                });
            });

            MenuButton btnSound = new MenuButton("Sound");
            btnSound.setOnMouseClicked(event -> {
               getChildren().add(soundMenu);

               TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), settingsMenu); //how fast is settings menu gone.
               tt.setToX(settingsMenu.getTranslateX() - offset);

               TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), soundMenu); //how fast is settings menu come.
               tt1.setToX(settingsMenu.getTranslateX());

               //play both animation of screens.
               tt.play();
               tt1.play();
               
               tt.setOnFinished(evt -> {
                   getChildren().remove(settingsMenu);
               });
           });
            
            MenuButton btnSoundReturn = new MenuButton("Return");
            btnSoundReturn.setOnMouseClicked(event -> {
                getChildren().add(settingsMenu);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), soundMenu);
                tt.setToX(soundMenu.getTranslateX() + offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), settingsMenu);
                tt1.setToX(soundMenu.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt -> {
                    getChildren().remove(soundMenu);
                });
            });
            
            MenuButton btnSoundHigh = new MenuButton("High");
            btnSoundHigh.setOnMouseClicked(event -> {
               //MAX VOLUME
            	menuSound.stop();
            	menuSound.play(1);
           });
            
            MenuButton btnSoundMedium = new MenuButton("Medium");
            btnSoundMedium.setOnMouseClicked(event -> {
               //MEDIUM VOLUME
            	menuSound.stop();
            	menuSound.play(0.4);
           });
            
            MenuButton btnSoundLow = new MenuButton("Low");
            btnSoundLow.setOnMouseClicked(event -> {
              //LOW VOLUME
            	menuSound.stop();
            	menuSound.play(0.05);
           });
            
            MenuButton btnSoundStop = new MenuButton("Stop Sound");
            btnSoundStop.setOnMouseClicked(event -> {
              //LOW VOLUME
            	menuSound.stop();
           });
            
            MenuButton btnGraphics = new MenuButton("Graphics");
            btnGraphics.setOnMouseClicked(event -> {
               FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
               ft.setFromValue(1);
               ft.setToValue(0);
               ft.setOnFinished(evt -> setVisible(false));
               ft.play();
           });
            
            MenuButton btnPatchNotes = new MenuButton("Patch Notes");
            btnPatchNotes.setOnMouseClicked(event -> {
                FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
                ft.setFromValue(1);
                ft.setToValue(0);
                ft.setOnFinished(evt -> setVisible(false));
                ft.play();
            });

            
            MenuButton btnQuit = new MenuButton("Quit");
            btnQuit.setOnMouseClicked(event -> {
                System.exit(0);
            });

            mainMenu.getChildren().addAll(btnNewGame,btnLoadGame, btnCompendium, btnStatistics, btnSettings, btnPatchNotes, btnQuit);
            settingsMenu.getChildren().addAll(btnGraphics, btnSound, btnReturn);
            soundMenu.getChildren().addAll(btnSoundHigh,btnSoundMedium,btnSoundLow,btnSoundStop,btnSoundReturn);
            statisticsMenu.getChildren().addAll(btnStatisticsReturn);
            statisticsInfo.getChildren().addAll(stats,stat1,stat2,stat3,stat4,stat5,stat6,stat7,stat8,stat9,stat10,stat11,stat12);
            characterSelection.getChildren().addAll(btnChSelectionReturn,btnCh1,btnCh2,btnCh3,btnStart);
            compendiumMenu.getChildren().addAll(btnCompendiumReturn,cardCollection);
            loadGamePane.getChildren().addAll(btnLoadGameReturn);

            mainPane.getChildren().addAll(bg, mainMenu);
            getChildren().addAll(mainPane);
        }
    }

    public static class MenuButton extends StackPane {
        private Text text;

        public MenuButton(String name) {
            text = new Text(name);
            text.setFont(text.getFont().font(20));
            text.setFill(Color.WHITE);

            Rectangle bg = new Rectangle(200, 30);
            bg.setOpacity(0.6);
            bg.setFill(Color.BLACK);
            bg.setEffect(new GaussianBlur(3.5));

            setAlignment(Pos.CENTER_LEFT);
            setRotate(-0.5);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                bg.setTranslateX(10);
                text.setTranslateX(10);
                bg.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });

            setOnMouseExited(event -> {
                bg.setTranslateX(0);
                text.setTranslateX(0);
                bg.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
            });

            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());

            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
