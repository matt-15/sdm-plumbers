package boundary;

import controller.ViewProfileController;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Profile;

public class ViewProfileUI {

    private final ViewProfileController controller = new ViewProfileController();

    public void show(Stage stage) {
        TableView<Profile> table = new TableView<>();

        TableColumn<Profile, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Profile, Integer> userIDCol = new TableColumn<>("User ID");
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));

        TableColumn<Profile, String> nameCol = new TableColumn<>("Full Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        TableColumn<Profile, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Profile, String> phoneCol = new TableColumn<>("Phone Number");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));

        table.getColumns().addAll(idCol, userIDCol, nameCol, emailCol, phoneCol);
        table.setItems(FXCollections.observableArrayList(controller.viewAllProfiles()));

        VBox layout = new VBox(table);
        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.setTitle("View All Profiles");
        stage.show();
    }
}



