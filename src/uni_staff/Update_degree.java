package uni_staff;

import database.Degree_database;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Update_degree extends GridPane implements EventHandler<ActionEvent> {

    Label m1, m2, m3, m4, m5, m6, idl;
    TextField t1, t2, t3, t4, t5, t6, id;
    Button search_id, update_degree;
    domain.Degree degree;

    public Update_degree() {

        this.setAlignment(Pos.CENTER);
        this.setHgap(20);
        this.setVgap(15);

        idl = new Label("Enter id student");
        m1 = new Label("IS");
        m2 = new Label("NC");
        m3 = new Label("IT");
        m4 = new Label("SC");
        m5 = new Label("SA");
        m6 = new Label("AI");

        id = new TextField();
        t1 = new TextField();
        t2 = new TextField();
        t3 = new TextField();
        t4 = new TextField();
        t5 = new TextField();
        t6 = new TextField();

        search_id = new Button("Search");
        update_degree = new Button("Update degree");

        this.add(idl, 0, 0);
        this.add(m1, 0, 1);
        this.add(m2, 0, 2);
        this.add(m3, 0, 3);
        this.add(m4, 0, 4);
        this.add(m5, 0, 5);
        this.add(m6, 0, 6);

        this.add(id, 1, 0);
        this.add(t1, 1, 1);
        this.add(t2, 1, 2);
        this.add(t3, 1, 3);
        this.add(t4, 1, 4);
        this.add(t5, 1, 5);
        this.add(t6, 1, 6);

        this.add(search_id, 2, 0);

        HBox update = new HBox(update_degree);
        this.add(update, 0, 7);
        GridPane.setColumnSpan(update, 3);
        update.setAlignment(Pos.CENTER);

        search_id.setOnAction(this);
        update_degree.setOnAction(this);

    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == search_id) {
            degree = Degree_database.get_degree(Integer.parseInt(id.getText()));
            if (degree != null) {
                t1.setText(degree.getM1() + "");
                t2.setText(degree.getM2() + "");
                t3.setText(degree.getM3() + "");
                t4.setText(degree.getM4() + "");
                t5.setText(degree.getM5() + "");
                t6.setText(degree.getM6() + "");
            } else {
                Speitcal.showAlert("Not student", "In database not student by id : " + id.getText(), AlertType.INFORMATION);
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                t6.setText("");
            }
        }

        if (event.getSource() == update_degree) {
            Degree_database.update_degree(Integer.parseInt(id.getText()), Integer.parseInt(t1.getText()), Integer.parseInt(t2.getText()), Integer.parseInt(t3.getText()), Integer.parseInt(t4.getText()), Integer.parseInt(t5.getText()), Integer.parseInt(t6.getText()));
            Speitcal.showAlert("Update degree", "Updated degree ", AlertType.INFORMATION);
            id.setText("");
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
            t6.setText("");
        }
    }

}
