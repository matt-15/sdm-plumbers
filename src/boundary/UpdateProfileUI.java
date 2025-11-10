package boundary;

import controller.UpdateProfileController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UpdateProfileUI {
    private final UpdateProfileController controller = new UpdateProfileController();

    public void show(Stage stage) {
        TextField userField = new TextField();
        userField.setPromptText("Enter User ID");

        TextField nameField = new TextField();
        nameField.setPromptText("New Full Name");

        TextField emailField = new TextField();
        emailField.setPromptText("New Email");

        TextField phoneField = new TextField();
        phoneField.setPromptText("New Phone");

        Button updateBtn = new Button("Update");
        Label msg = new Label();

        updateBtn.setOnAction(e -> {
            try {
                int userID = Integer.parseInt(userField.getText().trim());
                boolean ok = controller.updateProfile(userID,
                        nameField.getText().trim(),
                        emailField.getText().trim(),
                        phoneField.getText().trim());

                msg.setText(ok ? "✅ Updated!" : "❌ Update failed.");
            } catch (Exception ex) {
                msg.setText("⚠ Invalid input.");
            }
        });

        VBox box = new VBox(10, userField, nameField, emailField, phoneField, updateBtn, msg);
        box.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(box, 400, 300));
        stage.setTitle("Update Profile");
        stage.show();
    }
}



