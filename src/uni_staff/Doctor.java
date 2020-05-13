package uni_staff;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Doctor extends Stage {

    String department;
    TabPane tabPane;
    Add a1;
    Degree d1;
    Print_degree d2;
    Update_student d3;
    Update_degree d4;

    public void setDepartment(String department) {
        this.department = department;
    }

    public Doctor(String department) {
        this.department = department;

        a1 = new Add();
        d1 = new Degree(department);
        d2 = new Print_degree();
        d3 = new Update_student();
        d4 = new Update_degree();

        tabPane = new TabPane();

        Tab tab1 = new Tab("Student", a1);
        Tab tab2 = new Tab("Degree", d1);
        Tab tab3 = new Tab("Print", d2);
        Tab tab4 = new Tab("Update Student", d3);
        Tab tab5 = new Tab("Update degree", d4);

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);
        tabPane.getTabs().add(tab4);
        tabPane.getTabs().add(tab5);

        tab1.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);
        tab4.setClosable(false);
        tab5.setClosable(false);

        Scene scene = new Scene(tabPane, 500, 400);
        this.setTitle("Doctor");
        this.setScene(scene);
        this.setResizable(false);
    }

}
