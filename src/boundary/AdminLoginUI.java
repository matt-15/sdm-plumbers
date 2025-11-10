package boundary;

import controller.AdminAuthController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class AdminLoginUI {

    private final AdminAuthController controller = new AdminAuthController();

    public void show(Stage stage) {
        Label title = new Label("Admin Login");
        TextField userField = new TextField();
        userField.setPromptText("Username");

        PasswordField passField = new PasswordField();
        passField.setPromptText("Password");

        Button loginBtn = new Button("Login");
        Label msg = new Label();

        loginBtn.setOnAction(e -> {
            User user = controller.login(userField.getText().trim(), passField.getText().trim());

            if (user != null && "ADMIN".equalsIgnoreCase(user.getRole())) {
                msg.setText("✅ Login successful");

                Stage dash = new Stage();
                dash.setScene(new AdminDashboard(user, () -> {
                    stage.show(); // return to login on logout if needed
                }).scene());
                dash.setTitle("Admin Dashboard");
                dash.show();

                stage.close();
            } else {
                msg.setText("❌ Invalid admin credentials");
            }
        });

        VBox box = new VBox(10, title, userField, passField, loginBtn, msg);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-padding: 20;");

        stage.setScene(new Scene(box, 350, 250));
        stage.setTitle("Admin Login");
        stage.show();
    }
}


