package uni_staff;

import database.User_database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Second_screen extends Stage implements EventHandler<ActionEvent> {

    StackPane root = new StackPane();
    Label user, pass, depart, note;
    TextField user_name, password;
    ComboBox department;
    Button send, back;

    ObservableList<String> departmentList = FXCollections.observableArrayList(
            "CS", "IS", "IT"
    );

    public Second_screen() {

        root.setBackground(Imags.getBGImage());

        user = new Label("User name");
        pass = new Label("Password");
        depart = new Label("Department");
        send = new Button("send data");
        back = new Button("back");
        user_name = new TextField();
        password = new TextField();
        department = new ComboBox(departmentList);

        GridPane gridPane = new GridPane();

        HBox hBox = new HBox(20, back, send);
        hBox.setAlignment(Pos.CENTER);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(user, 0, 0);
        gridPane.add(pass, 0, 1);
        gridPane.add(depart, 0, 2);
        gridPane.add(hBox, 0, 3);

        gridPane.add(user_name, 1, 0);
        gridPane.add(password, 1, 1);
        gridPane.add(department, 1, 2);
        GridPane.setColumnSpan(hBox, 2);

        department.setMaxWidth(Double.MAX_VALUE);

        gridPane.setHgap(20);
        gridPane.setVgap(20);

        root.getChildren().add(gridPane);

        this.setTitle("Log up");
        this.setResizable(false);

        send.setOnAction(this);
        back.setOnAction(this);
        Scene scene = new Scene(root, 428, 322);
        this.setScene(scene);

    }

    @Override
    public void handle(ActionEvent event) {

        if (event.getSource() == back) {
            this.close();
            Main.firstScreen.show();
        }

        if (event.getSource() == send) {
            String t1 = department.getSelectionModel().getSelectedItem().toString();
            if (t1.equalsIgnoreCase("cs") || t1.equalsIgnoreCase("is") || t1.equalsIgnoreCase("it")) {
                User_database.insert_user(user_name.getText(), password.getText(), department.getSelectionModel().getSelectedItem().toString());
                this.close();
                setDefalutValues();
                Speitcal.showAlert("New user", "Hallo doctor " + user_name.getText(), AlertType.INFORMATION);
                Main.firstScreen.show();
            } else {
                Speitcal.showAlert("Error department", "Department is not exist", AlertType.ERROR);
            }
        }

    }
    
    public void setDefalutValues(){
        user_name.setText("");
        password.setText("");
        department.getSelectionModel().select(-1);
    }

}
