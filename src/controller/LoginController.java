package controller;

import database.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> roleBox;

    @FXML
    private Button loginButton;

    @FXML
    private Label messageLabel;

    private UserDAO userDAO = new UserDAO();

    @FXML
    private void initialize() {
        roleBox.getItems().addAll("ADMIN", "CSR", "PIN");
        loginButton.setOnAction(e -> login());
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String role = roleBox.getValue();

        User user = userDAO.login(username, password);
        if (user != null && role != null && role.equalsIgnoreCase(user.getRole())) {
            messageLabel.setText("✅ Login successful. Welcome " + user.getUsername());
            openDashboard(user);
        } else {
            messageLabel.setText("❌ Invalid username, password, or role.");
        }
    }

    private void openDashboard(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/DashboardView.fxml"));
            Parent root = loader.load();

            boundary.DashboardController controller = loader.getController();
            controller.setCurrentUser(user);

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("❌ Failed to open dashboard.");
        }
    }

    @FXML
    private void openRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/RegisterView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Register New User");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("❌ Failed to open register page.");
        }
    }
}







