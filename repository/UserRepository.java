package project.repository;
import project.entity.User;
import java.util.Collection;

public interface UserRepository {
    void save(User user);
    User findByUsername(String username);
    void delete(String username);
    Collection<User> findAll();
}
