package uni_staff;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Main extends Application {

    static First_screen firstScreen = new First_screen();
    static Second_screen second_screen = new Second_screen();
    static Doctor doctor;

    @Override
    public void start(Stage stage) throws Exception {
        stage = firstScreen;
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
