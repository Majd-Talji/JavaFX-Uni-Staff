package uni_staff;

import javafx.scene.control.Alert;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Speitcal {
    
    static Alert alert = new Alert(Alert.AlertType.NONE);

    public static void showAlert(String title, String message, Alert.AlertType alertType) {
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.setAlertType(alertType);
        alert.showAndWait();
    }

}
