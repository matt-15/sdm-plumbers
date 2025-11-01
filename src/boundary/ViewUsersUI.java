package boundary;

import controller.ViewUserController;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class ViewUsersUI {

    private final ViewUserController controller = new ViewUserController();

    public void show(Stage stage) {
        TableView<User> table = new TableView<>();

        TableColumn<User, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> roleCol = new TableColumn<>("Role");
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

        table.getColumns().addAll(idCol, usernameCol, roleCol);
        table.setItems(FXCollections.observableArrayList(controller.viewUsers()));

        VBox layout = new VBox(table);
        Scene scene = new Scene(layout, 400, 300);
        stage.setTitle("View Users");
        stage.setScene(scene);
        stage.show();
    }
}

