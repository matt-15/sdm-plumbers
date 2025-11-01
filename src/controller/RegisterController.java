package controller;

import database.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> roleBox;

    @FXML
    private Button registerButton;

    @FXML
    private Label messageLabel;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    private void initialize() {
        roleBox.getItems().addAll("ADMIN", "CSR", "PIN");

        registerButton.setOnAction(e -> register());
    }

    private void register() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String role = roleBox.getValue();

        if (username.isEmpty() || password.isEmpty() || role == null) {
            messageLabel.setText("⚠ Please fill in all fields.");
            return;
        }

        if (userDAO.exists(username)) {
            messageLabel.setText("⚠ Username already exists.");
            return;
        }

        User user = new User(username, password, role);
        boolean ok = userDAO.register(user);
        if (ok) {
            messageLabel.setText("✅ Register successful!");
            closeWindow();
        } else {
            messageLabel.setText("❌ Failed to register user.");
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.close();
    }
}



