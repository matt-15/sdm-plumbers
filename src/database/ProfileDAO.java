package database;

import model.Profile;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAO {
    private Connection conn;

    public ProfileDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:csr.db");
            System.out.println("✅ Connected to SQLite for ProfileDAO");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- CREATE PROFILE ----------------
    public boolean createProfile(Profile profile) {
        String sql = "INSERT INTO profiles (userID, fullName, email, phoneNum) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, profile.getUserID());
            stmt.setString(2, profile.getFullName());
            stmt.setString(3, profile.getEmail());
            stmt.setString(4, profile.getPhoneNum());
            stmt.executeUpdate();
            System.out.println("✅ Profile created successfully for user ID " + profile.getUserID());
            return true;
        } catch (SQLException e) {
            System.out.println("❌ Failed to create profile: " + e.getMessage());
            return false;
        }
    }

    // ---------------- VIEW ALL PROFILES ----------------
    public List<Profile> getAllProfiles() {
        List<Profile> profiles = new ArrayList<>();
        String sql = "SELECT * FROM profiles";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                profiles.add(new Profile(
                    rs.getInt("id"),
                    rs.getInt("userID"),
                    rs.getString("fullName"),
                    rs.getString("email"),
                    rs.getString("phoneNum")
                ));
            }
            System.out.println("✅ Fetched " + profiles.size() + " profiles from database.");
        } catch (SQLException e) {
            System.out.println("❌ Failed to fetch profiles: " + e.getMessage());
        }
        return profiles;
    }
    
    // -------------------- SUSPEND (DELETE) PROFILE --------------------
    public boolean suspendProfile(int userID) {
        String sql = "DELETE FROM profiles WHERE userID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("🗑 Profile deleted successfully for user ID = " + userID);
                return true;
            } else {
                System.out.println("⚠ No profile found for user ID = " + userID);
                return false;
            }
        } catch (SQLException e) {
            System.out.println("❌ Failed to suspend profile: " + e.getMessage());
            return false;
        }
    }
    
  public List<Profile> searchProfile(String username) {
    List<Profile> profiles = new ArrayList<>();
    String sql = "SELECT * FROM profiles WHERE fullName LIKE ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, "%" + username + "%");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            profiles.add(new Profile(
                rs.getInt("id"),
                rs.getInt("userID"),
                rs.getString("fullName"),
                rs.getString("email"),
                rs.getString("phoneNum")
            ));
        }
        System.out.println("✅ Found " + profiles.size() + " profiles matching '" + username + "'");
    } catch (SQLException e) {
        System.out.println("❌ Search failed: " + e.getMessage());
    }
    return profiles;
}
 
 // -------------------- UPDATE PROFILE --------------------
   public boolean updateProfile(int userID, String newFullName, String newEmail, String newPhoneNum) {
    String sql = "UPDATE profiles SET fullName = ?, email = ?, phoneNum = ? WHERE userID = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, newFullName);
        stmt.setString(2, newEmail);
        stmt.setString(3, newPhoneNum);
        stmt.setInt(4, userID);

        int rows = stmt.executeUpdate();
        if (rows > 0) {
            System.out.println("✅ Profile updated successfully for user ID " + userID);
            return true;
        } else {
            System.out.println("⚠ No profile found for user ID " + userID);
            return false;
        }
    } catch (SQLException e) {
        System.out.println("❌ Failed to update profile: " + e.getMessage());
        return false;
    }
}



}


