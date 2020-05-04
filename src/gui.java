// Class File for javafx GUI 

// impors for program
import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.util.InvalidPropertiesFormatException;
import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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
    public static User user;
    private Object InvalidPropertiesFormatException;

    public static void main(String[] args, User user) {
        gui.user = user;
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        // labels for fields
        Label lName = new Label("Name");
        Label lType = new Label("Type");
        Label lDate = new Label("Date");
        Label lStartTime = new Label("Start Time");
        Label lDuration = new Label("Duration");
        Label lFreq = new Label("Frequency");
        Label lEndDate = new Label("EndDate");

        // Button labels
        String submit = "Submit";
        String View = "View";
        String Clear = "Clear";

        // text fields
        TextField tf_name = new TextField();
        TextField tf_type = new TextField();
        TextField tf_date = new TextField();
        TextField tf_startTime = new TextField();
        TextField tf_duration = new TextField();
        TextField tf_endDate = new TextField();
        TextField tf_freq = new TextField();

        // button declarations
        Button bSubmit = new Button(submit);
        Button bView = new Button(View);
        Button bClear = new Button(Clear);

        // create view & scene & pane whatever it is
        GridPane root = new GridPane();  // root of view I think

        // adding text fields
        root.addRow(0, lName, tf_name);
        root.addRow(1, lType, tf_type);
        root.addRow(2, lDate, tf_date);
        root.addRow(3, lStartTime, tf_startTime);
        root.addRow(4, lDuration, tf_duration);
        root.addRow(5, lEndDate, tf_endDate);
        root.addRow(6, lFreq, tf_freq);

        // add buttons
        // TODO add clear and view buttons inline
        root.addRow(6, bSubmit);
        root.addRow(7,bView);
        // create Scene
        Scene scene = new Scene(root, 800, 800);

        // stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("PSS");
        primaryStage.show();


        // event handler using lambda expression
        // TODO type checking/parsing and writing interation between other classes 
        bSubmit.setOnAction(event -> {
            try {
                // variables to store text field data, data types pending adjust as needed
                String name = "";
                if (!tf_name.getText().isEmpty()){
                    name = tf_name.getText();
                    System.out.println("Name is " + name);
                }
                else {
                    throw new Exception();
                }

                String type = "";
                if (!tf_type.getText().isEmpty()){
                    type = tf_type.getText();
                    System.out.println("Type is " + type);
                }
                else {
                    throw new Exception();
                }

                String date = "";
                if(!tf_date.getText().isEmpty()){
                    date = tf_date.getText();
                }
                else{
                    throw new Exception();
                }
                int startDate = Integer.parseInt(date);
                System.out.println("Date is " + date);

                String StartTime = "";
                if(!tf_startTime.getText().isEmpty()) {
                    StartTime = tf_startTime.getText();
                }
                else{
                    throw new Exception();
                }
                double startTime = Double.parseDouble(StartTime);
                System.out.println("Start time is " + StartTime);

                String Duration = "";
                double duration;
                if(!tf_duration.getText().isEmpty()){
                    Duration =tf_duration.getText();
                    System.out.println("Duration is " + Duration);
                    duration = Double.parseDouble(Duration);
                }
                else {
                     throw new Exception();
                }

                String EndDate = "";
                int endDate;
                if(!tf_endDate.getText().isEmpty()){
                    EndDate = tf_endDate.getText();
                    System.out.println("End date is "+ EndDate);
                     endDate = Integer.parseInt(EndDate);
                }
                else {
                     endDate = 0;
                }


                String freq = "";
                int frequency;
                if(!tf_freq.getText().isEmpty()){
                    freq = tf_freq.getText();
                    System.out.println("Freq is " + freq);
                     frequency = Integer.parseInt(freq);
                }
                else {
                     frequency = 0;
                }

                //Create the appropriate task
                if (frequency != 0) {
                    user.addrecurring(name, type, startDate, startTime, duration, endDate, frequency);
                    System.out.println("Recurring made");
                } else if (type.equals("Cancellation")) {
                    user.antitask(name, startDate, startTime, duration);
                    System.out.println("Anti made");
                } else if (endDate == 0 && frequency == 0 ) {
                    user.addtransient(name, type, startDate, startTime, duration);
                    System.out.println("Transient made");
                }
            }catch(Exception e){
                //TODO notify user that the input is bad
            }
        });

        bView.setOnAction(event -> {
            display();
        });
    }
    public static void display() {
        Stage popUp = new Stage();
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.setTitle("View Events");
        VBox layout = new VBox(10);
        Scene scene1 = new Scene(layout, 600, 600);
        new DatePickerClass().start(popUp);
        // popUp.setScene(scene1);
        // popUp.showAndWait();
    }
   /* public static void display() {
        Stage popUp = new Stage();
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.setTitle("View Events");
        VBox layout = new VBox(10);
        Scene scene1 = new Scene(layout, 600, 600);

        popUp.setScene(scene1);
        popUp.showAndWait();
    }*/
}
