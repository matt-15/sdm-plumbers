package boundary;

import controller.SearchProfileController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Profile;

public class SearchProfileUI {

    private final SearchProfileController controller = new SearchProfileController();

    public void show(Stage stage) {
        Label title = new Label("🔍 Search Profile by Full Name");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter full name keyword");

        Button searchBtn = new Button("Search");

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

        searchBtn.setOnAction(e -> {
            String keyword = nameField.getText().trim();
            if (keyword.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Please enter a name keyword.", ButtonType.OK);
                alert.show();
                return;
            }
            table.setItems(FXCollections.observableArrayList(controller.searchProfile(keyword)));
        });

        VBox layout = new VBox(10, title, nameField, searchBtn, table);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Search Profiles");
        stage.show();
    }
}
