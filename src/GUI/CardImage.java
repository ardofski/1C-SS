package GUI;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import Model.Card;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

class CardImage extends StackPane {
	InputStream is;
   Image img;
   public Text cardEnergy;
   Boolean upgrade;

	/**
	 *
	 * @param card
	 */
   public CardImage(Card card)
	{
		// Initializing properties of a card Image
		String cardNam = card.getName();
		String type = card.getType();
		String energy = ""+card.getEnergy();
		String desc = ""+card.getDescription();
		boolean upgrade = card.getUpgrade();

		// Creating a rectangle to cover all card image
		Rectangle bg = new Rectangle(175,240);
		//Rectangle texts = new Rectangle(100,60);
		this.upgrade = upgrade;
		ImageView cardBG;
		setHeight(210);
		setWidth(150);
		
		// Creating an image from database
		try {
			if(upgrade)
		   		is = Files.newInputStream(Paths.get("resources/images/card"+type+"U.png"));
			else
				is = Files.newInputStream(Paths.get("resources/images/card"+type+".png"));
		   img = new Image(is);
         is.close(); //this is to give access other programs to that image as well.
         cardBG = new ImageView(img); 
         cardBG.setFitWidth(40); 
         cardBG.setFitHeight(40);	        
		} catch (IOException e) {
		e.printStackTrace();
		} //get the image  

		//Filling rectangle with that image
		bg.setFill(new ImagePattern(img));


		// Text object for card name
		Text cardName = new Text(cardNam);
		cardName.setFill(Color.WHITE);
		cardName.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 16));
		cardName.setX(10);
		cardName.setY(10);

		// Text object for card description
		Text cardDesc = new Text(desc);
		cardDesc.setFill(Color.WHITE);
		cardDesc.setFont(Font.font("COMIC SANS MS", 10));
		cardDesc.setWrappingWidth(120);
		cardDesc.setX(10);
		cardDesc.setY(10);

		// Text object for card energy
		cardEnergy = new Text(energy);
		cardEnergy.setFill(Color.WHITE);
		cardEnergy.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 20));
		cardEnergy.setX(0);
		cardEnergy.setY(0);
		
		
		setRotate(-0.5);
		
		//Positions of texts on card image
		setAlignment(cardEnergy,Pos.TOP_LEFT);
		setAlignment(cardDesc, Pos.CENTER);
		setAlignment(cardName,Pos.TOP_CENTER);
		if(!upgrade)
			cardEnergy.setTranslateX(11.5);
		else
			cardEnergy.setTranslateX(28);
		cardEnergy.setTranslateY(6.5);
		cardDesc.setTranslateY(50);
		cardDesc.setTranslateX(5);
		cardName.setTranslateY(16);
		

		// Adding all components to root group
		getChildren().addAll(bg,cardDesc,cardEnergy,cardName);

		// Adding effects for mouse enter and click
		DropShadow drop = new DropShadow(50, Color.WHITE);
	     drop.setInput(new Glow());
		setOnMouseEntered(event -> {
         bg.setTranslateY(-50);
         cardDesc.setTranslateY(-10);
         cardEnergy.setTranslateY(-45);
         cardName.setTranslateY(-34);
         
         setEffect(drop);
     });

     setOnMouseExited(event -> {
   	  bg.setTranslateY(0);
        cardDesc.setTranslateY(40);
        cardEnergy.setTranslateY(6.5);
        cardName.setTranslateY(16);
        
        setEffect(null);
     });
     
     
	 // Mouse listeners for card image
     setOnMousePressed(event -> setEffect(drop));
     setOnMouseReleased(event -> setEffect(null));
		
	}
	
	
}
