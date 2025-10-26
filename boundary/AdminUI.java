package project.boundary;

import project.controller.UserController;
import project.controller.ProfileController;
import project.entity.User;
import project.entity.Profile;

import javax.swing.*;
import java.awt.*;

public class AdminUI extends JFrame {
    private UserController userController;
    private ProfileController profileController;
    private static final long serialVersionUID = 1L;


    public AdminUI(UserController userController, ProfileController profileController) {
        this.userController = userController;
        this.profileController = profileController;

        setTitle("Admin Panel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create panel first
        JPanel panel = new JPanel();

        // Create buttons
        JButton createUserBtn = new JButton("Create User");
        JButton viewAllBtn = new JButton("View All Users");
        JButton suspendBtn = new JButton("Suspend User");
        JButton unsuspendBtn = new JButton("Unsuspend User");
        JButton deleteBtn = new JButton("Delete User + Profile");
        JButton exitBtn = new JButton("Exit");

        // Add buttons to panel
        panel.add(createUserBtn);
        panel.add(viewAllBtn);
        panel.add(suspendBtn);
        panel.add(unsuspendBtn);
        panel.add(deleteBtn);
        panel.add(exitBtn);

        add(panel);
        setVisible(true);

        // Action listeners
        createUserBtn.addActionListener(e -> createUserDialog());
        viewAllBtn.addActionListener(e -> showAllUsers());

        suspendBtn.addActionListener(e -> {
            String username = JOptionPane.showInputDialog(this, "Enter username to suspend:");
            if (username != null) userController.suspendUser(username);
        });

        unsuspendBtn.addActionListener(e -> {
            String username = JOptionPane.showInputDialog(this, "Enter username to unsuspend:");
            if (username != null) userController.unsuspendUser(username);
        });

        deleteBtn.addActionListener(e -> {
            String username = JOptionPane.showInputDialog(this, "Enter username to delete:");
            if (username != null) {
                userController.deleteUser(username);
                profileController.deleteProfile(username);
            }
        });

        exitBtn.addActionListener(e -> dispose());
    }

    private void createUserDialog() {
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField roleField = new JTextField();
        JTextField fullNameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();

        Object[] fields = {
            "Username:", usernameField,
            "Password:", passwordField,
            "Role:", roleField,
            "Full Name:", fullNameField,
            "Email:", emailField,
            "Phone:", phoneField
        };

        int option = JOptionPane.showConfirmDialog(
                this, fields, "Create User + Profile", JOptionPane.OK_CANCEL_OPTION
        );
        if (option == JOptionPane.OK_OPTION) {
            User user = new User(
                    usernameField.getText(),
                    passwordField.getText(),
                    roleField.getText()
            );
            userController.createUser(user);

            Profile profile = new Profile(
                    usernameField.getText(),
                    fullNameField.getText(),
                    emailField.getText(),
                    phoneField.getText()
            );
            profileController.createProfile(profile);

            JOptionPane.showMessageDialog(this, "User and Profile created!");
        }
    }

    private void showAllUsers() {
        var users = userController.getAllUsers();
        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No users found.");
        } else {
            StringBuilder sb = new StringBuilder("--- All Users ---\n");
            for (User u : users) {
                sb.append("User: ").append(u.getUsername())
                  .append(" | Role: ").append(u.getRole())
                  .append(" | Active: ").append(u.isActive())
                  .append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString());
        }
    }
}
