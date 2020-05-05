
import java.awt.*;
import java.io.File;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.parser.JSONParser;

/**
 * Main driving class of PSS
 */
public class gui extends Application {
    public static User user = new User();
    public static PSSMain pssst = new PSSMain(user);

    //Launches the GUI
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        new DatePickerClass();
        FileChooser fileChooser = new FileChooser();
        Desktop desktop = Desktop.getDesktop();
        // labels for fields
        Label lName = new Label("Name");
        Label lType = new Label("Type");
        Label lDate = new Label("Date");
        Label lStartTime = new Label("Start Time");
        Label lDuration = new Label("Duration");
        Label lFreq = new Label("Frequency");
        Label lEndDate = new Label("EndDate");
        Label lHeader = new Label("-OR- Enter a new task below: ");

        // Button labels
        String submit = "Submit";
        String View = "View List";
        String Calendar = "Display Calendar";
        String Clear = "Clear";
        String OpenFile = "Open a JSON file...";

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
        Button bOpenFile = new Button(OpenFile);
        Button bCalendarView = new Button(Calendar);

        // create view & scene & pane whatever it is
        BorderPane root = new BorderPane();  // root of panes
        HBox buttonPane = new HBox();       // pane to hold "view" buttons
        GridPane dataEntryPane = new GridPane(); //pane to hold all text and textfields

        dataEntryPane.setAlignment(Pos.CENTER);
        dataEntryPane.setHgap(20);
        dataEntryPane.setVgap(5);

        // adding text fields
        dataEntryPane.add(bOpenFile, 0, 0);
        dataEntryPane.add(lHeader,1,0);
        dataEntryPane.addRow(1, lName, tf_name);
        dataEntryPane.addRow(2, lType, tf_type);
        dataEntryPane.addRow(3, lDate, tf_date);
        dataEntryPane.addRow(4, lStartTime, tf_startTime);
        dataEntryPane.addRow(5, lDuration, tf_duration);
        dataEntryPane.addRow(6, lEndDate, tf_endDate);
        dataEntryPane.addRow(7, lFreq, tf_freq);
        dataEntryPane.add(bSubmit, 1, 8);

        // add buttons
        buttonPane.getChildren().addAll(bCalendarView, bView);
        buttonPane.setSpacing(40);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setBackground(new Background(new BackgroundFill(Color.rgb(60, 170, 190), CornerRadii.EMPTY, Insets.EMPTY)));

        root.setStyle("-fx-background-color: #DAE6F3;");
        root.setCenter(dataEntryPane);
        root.setBottom(buttonPane);

        Scene scene = new Scene(root, 325, 325);

        // stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("PSS");
        primaryStage.show();

        Stage openFileStage = new Stage();

        //Event handler of Open File button
        bOpenFile.setOnAction(event ->{
            try {
                fileChooser.setTitle("Open JSON File");
                fileChooserConfig(fileChooser);
                File file = fileChooser.showOpenDialog(openFileStage);
                String fileName = file.toString();
                JSONParser jParse = new JSONParser();
                pssst.ReadJsonFile(fileName, jParse);
            }catch(Exception e){}

        });

        //Event handler of Submit button
        bSubmit.setOnAction(event -> {
            try {
                // variables to store text field data, data types pending adjust as needed
                String name = "";
                if (!tf_name.getText().isEmpty()){
                    name = tf_name.getText();
                }
                else {
                    throw new Exception();
                }

                String type = "";
                if (!tf_type.getText().isEmpty()){
                    type = tf_type.getText();
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

                String StartTime = "";
                if(!tf_startTime.getText().isEmpty()) {
                    StartTime = tf_startTime.getText();
                }
                else{
                    throw new Exception();
                }
                double startTime = Double.parseDouble(StartTime);

                String Duration = "";
                double duration;
                if(!tf_duration.getText().isEmpty()){
                    Duration =tf_duration.getText();
                    duration = Double.parseDouble(Duration);
                }
                else {
                    throw new Exception();
                }

                String EndDate = "";
                int endDate;
                if(!tf_endDate.getText().isEmpty()){
                    EndDate = tf_endDate.getText();
                    endDate = Integer.parseInt(EndDate);
                }
                else {
                    endDate = 0;
                }


                String freq = "";
                int frequency;
                if(!tf_freq.getText().isEmpty()){
                    freq = tf_freq.getText();
                    frequency = Integer.parseInt(freq);
                }
                else {
                    frequency = 0;
                }

                //Create the appropriate task
                if (frequency != 0) {
                    user.addrecurring(name, type, startDate, startTime, duration, endDate, frequency);
                } else if (type.equals("Cancellation")) {
                    user.antitask(name, startDate, startTime, duration);
                } else if (endDate == 0 && frequency == 0 ) {
                    user.addtransient(name, type, startDate, startTime, duration);
                }
            }catch(Exception e){
                //TODO notify user that the input is bad
            }
        });


        bView.setOnAction(event -> {
            displayList();
        });

        bCalendarView.setOnAction(event -> {
            displayCalendar();
        });
    }

    //Provides a filter so that only .json files can be selected
    public void fileChooserConfig(FileChooser filechooser){

        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON files", "*.json*"));
    }
//    private void openFile(File file) {
//        try {
//            Desktop desktop = new Desktop;
//            desktop.open(file);
//        } catch (IOException ex) {
//
//        }
//    }

    //Displays the list of all the tasks
    public static void displayList(){

        Stage popUp = new Stage();
        ScrollPane sp = new ScrollPane();
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.setTitle("View Events");
        VBox layout = new VBox(10);
        layout.getChildren().add(sp);
        sp.setVmax(100);
        sp.setHmax(1000);
        sp.setPrefSize(0, 0);
        String allTasksAsString = user.returnAllTasks();
        TextArea textArea = new TextArea(allTasksAsString);
        textArea.setEditable(true);
        layout.getChildren().add(textArea);
        sp.vvalueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                //enter what we're putting in the scroll here;
            }
        });
        Scene scene1 = new Scene(layout, 300, 300);
         popUp.setScene(scene1);
         popUp.showAndWait();
         textArea.clear();
    }

    //Displays the calender with all the tasks
    //TODO make tasks appear on calender
    public static void displayCalendar() {
        Stage popUp = new Stage();
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.setTitle("View Events by day: ");
        VBox layout = new VBox(10);
       // Scene scene1 = new Scene(layout, 300, 200);
        new DatePickerClass().start(popUp);
    }
}


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


//    private Object InvalidPropertiesFormatException;
// private org.json.simple.parser.JSONParser JSONParser;
