package uni_staff;

import database.Student_database;
import domain.Student;
import java.text.MessageFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Print_degree extends VBox implements EventHandler<ActionEvent> {

    TableView<Student> tableView;
    Button print;

    ObservableList<Student> data = FXCollections.observableArrayList();

    public Print_degree() {

        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(5,1,1,1));
        this.setSpacing(5);

        data = Student_database.get_students_and_degree();

        print = new Button("Print degree");
        this.getChildren().add(print);

        print.setStyle("-fx-background-color: blue; -fx-text-fill: lightGray;");
        print.setOnAction(this);

        TableColumn columnID = new TableColumn("ID");
        TableColumn columnFirstName = new TableColumn("First Name");
        TableColumn columnLastName = new TableColumn("Last Name");
        TableColumn columnDegree = new TableColumn("Degree");

        columnID.setPrefWidth(50);
        columnFirstName.setPrefWidth(145);
        columnLastName.setPrefWidth(150);
        columnDegree.setPrefWidth(145);

        tableView = new TableView();
        this.getChildren().add(tableView);
        tableView.getColumns().addAll(columnID, columnFirstName, columnLastName,columnDegree);

        tableView.setItems(data);

        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnFirstName.setCellValueFactory(new PropertyValueFactory<>("fname"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<>("lname"));
        columnDegree.setCellValueFactory(new PropertyValueFactory<>("address"));

    }

    @Override
    public void handle(ActionEvent event) {
        doPrintAction();
    }
    
    private void doPrintAlter() {
        MessageFormat h = new MessageFormat(".... student degree ...");
        MessageFormat f = new MessageFormat(" ... Page {1} ...");
        try {
//            tableView.print(JTable.PrintMode.NORMAL, h, f);
        } catch (Exception ee) {
            System.out.println(ee.getMessage());
        }
    }

    private void doPrintAction() {
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);

        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null && printerJob.showPrintDialog(tableView.getScene().getWindow())) {
            boolean success = printerJob.printPage(pageLayout, tableView);
            if (success) {
                printerJob.endJob();

            }
        }

        tableView.getTransforms().clear();
    }

    public void printtee() {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.printPage(tableView);
            if (success) {
                job.endJob();
            }
        }
    }

}
