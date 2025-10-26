package project.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import project.entity.User;
import project.repository.UserRepository;

public class JdbcUserRepository implements UserRepository {
    private Connection conn;

    public JdbcUserRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public User findByUsername(String username) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM users WHERE username = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User u = new User(
                    rs.getString("username"),
                    rs.getString("password_hash"),
                    rs.getString("role")
                );
                if (!rs.getBoolean("active")) u.suspend();
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // implement createUser, deleteUser, etc.
}

//i'll make a database and connect it later.
