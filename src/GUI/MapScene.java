package GUI;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import Controller.*;
import Controller.Fight.FightController;
import Model.Map;
import Model.Room.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Screen;

/**
 * The type Map scene.
 */
class MapScene extends Parent {
    /**
     * The constant ROOM_BUTTON_SIZE.
     */
    public static final int ROOM_BUTTON_SIZE = 50;

    /**
     * The Screen bounds.
     */
    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    /**
     * The Screen x.
     */
    double SCREEN_X = screenBounds.getWidth(); //gets the screen width
    /**
     * The Screen y.
     */
    public double SCREEN_Y = screenBounds.getHeight(); //gets the screen height
    /**
     * The Map length.
     */
    int mapLength;
    /**
     * The Pane.
     */
    Pane pane;
    /**
     * The Game controller.
     */
    MapController gameController;
    /**
     * The Game pane.
     */
    StackPane gamePane;
    /**
     * The Map menu.
     */
    MapMenu mapMenu;
    /**
     * The Map scroll.
     */
    ScrollPane mapScroll;

    /**
     * Instantiates a new Map scene.
     *
     * @param gameController the game controller
     */
    public MapScene(MapController gameController)
    {
        gamePane = new StackPane();
        this.gameController = gameController;
        mapLength = Map.LENGTH;
        boolean[][][][] arr = gameController.getPaths();


        GameScene.MenuButton returnB = new GameScene.MenuButton("Return");
        returnB.setOnMouseClicked(event ->{
            getChildren().clear();
            MainMenu.GameMenu menuScene = new MainMenu().new GameMenu();
            getChildren().add(menuScene);
        });

        GridPane mapButtons = new GridPane();

        pane = new Pane();




        mapScroll = new ScrollPane();
        getStylesheets().add(getClass().getResource("scrollBarMap.css").toExternalForm());
        mapScroll.setStyle("-fx-background-color:black;");
        mapScroll.setPrefSize(SCREEN_X, SCREEN_Y);
        mapScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mapScroll.fitToHeightProperty().set(false);
        mapScroll.setFitToWidth(false);
        mapScroll.setFitToHeight(false);


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
        pane.setPrefSize(SCREEN_X -5, mapLength *400);

        ImageView[][] mapArray = new ImageView[mapLength][mapLength];
        MapRoomButton[][] roomButtons = new MapRoomButton[mapLength][mapLength];

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
            for (int j = 0; j < mapLength; j++){

                try {
                    //this is to give access other programs to that image as well.
                    /*
                    if( gameController.getLocations()[i][j] != null ){
                        is = Files.newInputStream(Paths.get("resources/images/mapIcon"+types[i][j]+".png"));
                    }
                    else{
                        is = Files.newInputStream(Paths.get("resources/images/"+"emptyRoom.png"));
                    }

                     */
                    //img = new Image(is);
                    //img = getRoomImage( gameController.getLocations()[i][j] );

                    //is.close(); //this is to give access other programs to that image as well.
/*
                    mapButtonIcon = new ImageView(img);
                    mapButtonIcon.setFitWidth(50);
                    mapButtonIcon.setFitHeight(50);
                    mapArray[i][j] = mapButtonIcon;
                    */
                    roomButtons[i][j] = new MapRoomButton(gameController,i,j, (int) (Math.random()*6),this );


                } catch (Exception e) {
                    e.printStackTrace();
                }
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
                //System.out.println("i: "+i);
                //mapButtons.add(mapArray[j][i],firstX+j ,firstY-j);
                mapButtons.add(roomButtons[j][i],firstX+j ,firstY-j);
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

        //Initilize Save Game Button
        mapMenu = new MapMenu(gameController);

        mapScroll.setContent(pane);


        mapScroll.setVvalue(mapScroll.getVmax() );
        getChildren().addAll(mapScroll,mapMenu,returnB);
        returnB.setTranslateX(1100);
        returnB.setTranslateY(620);



    }

    /**
     * Visit.
     *
     * @param i    the
     * @param j    the j
     * @param room the room
     */
    public void visit(int i,int j,Room room){

        gameController.visit(i,j);
        RoomController controller = gameController.createController(room);
        //getChildren().removeAll(scroll);
        //getChildren().clear();
        if(controller instanceof FightController){
            GameScene roomScene = new GameScene((FightController) controller, this, gameController.getFloorNumber());
            getChildren().addAll(roomScene);
        }
        else if(controller instanceof MerchantController){
            MerchantRoomScene merchant = new MerchantRoomScene(controller, this);
            getChildren().addAll(merchant);
        }
        else if(controller instanceof EventController){
            EventScene event = new EventScene(new HUDPane(((EventController)controller).getCharacter()), new EventController(((EventController)controller).getCharacter(),room),this);
            getChildren().addAll(event);
        }
        else if(controller instanceof RestSiteController){
            RestScene rest = new RestScene((RestSiteController)controller, new HUDPane(((RestSiteController)controller).getCharacter()), gameController.getFloorNumber() );
            getChildren().addAll(rest);
        }
        else if(controller instanceof TreasureController){
            TreasureScene treasure = new TreasureScene(((TreasureController)controller), new HUDPane(((TreasureController)controller).getCharacter()), gameController.getFloorNumber(), this );
            getChildren().addAll(treasure);
        }
    }

    /**
     * Get room ımage ımage.
     *
     * @param room the room
     * @return the ımage
     */
    private Image getRoomImage(Room room ){
        InputStream is = null;
        Image img = null;

        try {
            int roomNum = (int) (Math.random()*6);
            if( room == null){
                is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/empty.png"));
            }
            else{
                //System.out.println("--------Room is not null.---------");
                if( roomNum == 0) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/elite.png"));
                }
                if( roomNum == 1) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/enemy.png"));
                }
                if( roomNum == 2) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/merchant.png"));
                }
                if( roomNum == 3) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/rest.png"));
                }
                if( roomNum == 4) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/tresaure.png"));
                }
                if( roomNum == 5) {
                    is = Files.newInputStream(Paths.get("resources/images/" + "map-icons/unknown.png"));
                }

            }


            /*
            if( room == null) {
                is = Files.newInputStream(Paths.get("resources/images/" + "empty-room.png"));
            }
            else if( room instanceof EnemyRoom)
                is = Files.newInputStream(Paths.get("resources/images/" + "monster-room.png"));
            else if(room instanceof MerchantRoom)
                System.out.println("---------------MERCHANT ROOM--------------");
            */
            img = new Image(is);
            is.close(); //this is to give access other programs to that image as well.
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    /**
     * Convert ındex int [ ].
     *
     * @param i the
     * @param j the j
     * @return the int [ ]
     */
    private int[] convertIndex (int i , int j)
    {
        int rowNum = 2* mapLength -1 ;
        int columnNum = 2* mapLength -1 ;
        int firstX = (int) (rowNum/2);
        int firstY = (columnNum-1);
        //System.out.println("i :"+i+"   j:"+j);
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
        //System.out.println("x :"+firstX+"   y:"+firstY);
        return arr;
    }


    /**
     * Draw path.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public void drawPath(int x1 , int y1, int x2, int y2)
    {
        int length = ((x2-x1)*100) ;
        int height = ((y2-y1)*140) ;

        int startX = (x1*100 + (550-80*(mapLength -1)) +25 ) ;
        int startY = (y1*140 + mapLength *100 + 25 );

        //System.out.println("StartX: "+startX+"StartY: "+startY+"EndX: "+startX+length+"EndY: "+startY+height);

        Line line = new Line(startX,startY,startX+length,startY+height);
        line.getStrokeDashArray().addAll(15d, 10d);
        pane.getChildren().addAll(line);
    }


}

