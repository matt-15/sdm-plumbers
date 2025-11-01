package controller;

import database.UserDAO;
import model.User;
import java.util.List;

public class ViewUserController {
    private final UserDAO userDAO = new UserDAO();

    public List<User> viewUsers() {
        return userDAO.getAllUsers();
    }
}

