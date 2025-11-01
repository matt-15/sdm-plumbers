package controller;

import database.UserDAO;

public class UserController {
    private final UserDAO userDAO = new UserDAO();

    public boolean updateUser(int userId, String newUsername, String newPassword, String newRole) {
        if (newUsername == null || newUsername.isEmpty() || newPassword == null || newPassword.isEmpty()) {
            System.out.println("⚠ Username or password cannot be empty.");
            return false;
        }

        return userDAO.updateUser(userId, newUsername, newPassword, newRole);
    }
}

