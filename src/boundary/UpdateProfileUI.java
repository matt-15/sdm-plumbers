package boundary;

import controller.UpdateProfileController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UpdateProfileUI {

    private final UpdateProfileController controller = new UpdateProfileController();

    public void show(Stage stage) {
        Label title = new Label("🔧 Update Profile");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField userIDField = new TextField();
        userIDField.setPromptText("Enter User ID (number)");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter new full name");

        TextField emailField = new TextField();
        emailField.setPromptText("Enter new email");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter new phone number");

        Button updateBtn = new Button("Update");
        Label messageLabel = new Label();

        updateBtn.setOnAction(e -> {
            try {
                int userID = Integer.parseInt(userIDField.getText().trim());
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String phone = phoneField.getText().trim();

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    messageLabel.setText("⚠ Please fill in all fields.");
                    return;
                }

                boolean success = controller.updateProfile(userID, name, email, phone);
                if (success) {
                    messageLabel.setText("✅ Profile updated successfully!");
                } else {
                    messageLabel.setText("❌ Failed to update profile (check User ID).");
                }

            } catch (NumberFormatException ex) {
                messageLabel.setText("⚠ User ID must be a number!");
            } catch (Exception ex) {
                messageLabel.setText("❌ Unexpected error: " + ex.getMessage());
            }
        });

        VBox layout = new VBox(10, title, userIDField, nameField, emailField, phoneField, updateBtn, messageLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 400, 350);
        stage.setTitle("Update Profile");
        stage.setScene(scene);
        stage.show();
    }
}


