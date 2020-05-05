import java.time.LocalDate;
import java.util.Locale;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DatePickerClass extends Application {
    public static User tempUser;
    private Stage stage;
    private DatePicker checkInDatePicker;
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("Show tasks on given day ");
        initUI();
        stage.show();
    }

    private void initUI() {

        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(vbox, 300, 300);
        stage.setScene(scene);

        checkInDatePicker = new DatePicker();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label checkInlabel = new Label("Check day for tasks:");
        gridPane.add(checkInlabel, 0, 0);

        GridPane.setHalignment(checkInlabel, HPos.LEFT);
        gridPane.add(checkInDatePicker, 0, 1);
        vbox.getChildren().add(gridPane);

        TextArea textArea = new TextArea();
        vbox.getChildren().addAll(textArea);

        Button button = new Button("Choose Date");
        vbox.getChildren().add(button);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override

            public void handle(ActionEvent actionEvent) {
                textArea.setText("");
                String finalConcat ="";
                Scheduler tempSched = new Scheduler();
                LocalDate value = checkInDatePicker.getValue();
                tempSched.tasks = tempUser.returnTaskOnDay(dateConvergence(value));
                String tempName = "";
                String tempType = "";
                String tempStartDate = "";
                String tempStartTime = "";
                String tempDuration = "";
                String tempEndDate = "";
                String tempFrequency = "";

                for (int i = 0; i < tempSched.tasks.size(); i++) {
                    if (!tempSched.tasks.get(i).getName().isEmpty()) {
                        tempName = String.valueOf(tempSched.tasks.get(i).getName());
                    }
                    if (!tempSched.tasks.get(i).getType().isEmpty()) {
                        tempType = String.valueOf(tempSched.tasks.get(i).getType());
                    }

                    tempStartDate = String.valueOf(tempSched.tasks.get(i).getStartDate());

                    tempStartTime = String.valueOf(tempSched.tasks.get(i).getStartTime());

                    if (tempSched.tasks.get(i).getDuration() != 0) {
                        tempDuration = String.valueOf(tempSched.tasks.get(i).getDuration());
                    }

                    if (String.valueOf(tempSched.tasks.get(i).getClass()).compareTo("class RecurringTask") == 0) {
                        tempEndDate = String.valueOf(tempSched.tasks.get(i).getEndDate());
                    }

                    if (String.valueOf(tempSched.tasks.get(i).getClass()).compareTo("class RecurringTask") == 0) {
                        tempFrequency = String.valueOf(tempSched.tasks.get(i).getFrequency());
                    }

                    finalConcat += tempName + "\n" + tempType + "\n" + tempStartDate + "\n" + tempStartTime + "\n" + tempDuration + "\n" + tempEndDate + "\n" + tempFrequency + "\n" + "\n";
                    textArea.setText(finalConcat);

                }

                textArea.setEditable(false);

            } });
    }
    private static int dateConvergence(LocalDate date){
        int jsonDate;
        String tempDate;
        tempDate = String.valueOf(date);
        String[] stringyArr = tempDate.split("-");

        String year = stringyArr[0];
        String month= stringyArr[1];
        String day= stringyArr[2];
        tempDate = year + month + day;
        jsonDate = Integer.parseInt(tempDate);

        return jsonDate;
    }
    public DatePickerClass ( User tempUser){
        this.tempUser = tempUser;
    }
}
