package boundary;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class ProfileManagementUI {

    private final User user; 

    public ProfileManagementUI(User user) {
        this.user = user;
    }

    public void show(Stage stage) {
        VBox box = new VBox(12);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-padding: 20;");

        Label title = new Label("👤 Profile Management - " + user.getUsername()); 

        Button createBtn = new Button("➕ Create Profile");
        createBtn.setOnAction(e -> new CreateProfileUI().show(new Stage(), user.getId())); 

        Button viewBtn = new Button("👀 View All Profiles");
        viewBtn.setOnAction(e -> new ViewProfileUI().show(new Stage()));

        Button updateBtn = new Button("📝 Update Profile");
        updateBtn.setOnAction(e -> new UpdateProfileUI().show(new Stage()));

        Button suspendBtn = new Button("❌ Suspend Profile");
        suspendBtn.setOnAction(e -> new SuspendProfileUI().show(new Stage()));

        Button backBtn = new Button("⬅ Back to Dashboard");
        backBtn.setOnAction(e -> {
            stage.close();
            new AdminDashboard(user, () -> {}).scene().getWindow();
        });

        box.getChildren().addAll(title, createBtn, viewBtn, updateBtn, suspendBtn, backBtn);

        Scene scene = new Scene(box, 420, 400);
        stage.setScene(scene);
        stage.setTitle("Profile Management");
        stage.show();
    }
}



