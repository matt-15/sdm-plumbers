package project.controller;
import project.entity.User;
import project.repository.UserRepository;
public class AuthController {
    private UserRepository repo;
    private User currentUser;
    public AuthController(UserRepository repo) {
        this.repo = repo;
    }
    public boolean authenticate(String username, String password) {
        User user = repo.findByUsername(username);
        if (user != null && user.getPasswordHash().equals(password) && user.isActive()) {
            currentUser = user;
            return true;
        }
        return false;
    }
    public void logout() {
        currentUser = null;
    }
    public User getCurrentUser() {
        return currentUser;
    }
    // NEW METHOD: Add this to provide role access without direct Entity calls
    public String getCurrentUserRole() {
        if (currentUser != null) {
            return currentUser.getRole();
        }
        return null;  // Or throw an exception if no user is logged in
    }
}