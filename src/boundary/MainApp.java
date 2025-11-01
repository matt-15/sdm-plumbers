package boundary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundary/LoginView.fxml"));
            Parent root = loader.load();

            primaryStage.setTitle("CSR Matching System - Login");
            primaryStage.setScene(new Scene(root, 400, 300));
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Failed to load LoginView.fxml");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}





