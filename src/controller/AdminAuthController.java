package controller;

import model.User;

public class AdminAuthController {

    public User login(String username, String password) {
        User u = User.login(username, password); 
        if (u != null && "ADMIN".equalsIgnoreCase(u.getRole())) {
            return u;
        }
        return null;
    }
}

