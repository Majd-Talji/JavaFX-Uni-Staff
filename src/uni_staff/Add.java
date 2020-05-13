package uni_staff;

import database.Student_database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Add extends StackPane implements EventHandler<ActionEvent> {

    Label fname, lname, add, depart;
    TextField firstname, lastname, addr;
    Button send;
    ComboBox department;

    ObservableList<String> departmentList = FXCollections.observableArrayList(
            "CS", "IS", "IT"
    );

    public Add() {

        fname = new Label("First name");
        lname = new Label("Last name");
        add = new Label("Address");
        depart = new Label("Department");
        firstname = new TextField();
        lastname = new TextField();
        addr = new TextField();
        department = new ComboBox(departmentList);
        send = new Button("Send data");

        GridPane gridPane = new GridPane();

        HBox hBox = new HBox(send);
        hBox.setAlignment(Pos.CENTER);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(fname, 0, 0);
        gridPane.add(lname, 0, 1);
        gridPane.add(add, 0, 2);
        gridPane.add(depart, 0, 3);
        gridPane.add(hBox, 0, 4);
        gridPane.add(firstname, 1, 0);
        gridPane.add(lastname, 1, 1);
        gridPane.add(addr, 1, 2);
        gridPane.add(department, 1, 3);
        GridPane.setColumnSpan(hBox, 2);

        department.setMaxWidth(Double.MAX_VALUE);

        send.setPrefWidth(120);

        gridPane.setHgap(20);
        gridPane.setVgap(20);

        this.getChildren().add(gridPane);

        send.setOnAction(this);
        
        setDefalutValues();

    }

    @Override
    public void handle(ActionEvent event) {

        if (event.getSource() == send) {
            String t1 = department.getSelectionModel().getSelectedItem().toString();
            if (t1.equalsIgnoreCase("cs") || t1.equalsIgnoreCase("is") || t1.equalsIgnoreCase("it")) {
                Student_database.inset_student(firstname.getText(), lastname.getText(), addr.getText(), department.getSelectionModel().getSelectedItem().toString());
                Speitcal.showAlert("New student", "Hallo " + firstname.getText(), AlertType.INFORMATION);
                setDefalutValues();
                Degree.data = Student_database.get_students(Main.doctor.department);
            } else {
                Speitcal.showAlert("Error department", "Department is not exist", AlertType.ERROR);
            }
        }

    }
    
    public void setDefalutValues(){
        firstname.setText("");
        lastname.setText("");
        addr.setText("");
        department.getSelectionModel().select(-1);
    }

}
