package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Shortlist {

    private static final String URL = "jdbc:sqlite:csr.db";

    // ✅ Save request to shortlist (trigger count +1)
    public static boolean saveToShortlist(int csrID, int requestID) {
        String sql = "INSERT INTO shortlist(csrID, requestID) VALUES (?, ?)";
        String bump = "UPDATE requests SET shortlistCount = shortlistCount + 1 WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false);

            try (PreparedStatement ps1 = conn.prepareStatement(sql);
                 PreparedStatement ps2 = conn.prepareStatement(bump)) {

                ps1.setInt(1, csrID);
                ps1.setInt(2, requestID);
                ps1.executeUpdate();

                ps2.setInt(1, requestID);
                ps2.executeUpdate();

                conn.commit();
                System.out.println("✅ Saved & count updated");
                return true;
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("❌ saveToShortlist: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.out.println("❌ DB connection: " + e.getMessage());
            return false;
        }
    }

    // ✅ Remove / Unsavelist
    public static boolean removeShortlist(int csrID, int requestID) {
        String sql = "DELETE FROM shortlist WHERE csrID = ? AND requestID = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, csrID);
            ps.setInt(2, requestID);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ removeShortlist error: " + e.getMessage());
            return false;
        }
    }

    // ✅ View shortlist
    public static List<Request> viewShortlist(int csrID) {
        List<Request> list = new ArrayList<>();

        String sql = """
            SELECT r.*
            FROM requests r
            JOIN shortlist s ON r.id = s.requestID
            WHERE s.csrID = ?
            ORDER BY r.createdAt DESC
        """;

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, csrID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Request(
                    rs.getInt("id"),
                    rs.getString("requesterName"),
                    rs.getString("description"),
                    rs.getString("status"),
                    rs.getString("assignedCSR"),
                    rs.getString("category"),
                    rs.getInt("viewsCount"),
                    rs.getInt("shortlistCount")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ viewShortlist error: " + e.getMessage());
        }
        return list;
    }

    // ✅ View specific request in shortlist
    public static List<Request> viewShortlistByRequest(int requestID, int csrID) {
        List<Request> list = new ArrayList<>();

        String sql = """
            SELECT r.*
            FROM requests r
            JOIN shortlist s ON r.id = s.requestID
            WHERE s.csrID = ? AND r.id = ?
        """;

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, csrID);
            ps.setInt(2, requestID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Request(
                    rs.getInt("id"),
                    rs.getString("requesterName"),
                    rs.getString("description"),
                    rs.getString("status"),
                    rs.getString("assignedCSR"),
                    rs.getString("category"),
                    rs.getInt("viewsCount"),
                    rs.getInt("shortlistCount")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ viewShortlistByRequest error: " + e.getMessage());
        }

        return list;
    }
}









