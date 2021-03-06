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
 *
 */
public class gui extends Application {
    public static User user = new User();
    public static PSSMain pssst = new PSSMain(user);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        new DatePickerClass(user);
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
        String Save = "Save tasks...";
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
        Button bSave = new Button(Save);
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

        // event handler using lambda expression
        bSubmit.setOnAction(event -> {
            try {
                // variables to store text field data, data types pending adjust as needed
                String name = "";
                if (!tf_name.getText().isEmpty()){
                    name = tf_name.getText();
                    System.out.println("Name is " + name);
                    tf_name.clear();
                }
                else {
                    throw new Exception();
                }

                String type = "";
                if (!tf_type.getText().isEmpty()){
                    type = tf_type.getText();
                    System.out.println("Type is " + type);
                    tf_type.clear();
                }
                else {
                    throw new Exception();
                }

                String date = "";
                if(!tf_date.getText().isEmpty()){
                    date = tf_date.getText();
                    tf_date.clear();
                }
                else{
                    throw new Exception();
                }
                int startDate = Integer.parseInt(date);
                System.out.println("Date is " + date);

                String StartTime = "";
                if(!tf_startTime.getText().isEmpty()) {
                    StartTime = tf_startTime.getText();
                    tf_startTime.clear();
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
                    tf_duration.clear();
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
                    tf_endDate.clear();
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
                    tf_freq.clear();
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
            displayList();
        });

        bCalendarView.setOnAction(event -> {
            displayCalendar();
        });
    }

    public void fileChooserConfig(FileChooser filechooser){

        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON files", "*.json*"));
    }

    public static void displayList(){
        FileChooser fileChooser = new FileChooser();

        ScrollPane sp = new ScrollPane();
        Stage popUp = new Stage();
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.setTitle("View Events");

        VBox layout = new VBox(10);
        layout.getChildren().add(sp);
        sp.setVmax(100);
        sp.setHmax(1000);
        sp.setPrefSize(0, 0);

        String allTasksAsString = user.returnAllTasks();
        TextArea textArea = new TextArea(allTasksAsString);
        textArea.setEditable(false);
        layout.getChildren().add(textArea);

        Button saveButton = new Button("Save...");
        saveButton.setOnAction(event ->{
            try {
                fileChooser.setTitle("Save list to JSON File");
                File file = fileChooser.showSaveDialog(popUp);
                pssst.WriteJSonFile(user.returnTasksListAsList(),file);

            }catch(Exception e){e.printStackTrace();}
        });

        layout.getChildren().add(saveButton);
        sp.vvalueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
            }
        });

        Scene scene1 = new Scene(layout, 300, 300);
         popUp.setScene(scene1);
         popUp.showAndWait();
         textArea.clear();
    }

    public static void displayCalendar() {

        Stage popUp = new Stage();
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.setTitle("View Events by day: ");

        new DatePickerClass(user).start(popUp);
    }}


