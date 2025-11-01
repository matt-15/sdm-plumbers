package database;

import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection conn;

    public UserDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:csr.db");
            System.out.println("✅ Connected to SQLite: csr.db");

            String sql = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL UNIQUE,
                    password TEXT NOT NULL,
                    role TEXT NOT NULL
                );
            """;
            conn.createStatement().execute(sql);
            System.out.println("🧱 Table 'users' checked/created.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------- LOGIN --------------------
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // -------------------- REGISTER --------------------
    public boolean register(User user) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.executeUpdate();
            System.out.println("✅ User registered: " + user.getUsername());
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Register failed: " + e.getMessage());
            return false;
        }
    }

    // -------------------- CHECK USER EXISTS --------------------
    public boolean exists(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // -------------------- VIEW ALL USERS --------------------
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // -------------------- UPDATE USER --------------------
    public boolean updateUser(int userId, String newUsername, String newPassword, String newRole) {
        String sql = "UPDATE users SET username = ?, password = ?, role = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newUsername);
            stmt.setString(2, newPassword);
            stmt.setString(3, newRole);
            stmt.setInt(4, userId);
            stmt.executeUpdate();
            System.out.println("✅ User updated successfully: ID = " + userId);
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Update failed: " + e.getMessage());
            return false;
        }
    }

    // -------------------- DELETE (Suspend) USER --------------------
    public boolean suspendUser(int userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
            System.out.println("🗑 User suspended (deleted) successfully: ID = " + userId);
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Suspend failed: " + e.getMessage());
            return false;
        }
    }
}








