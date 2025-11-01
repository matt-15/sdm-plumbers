package boundary;

import model.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PINDashboard {
    private final User user;
    private final Runnable onLogout;

    public PINDashboard(User user, Runnable onLogout) {
        this.user = user;
        this.onLogout = onLogout;
    }

    public Scene scene() {
        VBox box = new VBox(12);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-padding: 20;");
        Label welcome = new Label("Welcome, " + user.getUsername() + " (PIN)");
        Button logout = new Button("Logout");
        logout.setOnAction(e -> {
            logout.getScene().getWindow().hide();
            onLogout.run();
        });
        box.getChildren().addAll(welcome, logout);
        return new Scene(box, 520, 320);
    }
}

