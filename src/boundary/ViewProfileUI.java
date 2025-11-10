package boundary;

import controller.ViewProfileController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

        TableColumn<Profile, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));

        table.getColumns().addAll(idCol, userIDCol, nameCol, emailCol, phoneCol);

        // Load data into table
        table.setItems(FXCollections.observableArrayList(controller.viewProfiles()));

        VBox layout = new VBox(10, table);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 600, 400);
        stage.setTitle("View All Profiles");
        stage.setScene(scene);
        stage.show();
    }
}




