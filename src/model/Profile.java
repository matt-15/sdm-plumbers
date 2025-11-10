package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class Profile {

    private int id;
    private int userID;
    private String fullName;
    private String email;
    private String phoneNum;

    private static final String URL = "jdbc:sqlite:csr.db";

    public Profile() {}

    public Profile(int userID, String fullName, String email, String phoneNum) {
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public Profile(int id, int userID, String fullName, String email, String phoneNum) {
        this.id = id;
        this.userID = userID;
        this.fullName = fullName;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    // ====== Getters and Setters ======
    public int getId() { return id; }
    public int getUserID() { return userID; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhoneNum() { return phoneNum; }

    public void setId(int id) { this.id = id; }
    public void setUserID(int userID) { this.userID = userID; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    // ============================================================
    // Function: Retrieve all profiles from database
    // ============================================================
    public static List<Profile> getAllProfiles() {
        List<Profile> profiles = new ArrayList<>();
        String sql = "SELECT * FROM profiles";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement(sql);
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

            System.out.println("✅ Loaded " + profiles.size() + " profiles from database.");
        } catch (SQLException e) {
            System.out.println("❌ Failed to load profiles: " + e.getMessage());
        }

        return profiles;
    }
    // ============================================================
// Function: Update Profile by userID
// ============================================================
public static boolean updateProfile(int userID, String newFullName, String newEmail, String newPhoneNum) {
    String sql = "UPDATE profiles SET fullName = ?, email = ?, phoneNum = ? WHERE userID = ?";

    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, newFullName);
        stmt.setString(2, newEmail);
        stmt.setString(3, newPhoneNum);
        stmt.setInt(4, userID);

        int rows = stmt.executeUpdate();

        if (rows > 0) {
            System.out.println("✅ Profile updated for userID = " + userID);
            return true;
        } else {
            System.out.println("⚠ No profile found for userID = " + userID);
            return false;
        }

    } catch (SQLException e) {
        System.out.println("❌ Profile Update failed: " + e.getMessage());
        return false;
    }
}

// ============================================================
// Function: Suspend (delete) profile by userID
// ============================================================
public static boolean susProfile(int userID) {
    String sql = "DELETE FROM profiles WHERE userID = ?";

    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, userID);
        int rows = stmt.executeUpdate();

        if (rows > 0) {
            System.out.println("🗑 Profile suspended (deleted) for userID = " + userID);
            return true;
        } else {
            System.out.println("⚠ No profile found for userID = " + userID);
            return false;
        }

    } catch (SQLException e) {
        System.out.println("❌ Failed to suspend profile: " + e.getMessage());
        return false;
    }
}
// ============================================================
// Function: Search profiles by full name (LIKE query)
// ============================================================
public static List<Profile> searchProfile(String name) {
    List<Profile> profiles = new ArrayList<>();
    String sql = "SELECT * FROM profiles WHERE fullName LIKE ?";

    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, "%" + name + "%");
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

        System.out.println("✅ Found " + profiles.size() + " profiles matching: " + name);

    } catch (SQLException e) {
        System.out.println("❌ Failed to search profiles: " + e.getMessage());
    }
    return profiles;
}
// ============================================================
// Function: View all profiles from database
// ============================================================
public static List<Profile> viewAllProfiles() {
    List<Profile> profiles = new ArrayList<>();
    String sql = "SELECT * FROM profiles";

    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement stmt = conn.prepareStatement(sql);
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
        System.out.println("✅ Loaded " + profiles.size() + " profiles.");

    } catch (SQLException e) {
        System.out.println("❌ Failed to load profiles: " + e.getMessage());
    }
    return profiles;
}
// ============================================================
// Function: Create new profile in DB
// ============================================================
public boolean createProfile() {
    String sql = "INSERT INTO profiles (userID, fullName, email, phoneNum) VALUES (?, ?, ?, ?)";

    try (Connection conn = DriverManager.getConnection(URL);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, this.userID);
        stmt.setString(2, this.fullName);
        stmt.setString(3, this.email);
        stmt.setString(4, this.phoneNum);

        stmt.executeUpdate();
        System.out.println("✅ Profile created for userID = " + this.userID);
        return true;

    } catch (SQLException e) {
        System.out.println("❌ Failed to create profile: " + e.getMessage());
        return false;
    }
}



}


