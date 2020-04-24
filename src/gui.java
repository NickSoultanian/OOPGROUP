// Class File for javafx GUI 

// impors for program
import java.util.Locale;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// to run ensure to have the javafx sdk installed 
// go here https://gluonhq.com/products/javafx/ 
// install latest version of javafx sdk which is 14 
// extra folder into preferred directory on windows I put in C:/ 
// on intelij add library and make sure it points the lib folder in the sdk fodler 
// after that headover to file -> Edit Configueration.. 
// in the text field labed "vim options:~ add this 
// --module-path "C:\javafx-sdk-14.0.1\lib" --add-modules javafx.controls,javafx.fxml 
// path in the quotes is provided you put the javafx sdk in the root directory if not just copy+paste 
// the file path inbetween the qutoes 
// - Jose L. Camacho 4-23-20 

// purpose - boiler plate program to test and get javafx running 
// sample code uncomment to test
//public class gui extends Application {
//    Button button;
//
//    public static void main(String[] args){
//        launch(args);
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        primaryStage.setTitle("Example Title");
//        button = new Button();
//        button.setText("Click me");
//
//        StackPane layout = new StackPane();
//        layout.getChildren().add(button);
//
//        Scene scene = new Scene(layout, 300, 250);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//}



public class gui extends Application {

    private Stage stage;
    private DatePicker checkInDatePicker;
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("DatePickerSample ");
        initUI();
        stage.show();
    }

    private void initUI() {
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(vbox, 400, 400);
        stage.setScene(scene);

        checkInDatePicker = new DatePicker();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label checkInlabel = new Label("Check-In Date:");
        gridPane.add(checkInlabel, 0, 0);

        GridPane.setHalignment(checkInlabel, HPos.LEFT);
        gridPane.add(checkInDatePicker, 0, 1);
        vbox.getChildren().add(gridPane);
    }
}