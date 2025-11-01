package controller;

import model.User;
import java.util.ArrayList;
import java.util.List;

public class SearchUserController {

    public List<User> searchUser(String username, String role) {
        List<User> allUsers = new ArrayList<>();
         // fake data
        allUsers.add(new User(1, "admin", "123", "ADMIN"));
        allUsers.add(new User(2, "csr1", "456", "CSR"));
        allUsers.add(new User(3, "pin1", "789", "PIN"));
        allUsers.add(new User(4, "csr2", "456", "CSR"));
        allUsers.add(new User(5, "admin2", "123", "ADMIN"));

        List<User> result = new ArrayList<>();
        for (User u : allUsers) {
            boolean matchUsername = username == null || username.isEmpty() || u.getUsername().contains(username);
            boolean matchRole = role == null || role.isEmpty() || u.getRole().equalsIgnoreCase(role);

            if (matchUsername && matchRole) {
                result.add(u);
            }
        }

        System.out.println("🔍 Found " + result.size() + " user(s).");
        return result;
    }
}

