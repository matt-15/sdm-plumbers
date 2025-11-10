package boundary;

import model.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminDashboard {
    private final User user;
    private final Runnable onLogout;

    public AdminDashboard(User user, Runnable onLogout) {
        this.user = user;
        this.onLogout = onLogout;
    }

    public Scene scene() {
        VBox box = new VBox(12);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-padding: 20;");

        Label welcome = new Label("Welcome, " + user.getUsername() + " (Admin)");

        Button viewUsers = new Button("👥 View Users");
        viewUsers.setOnAction(e -> new ViewUsersUI().show(new Stage()));

        Button suspendUser = new Button("❌ Suspend User");
        suspendUser.setOnAction(e -> new SuspendUserUI().show(new Stage()));

        Button profileManage = new Button("👤 Profile Management");
        profileManage.setOnAction(e -> new ProfileManagementUI(user).show(new Stage()));

        Button logout = new Button("Logout");
        logout.setOnAction(e -> {
            logout.getScene().getWindow().hide();
            onLogout.run();
        });

        box.getChildren().addAll(
                welcome, 
                viewUsers, 
                suspendUser, 
                profileManage, 
                logout
        );

        return new Scene(box, 520, 350);
    }
}






