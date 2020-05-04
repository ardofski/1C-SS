package GUI;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import Controller.GameController;
import Model.Map;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Screen;

class MapScene extends Parent {

    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double x = screenBounds.getWidth(); //gets the screen width
    double y = screenBounds.getHeight(); //gets the screen height
    int mapLength;
    Pane pane;
    GameController gameController;

    public MapScene(GameController gameController)
    {
        this.gameController = gameController;
        mapLength = Map.LENGTH;
        boolean[][][][] arr = gameController.getPaths();


        GridPane mapButtons = new GridPane();

        pane = new Pane();


        VBox vbox = new VBox();


        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(x,y);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.fitToHeightProperty().set(false);
        scroll.setFitToWidth(false);
        scroll.setFitToHeight(false);


        mapButtons.setHgap(50);
        mapButtons.setVgap(90);

        InputStream is;
        Image img;
        BackgroundImage mapBG = null;


        try {
            is = Files.newInputStream(Paths.get("resources/images/mapBG.png"));
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
            mapBG = new BackgroundImage(img,
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    new BackgroundSize(1.0, 1.0, true, true, false, false));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Background bg = new Background(mapBG);
        pane.setBackground(bg);


        pane.setPrefSize(x-5, mapLength *400);

        ImageView[][] mapArray = new ImageView[mapLength][mapLength];
        String[][] types = new String[mapLength][mapLength];


        mapButtons.setTranslateX(550-80*(mapLength -1) );
        mapButtons.setTranslateY(mapLength *100);


        for (int i = 0; i < mapLength; i++) {
            for (int j = 0; j < mapLength; j++){
                types[i][j] = "Monster";
            }
        }


        ImageView mapButtonIcon = null;


        for (int i = 0; i < mapLength; i++) {
            for (int j = 0; j < mapLength; j++)
                try {
                    //this is to give access other programs to that image as well.
                    if( gameController.getLocations()[i][j] != null ){
                        is = Files.newInputStream(Paths.get("resources/images/mapIcon"+types[i][j]+".png"));
                    }
                    else{
                        is = Files.newInputStream(Paths.get("resources/images/"+"emptyRoom.png"));
                    }
                    img = new Image(is);
                    is.close(); //this is to give access other programs to that image as well.

                    mapButtonIcon = new ImageView(img);
                    mapButtonIcon.setFitWidth(50);
                    mapButtonIcon.setFitHeight(50);
                    mapArray[i][j] = mapButtonIcon;
                } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int rowNum = 2* mapLength -1 ;
        int columnNum = 2* mapLength -1 ;
        int firstX = (int) (rowNum/2);
        int firstY = (columnNum-1);


        for(int i = 0; i < mapLength; i++)
        {
            for (int j = 0; j < mapLength; j++)
            {
                System.out.println("i: "+i);
                mapButtons.add(mapArray[j][i],firstX+j ,firstY-j);
            }
            firstX--;
            firstY--;
        }





        pane.getChildren().addAll(mapButtons);

        for(int i1 = 0; i1 < mapLength; i1++ ){
            for(int i2 = 0; i2 < mapLength; i2++ ){
                for(int i3 = 0; i3 < mapLength; i3++ ){
                    for(int i4 = 0; i4 < mapLength; i4++ ){
                        if( arr[i1][i2][i3][i4] == true)
                        {
                            int[] pair1 = convertIndex(i1,i2);
                            int[] pair2 = convertIndex(i3,i4);

                            int x1 = pair1[0];
                            int x2 = pair2[0];
                            int y1 = pair1[1];
                            int y2 = pair2[1];
                            drawPath(x1,y1,x2,y2);

                        }

                    }
                }

            }
        }
        //Bounds boundsInScreen = mapButtons[0][0].localToScreen(mapButtons[0][0].getBoundsInLocal());

        scroll.setContent(pane);
        getChildren().addAll(scroll);

    }

    private int[] convertIndex (int i , int j)
    {
        int rowNum = 2* mapLength -1 ;
        int columnNum = 2* mapLength -1 ;
        int firstX = (int) (rowNum/2);
        int firstY = (columnNum-1);
        System.out.println("i :"+i+"   j:"+j);
        for( ;   j > 0 ;  j--)
        {
            firstX--;
            firstY--;
        }

        for(; i > 0 ; i --)
        {
            firstY--;
            firstX++;
        }
        int[] arr = {firstX,firstY};
        System.out.println("x :"+firstX+"   y:"+firstY);
        return arr;
    }


    public void drawPath(int x1 , int y1, int x2, int y2)
    {
        int length = ((x2-x1)*100) ;
        int height = ((y2-y1)*140) ;

        int startX = (x1*100 + (550-80*(mapLength -1)) +25 ) ;
        int startY = (y1*140 + mapLength *100 + 25 );

        System.out.println("StartX: "+startX+"StartY: "+startY+"EndX: "+startX+length+"EndY: "+startY+height);

        Line line = new Line(startX,startY,startX+length,startY+height);
        line.getStrokeDashArray().addAll(15d, 10d);
        pane.getChildren().addAll(line);
    }


}

