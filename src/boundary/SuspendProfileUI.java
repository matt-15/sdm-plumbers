package boundary;

import controller.SusProfileController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SuspendProfileUI {

    private final SusProfileController controller = new SusProfileController();

    public void show(Stage stage) {
        Label label = new Label("Enter User ID to Suspend Profile:");
        TextField userIDField = new TextField();
        Button suspendBtn = new Button("Suspend");
        Label messageLabel = new Label();

        suspendBtn.setOnAction(e -> {
            String userID = userIDField.getText().trim();

            if (userID.isEmpty()) {
                messageLabel.setText("⚠ Please enter a User ID.");
                return;
            }

            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Suspension");
            confirmAlert.setHeaderText(null);
            confirmAlert.setContentText("Are you sure you want to suspend (delete) this profile?\nUser ID: " + userID);

            confirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    boolean success = controller.susProfile(userID);
                    if (success) {
                        messageLabel.setText("✅ Profile suspended successfully!");
                    } else {
                        messageLabel.setText("❌ Failed to suspend profile.");
                    }
                } else {
                    messageLabel.setText("🟡 Action cancelled.");
                }
            });
        });

        VBox layout = new VBox(12, label, userIDField, suspendBtn, messageLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        stage.setTitle("Suspend Profile");
        stage.setScene(new Scene(layout, 400, 250));
        stage.show();
    }
}


