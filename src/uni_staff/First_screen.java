package uni_staff;

import database.User_database;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class First_screen extends Stage implements EventHandler<ActionEvent> {

    StackPane root = new StackPane();
    Label user, pass;
    TextField user_name;
    PasswordField password;
    Button signin, signup;

    public First_screen() {

        root.setBackground(Imags.getBGImage());

        user = new Label("User name");
        pass = new Label("Password");
        user_name = new TextField();
        password = new PasswordField();
        signin = new Button("Sign in");
        signup = new Button("Sign up");

        GridPane gridPane = new GridPane();

        HBox hBox = new HBox(20, signin, signup);
        hBox.setAlignment(Pos.CENTER);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(user, 0, 0);
        gridPane.add(pass, 0, 1);
        gridPane.add(hBox, 0, 2);
        gridPane.add(user_name, 1, 0);
        gridPane.add(password, 1, 1);
        GridPane.setColumnSpan(hBox, 2);

        gridPane.setHgap(20);
        gridPane.setVgap(20);

        root.getChildren().add(gridPane);

        this.setTitle("Uni Staff");
        this.setResizable(false);

        signin.setOnAction(this);
        signup.setOnAction(this);
        Scene scene = new Scene(root, 428, 322);
        this.setScene(scene);
    }

    @Override
    public void handle(ActionEvent event) {

        if (event.getSource() == signin) {
            int i = User_database.check_user(user_name.getText(), password.getText());
            switch (i) {
                case 1:
                    this.close();
                    Speitcal.showAlert("Welcome ", "Hallo doctor " + user_name.getText(), AlertType.INFORMATION);
                    String department = User_database.get_department(user_name.getText());
                    Main.doctor = new Doctor(department);
                    Main.doctor.show();
                    break;
                case 2:
                    Speitcal.showAlert("Password wrong ", "You gave your password incorrectly.", AlertType.WARNING);
                    break;
                default:
                    Speitcal.showAlert("User wrong", "User is not exist.", AlertType.WARNING);
                    break;
            }
        }
        if (event.getSource() == signup) {
            this.close();
            Main.second_screen.show();
        }

    }

}
