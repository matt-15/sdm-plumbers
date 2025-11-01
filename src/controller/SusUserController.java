package controller;

import database.UserDAO;

public class SusUserController {
    private final UserDAO userDAO = new UserDAO();

    public boolean susUser(int userId) {
        if (userId <= 0) {
            System.out.println("⚠ Invalid user ID.");
            return false;
        }

        return userDAO.suspendUser(userId);
    }
}
