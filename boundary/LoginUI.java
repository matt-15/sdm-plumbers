package project.boundary;

import project.controller.*;
import project.entity.User;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {
    private AuthController authController;
    private UserController userController;
    private ProfileController profileController;
    private RequestController requestController;
    private CategoryController categoryController;
    private ReportController reportController;
    private static final long serialVersionUID = 1L;


    public LoginUI(AuthController authController,
                   UserController userController,
                   ProfileController profileController,
                   RequestController requestController,
                   CategoryController categoryController,
                   ReportController reportController) {
        this.authController = authController;
        this.userController = userController;
        this.profileController = profileController;
        this.requestController = requestController;
        this.categoryController = categoryController;
        this.reportController = reportController;

        initUI();
    }

    private void initUI() {
        setTitle("Volunteer Ting");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel());
        panel.add(loginButton);

        add(panel);
        setVisible(true);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (authController.authenticate(username, password)) {
                User current = authController.getCurrentUser();
                JOptionPane.showMessageDialog(this, "Welcome " + current.getRole());

                switch (authController.getCurrentUserRole().toUpperCase()) {
                    case "ADMIN" -> new AdminUI(userController, profileController);
                    case "CSR" -> new CSRUI(requestController);
                    case "PIN" -> new PINUI(requestController, current.getUsername());
                    case "PLATFORM" -> new PlatformUI(categoryController, reportController);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login");
            }
        });
    }
}
