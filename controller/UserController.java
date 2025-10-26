package project.controller;

import project.entity.User;
import project.repository.UserRepository;
import java.util.Collection;
public class UserController {
    private UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    public void createUser(User user) {
        repo.save(user);
        System.out.println("User created: " + user.getUsername());
    }

    public User viewUser(String username) {
        return repo.findByUsername(username);
    }

    public void updateUser(User user) {
        repo.save(user);
        System.out.println("User updated: " + user.getUsername());
    }

    public void deleteUser(String username) {
        repo.delete(username);
        System.out.println("User deleted: " + username);
    }

    public void suspendUser(String username) {
        User u = repo.findByUsername(username);
        if (u != null) {
            u.suspend();
            repo.save(u);
            System.out.println("User suspended: " + username);
        }
    }

    public void unsuspendUser(String username) {
        User u = repo.findByUsername(username);
        if (u != null) {
            u.activate();
            repo.save(u);
            System.out.println("User unsuspended: " + username);
        }
    }

    public User searchUser(String query) {
        return repo.findAll().stream()
            .filter(u -> u.getUsername().contains(query))
            .findFirst().orElse(null);
    }
    
    public Collection<User> getAllUsers() {
        return repo.findAll();
    }

}
