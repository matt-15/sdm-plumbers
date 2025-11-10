package boundary;

import controller.SusProfileController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SuspendProfileUI {

    private final SusProfileController controller = new SusProfileController();

    public void show(Stage stage) {
        Label title = new Label("❌ Suspend Profile");

        TextField userField = new TextField();
        userField.setPromptText("Enter User ID to suspend");

        Button suspendBtn = new Button("Suspend");

        Label messageLabel = new Label();

        suspendBtn.setOnAction(e -> {
            try {
                int userID = Integer.parseInt(userField.getText().trim());
                boolean success = controller.susProfile(userID);

                if (success) {
                    messageLabel.setText("✅ Profile suspended successfully!");
                } else {
                    messageLabel.setText("⚠ No profile found with this User ID.");
                }

            } catch (NumberFormatException ex) {
                messageLabel.setText("⚠ Please enter a valid numeric User ID.");
            }
        });

        VBox box = new VBox(10, title, userField, suspendBtn, messageLabel);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(box, 400, 250);
        stage.setScene(scene);
        stage.setTitle("Suspend Profile");
        stage.show();
    }
}


