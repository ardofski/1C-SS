package GUI;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

class HealthBar extends StackPane {

    Rectangle outerHealthRect;
    Rectangle innerHealthRect;
    Text healthNum;
    public HealthBar(int HP) {

        double height = 10;
        double outerWidth = 60;
        double innerWidth = 60;

        double x=0.0;
        double y=0.0;
        healthNum = new Text(Integer.toString(HP));
        healthNum.setFill(Color.WHITE);
        healthNum.setFont(Font.font("COMIC SANS MS", FontWeight.BOLD, FontPosture.REGULAR, 12));

        outerHealthRect = new Rectangle( x, y, outerWidth, height);
        outerHealthRect.setStroke(Color.BLACK);
        outerHealthRect.setStrokeWidth(2);
        outerHealthRect.setStrokeType( StrokeType.OUTSIDE);
        outerHealthRect.setFill(Color.RED);

        innerHealthRect = new Rectangle( x, y, innerWidth, height);
        innerHealthRect.setStrokeType( StrokeType.OUTSIDE);
        innerHealthRect.setFill(Color.LIMEGREEN);

        getChildren().addAll( outerHealthRect, innerHealthRect, healthNum);

    }

    public void setValue( double value, int healthNu) {
        innerHealthRect.setWidth( outerHealthRect.getWidth() * value);
        healthNum.setText(Integer.toString(healthNu));
    }

}