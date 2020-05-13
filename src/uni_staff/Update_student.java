package uni_staff;

import database.Student_database;
import domain.Student;
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

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Update_student extends GridPane implements EventHandler<ActionEvent> {

    Label fname, lname, add, depart, idl;
    TextField firstname, lastname, addr, id;
    ComboBox<String> department;
    Button search, update;
    Student student;

    public Update_student() {

        this.setAlignment(Pos.CENTER);
        this.setHgap(20);
        this.setVgap(15);

        idl = new Label("Enter id student");
        fname = new Label("First name");
        lname = new Label("Last name");
        add = new Label("Address");
        depart = new Label("Department");

        id = new TextField();
        firstname = new TextField();
        lastname = new TextField();
        addr = new TextField();
        department = new ComboBox<>();

        search = new Button("Search");
        update = new Button("Update degree");

        this.add(idl, 0, 0);
        this.add(fname, 0, 1);
        this.add(lname, 0, 2);
        this.add(add, 0, 3);
        this.add(depart, 0, 4);

        this.add(id, 1, 0);
        this.add(firstname, 1, 1);
        this.add(lastname, 1, 2);
        this.add(addr, 1, 3);
        this.add(department, 1, 4);

        department.setMaxWidth(Double.MAX_VALUE);

        this.add(search, 2, 0);

        HBox hBox = new HBox(update);
        this.add(hBox, 0, 5);
        GridPane.setColumnSpan(hBox, 3);
        hBox.setAlignment(Pos.CENTER);

        search.setOnAction(this);
        update.setOnAction(this);
        defalutValues();

    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == search) {
            student = Student_database.get_student(Integer.parseInt(id.getText()));
            if (student != null) {
                firstname.setText(student.getFname());
                lastname.setText(student.getLname());
                addr.setText(student.getAddress());
                department.getSelectionModel().select(student.getDepartment());
                id.setText(student.getId() + "");
                setDisableValues();
            } else {
                defalutValues();
                Speitcal.showAlert("Not student", "In database not student by id : " + id.getText(), AlertType.INFORMATION);
            }
        }

        if (event.getSource() == update) {
            Student_database.update_student(Integer.parseInt(id.getText()), firstname.getText(), lastname.getText(), addr.getText(), department.getSelectionModel().getSelectedItem());
            Speitcal.showAlert("Update student", "Hallo " + firstname.getText(), AlertType.INFORMATION);
        }
    }

    public void defalutValues() {
        firstname.setText("");
        lastname.setText("");
        addr.setText("");
        department.getSelectionModel().select(-1);
        firstname.setDisable(true);
        lastname.setDisable(true);
        addr.setDisable(true);
        department.setDisable(true);
        update.setDisable(true);
    }

    public void setDisableValues() {
        firstname.setDisable(false);
        lastname.setDisable(false);
        addr.setDisable(false);
        department.setDisable(false);
        update.setDisable(false);
    }

}
