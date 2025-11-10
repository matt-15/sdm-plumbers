package boundary;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import controller.AdminLogoutController;

public class AdminLogoutUI {

    private final AdminLogoutController controller = new AdminLogoutController();

    public void show(Stage stage) {
        Label msg = new Label("Are you sure you want to logout?");
        Button yesBtn = new Button("Logout");

        yesBtn.setOnAction(e -> {
            controller.logout();
            stage.close();
            new AdminLoginUI().show(new Stage()); // return to admin login
        });

        VBox box = new VBox(10, msg, yesBtn);
        stage.setScene(new Scene(box, 300, 200));
        stage.setTitle("Admin Logout");
        stage.show();
    }
}

