package project.repository;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import project.entity.User;
import project.repository.UserRepository;

public class InMemoryUserRepository implements UserRepository {
    private Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public User findByUsername(String username) {
        return users.get(username);
    }

    @Override
    public void delete(String username) {
        users.remove(username);
    }

    @Override
    public Collection<User> findAll() {
        return users.values();
    }
}
