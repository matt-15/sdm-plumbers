package boundary;

import controller.SearchRequestsController;
import controller.SaveRequestController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Request;

public class ViewRequestsUI {

    private final SearchRequestsController controller = new SearchRequestsController();
    private final SaveRequestController saveController = new SaveRequestController(); 

    public void show(Stage stage) {

        TextField userField = new TextField();
        userField.setPromptText("Search by username");

        TextField descField = new TextField();
        descField.setPromptText("Search by description");

        TextField statusField = new TextField();
        statusField.setPromptText("Search by status (Pending, Approved, Completed)");

        Button searchBtn = new Button("Search");

        TableView<Request> table = new TableView<>();
        TableColumn<Request, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getId()).asObject());

        TableColumn<Request, String> userCol = new TableColumn<>("Username");
        userCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getUsername()));

        TableColumn<Request, String> descCol = new TableColumn<>("Description");
        descCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getDescription()));

        TableColumn<Request, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getStatus()));

        // ✅ Add Save button
        TableColumn<Request, Void> saveCol = new TableColumn<>("Save");
        saveCol.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Save");

            {
                btn.setOnAction(e -> {
                    Request selected = getTableView().getItems().get(getIndex());
                    int requestID = selected.getId();

                    int userID = 1; // Temporary for demo — replace with logged CSR ID later

                    boolean ok = saveController.saveRequest(requestID, userID);
                    if (ok) {
                        new Alert(Alert.AlertType.INFORMATION, "Request saved to shortlist!").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to save request.").show();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        // ✅ Click row = viewsCount +1
        table.setRowFactory(tv -> {
            TableRow<Request> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Request req = row.getItem();
                    Request.incrementViews(req.getId());
                }
            });
            return row;
        });

        table.getColumns().addAll(idCol, userCol, descCol, statusCol, saveCol);

        searchBtn.setOnAction(e -> {
            var data = controller.searchRequests(
                    userField.getText().trim(),
                    descField.getText().trim(),
                    statusField.getText().trim()
            );
            table.setItems(FXCollections.observableArrayList(data));
        });

        VBox box = new VBox(10, userField, descField, statusField, searchBtn, table);
        box.setPadding(new Insets(10));
        box.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(box, 800, 400));
        stage.setTitle("Search CSR Requests");
        stage.show();
    }
}



