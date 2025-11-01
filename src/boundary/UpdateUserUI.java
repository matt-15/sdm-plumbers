package boundary;

import controller.UserController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UpdateUserUI {
    private final UserController controller = new UserController();

    public void show(Stage stage) {
        Label title = new Label("Update User Information");
        title.setStyle("-fx-font-size: 16px; -fx-padding: 0 0 10 0;");

        TextField idField = new TextField();
        idField.setPromptText("Enter User ID");

        TextField usernameField = new TextField();
        usernameField.setPromptText("New Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("New Password");

        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.getItems().addAll("ADMIN", "CSR", "PIN");
        roleBox.setPromptText("Select New Role");

        Button updateBtn = new Button("Update");
        Label messageLabel = new Label();

        updateBtn.setOnAction(e -> {
            try {
                int userId = Integer.parseInt(idField.getText().trim());
                String newUsername = usernameField.getText().trim();
                String newPassword = passwordField.getText().trim();
                String newRole = roleBox.getValue();

                boolean ok = controller.updateUser(userId, newUsername, newPassword, newRole);
                if (ok) {
                    messageLabel.setText("✅ User updated successfully!");
                } else {
                    messageLabel.setText("❌ Failed to update user.");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("⚠ Please enter a valid numeric User ID.");
            }
        });

        VBox root = new VBox(8, title, idField, usernameField, passwordField, roleBox, updateBtn, messageLabel);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20;");
        stage.setScene(new Scene(root, 400, 350));
        stage.setTitle("Update User");
        stage.show();
    }
}

