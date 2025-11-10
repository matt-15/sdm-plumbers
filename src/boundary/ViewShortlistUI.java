package boundary;

import controller.ViewShortlistController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Request;

public class ViewShortlistUI {

    private final ViewShortlistController controller = new ViewShortlistController();

    public void show(Stage stage) {

        Label title = new Label("Your Saved Requests");

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

        // ✅ UNSAVE button column
        TableColumn<Request, Void> unsaveCol = new TableColumn<>("Remove");
        unsaveCol.setCellFactory(col -> new TableCell<>() {
            private final Button btn = new Button("Remove");

            {
                btn.setOnAction(e -> {
                    Request r = getTableView().getItems().get(getIndex());
                    boolean ok = controller.removeFromShortlist(r.getId());

                    if (ok) {
                        new Alert(Alert.AlertType.INFORMATION, "Removed from shortlist").show();
                        refreshTable(table);
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to remove").show();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        table.getColumns().addAll(idCol, userCol, descCol, statusCol, csrCol, unsaveCol);

        // Load on open
        refreshTable(table);

        VBox box = new VBox(10, title, table);
        box.setPadding(new Insets(15));
        box.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(box, 750, 420));
        stage.setTitle("CSR Shortlist");
        stage.show();
    }

    private void refreshTable(TableView<Request> table) {
        table.setItems(FXCollections.observableArrayList(controller.viewShortlist()));
    }
}




