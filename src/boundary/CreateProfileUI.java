package boundary;

import controller.CreateProfileController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateProfileUI {
    private final CreateProfileController controller = new CreateProfileController();

    public void show(Stage stage, int userID) {
        Label title = new Label("Create New Profile");
        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");

        Button createBtn = new Button("Create");
        Label messageLabel = new Label();

        createBtn.setOnAction(e -> {
            String fullName = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();

            boolean success = controller.createProfile(userID, fullName, email, phone);
            if (success) {
                messageLabel.setText("✅ Profile created successfully!");
            } else {
                messageLabel.setText("❌ Failed to create profile.");
            }
        });

        VBox box = new VBox(10, title, nameField, emailField, phoneField, createBtn, messageLabel);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-padding: 20;");
        stage.setScene(new Scene(box, 400, 300));
        stage.setTitle("Create Profile");
        stage.show();
    }
}
