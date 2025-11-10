package boundary;

import controller.SearchHistoryController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Request;

public class ViewHistoryUI {

    private final SearchHistoryController controller = new SearchHistoryController();

    public void show(Stage stage) {

        Label title = new Label("Search Completed Request History");

        TextField requestField = new TextField();
        requestField.setPromptText("Enter Request ID");

        Button searchBtn = new Button("Search");

        TableView<Request> table = new TableView<>();

        TableColumn<Request, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getId()).asObject());

        TableColumn<Request, String> userCol = new TableColumn<>("Requester");
        userCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getUsername()));

        TableColumn<Request, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getDescription()));

        TableColumn<Request, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getStatus()));

        TableColumn<Request, String> csrCol = new TableColumn<>("Assigned CSR");
        csrCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getAssignedCSR()));

        table.getColumns().addAll(idCol, userCol, descCol, statusCol, csrCol);

        searchBtn.setOnAction(e -> {
            var data = controller.searchHistory(requestField.getText().trim());
            table.setItems(FXCollections.observableArrayList(data));
        });

        VBox box = new VBox(10, title, requestField, searchBtn, table);
        box.setPadding(new Insets(15));
        box.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(box, 750, 420));
        stage.setTitle("Request History");
        stage.show();
    }
}
