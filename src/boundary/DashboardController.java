package boundary;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.User;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    private User currentUser;

    public void setCurrentUser(User user) {
        this.currentUser = user;
        welcomeLabel.setText("Welcome, " + user.getUsername() + " (" + user.getRole() + ")");
    }

    @FXML
    public void initialize() {
        if (currentUser == null) {
            welcomeLabel.setText("Welcome to CSR Dashboard!");
        }
    }
}




