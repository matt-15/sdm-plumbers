package boundary;

import controller.SusUserController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SuspendUserUI {
    private final SusUserController susUserController = new SusUserController();

    public void show(Stage stage) {
        Label title = new Label("Suspend User");
        title.setStyle("-fx-font-size: 16px; -fx-padding: 0 0 10 0;");

        TextField userIdField = new TextField();
        userIdField.setPromptText("Enter User ID to suspend");

        Button suspendBtn = new Button("Suspend");
        Label messageLabel = new Label();

        suspendBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(userIdField.getText().trim());
                boolean ok = susUserController.susUser(id);
                if (ok) {
                    messageLabel.setText("✅ User suspended successfully!");
                } else {
                    messageLabel.setText("❌ Suspend failed. User may not exist.");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("⚠ Please enter a valid numeric ID.");
            }
        });

        VBox root = new VBox(10, title, userIdField, suspendBtn, messageLabel);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20;");
        stage.setScene(new Scene(root, 300, 200));
        stage.setTitle("Suspend User");
        stage.show();
    }
}

