package uni_staff;

import database.Degree_database;
import database.Student_database;
import domain.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Degree extends BorderPane implements EventHandler<ActionEvent> {

    TableView<Student> table;
    String depart;
    Label m1, m2, m3, m4, m5, m6;
    TextField t1, t2, t3, t4, t5, t6;
    Button addDegree;
    GridPane gridPane = new GridPane();

    public static ObservableList<Student> data = FXCollections.observableArrayList();

    public Degree(String department) {

        this.depart = department;

        data = Student_database.get_students(department);

        TableColumn columnID = new TableColumn("ID");
        TableColumn columnFirstName = new TableColumn("First Name");
        TableColumn columnLastName = new TableColumn("Last Name");

        columnID.setPrefWidth(50);
        columnFirstName.setPrefWidth(145);
        columnLastName.setPrefWidth(145);

        table = new TableView();
        this.setLeft(table);
        table.setPrefWidth(350);
        table.getColumns().addAll(columnID, columnFirstName, columnLastName);

        table.setItems(data);

        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnFirstName.setCellValueFactory(new PropertyValueFactory<>("fname"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<>("lname"));

        m1 = new Label("IS");
        m2 = new Label("NC");
        m3 = new Label("IT");
        m4 = new Label("SC");
        m5 = new Label("SA");
        m6 = new Label("AI");
        t1 = new TextField();
        t2 = new TextField();
        t3 = new TextField();
        t4 = new TextField();
        t5 = new TextField();
        t6 = new TextField();
        addDegree = new Button("Add Degree");

        HBox hBox = new HBox(addDegree);
        hBox.setAlignment(Pos.CENTER);
        GridPane.setColumnSpan(hBox, 2);

        FlowPane right = new FlowPane(gridPane);
        right.setPadding(new Insets(50, 30, 30, 30));

        t1.setPrefWidth(60);
        t2.setPrefWidth(60);
        t3.setPrefWidth(60);
        t4.setPrefWidth(60);
        t5.setPrefWidth(60);
        t6.setPrefWidth(60);

        gridPane.add(m1, 0, 0);
        gridPane.add(m2, 0, 1);
        gridPane.add(m3, 0, 2);
        gridPane.add(m4, 0, 3);
        gridPane.add(m5, 0, 4);
        gridPane.add(m6, 0, 5);
        gridPane.add(hBox, 0, 6);

        gridPane.add(t1, 1, 0);
        gridPane.add(t2, 1, 1);
        gridPane.add(t3, 1, 2);
        gridPane.add(t4, 1, 3);
        gridPane.add(t5, 1, 4);
        gridPane.add(t6, 1, 5);

        gridPane.setHgap(15);
        gridPane.setVgap(15);

        this.setRight(right);

        addDegree.setStyle("-fx-background-color: red; -fx-text-fill: lightGray;");
        addDegree.setOnAction(this);

        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                domain.Degree degree = Degree_database.get_degree(newValue.getId());
                if (degree != null) {
                    t1.setText(degree.getM1() + "");
                    t2.setText(degree.getM2() + "");
                    t3.setText(degree.getM3() + "");
                    t4.setText(degree.getM4() + "");
                    t5.setText(degree.getM5() + "");
                    t6.setText(degree.getM6() + "");
                    setDisable();
                } else {
                    setDefaultValues();
                    setEnable();
                }
            }
        });

        setDisable();

    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == addDegree) {
            if (table.getSelectionModel().getSelectedItem() != null) {
                int id = table.getSelectionModel().getSelectedItem().getId();
                Degree_database.inset_degree(id, Integer.parseInt(t1.getText()), Integer.parseInt(t2.getText()), Integer.parseInt(t3.getText()), Integer.parseInt(t4.getText()), Integer.parseInt(t5.getText()), Integer.parseInt(t6.getText()));
                Speitcal.showAlert("New Degree", "Inserted Degree", Alert.AlertType.INFORMATION);
                setDisable();
            } else {
                Speitcal.showAlert("Waring", "Select Student first.", Alert.AlertType.WARNING);
            }
        }
    }

    public void setEnable() {
        t1.setDisable(false);
        t2.setDisable(false);
        t3.setDisable(false);
        t4.setDisable(false);
        t5.setDisable(false);
        t6.setDisable(false);
        addDegree.setDisable(false);
    }

    public void setDisable() {
        t1.setDisable(true);
        t2.setDisable(true);
        t3.setDisable(true);
        t4.setDisable(true);
        t5.setDisable(true);
        t6.setDisable(true);
        addDegree.setDisable(true);
    }

    public void setDefaultValues() {
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
    }

}
