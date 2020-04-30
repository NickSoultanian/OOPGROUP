// Class File for javafx GUI 

// impors for program
import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

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
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        // variables to store text field data, data types pending adjust as needed
        String name;


        // labels for fields
        Label name = new Label("Name");
        Label type = new Label("Type");
        Label date = new Label("Date");
        Label startTime = new Label("Start Time");
        Label endTime = new Label("End Time");
        Label freq = new Label("Frequency");

        // Button labels
        String submit = "Submit";
        String View = "View";
        String Clear = "Clear";

        // text fields
        TextField tf_name = new TextField();
        TextField tf_type = new TextField();
        TextField tf_date = new TextField();
        TextField tf_startTime = new TextField();
        TextField tf_endTime = new TextField();
        TextField tf_freq = new TextField();

        // button declarations
        Button bSubmit = new Button(submit);
        Button bView = new Button(View);
        Button bClear = new Button(Clear);

        // create view & scene & pane whatever it is
        GridPane root = new GridPane();  // root of view I think

        // adding text fields
        root.addRow(0, name, tf_name);
        root.addRow(1, type, tf_type);
        root.addRow(2, date, tf_date);
        root.addRow(3, startTime, tf_startTime);
        root.addRow(4, endTime, tf_endTime);
        root.addRow(5, freq, tf_freq);

        // add buttons
        // TODO add clear and view buttons inline
        root.addRow(6, bSubmit);

        // create Scene
        Scene scene = new Scene(root, 800, 800);

        // stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("PSS");
        primaryStage.show();

        // TODO set getters and setters
        

    }
}