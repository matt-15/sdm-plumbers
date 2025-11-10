package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Request {

    private int id;
    private String requesterName;
    private String description;
    private String status;
    private String assignedCSR;
    private String category;
    private int viewsCount;
    private int shortlistCount;

    private static final String URL = "jdbc:sqlite:csr.db";

    // ----------------------------------------------------
    // Constructor
    // ----------------------------------------------------
    public Request(int id,
                   String requesterName,
                   String description,
                   String status,
                   String assignedCSR,
                   String category,
                   int viewsCount,
                   int shortlistCount) {

        this.id = id;
        this.requesterName = requesterName;
        this.description = description;
        this.status = status;
        this.assignedCSR = assignedCSR;
        this.category = category;
        this.viewsCount = viewsCount;
        this.shortlistCount = shortlistCount;
    }

    // ----------------------------------------------------
    // Getters (UI compatibility)
    // ----------------------------------------------------
    public int getId() { return id; }
    public String getUsername() { return requesterName; }
    public String getRequesterName() { return requesterName; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getAssignedCSR() { return assignedCSR; }
    public String getCategory() { return category; }
    public int getViewsCount() { return viewsCount; }
    public int getShortlistCount() { return shortlistCount; }

    // ----------------------------------------------------
    // Search requests with optional filters
    // ----------------------------------------------------
    public static List<Request> searchRequests(String requesterName, String description, String status) {
        List<Request> list = new ArrayList<>();

        String sql = """
            SELECT * FROM requests
            WHERE requesterName LIKE ? AND description LIKE ? AND status LIKE ?
            ORDER BY createdAt DESC
        """;

        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, "%" + (requesterName == null ? "" : requesterName) + "%");
            ps.setString(2, "%" + (description   == null ? "" : description)   + "%");
            ps.setString(3, "%" + (status        == null ? "" : status)        + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));

        } catch (SQLException e) {
            System.out.println("❌ searchRequests: " + e.getMessage());
        }
        return list;
    }

    // ----------------------------------------------------
    // Search by date range
    // ----------------------------------------------------
    public static List<Request> searchByDate(String dateFrom, String dateTo) {
        List<Request> list = new ArrayList<>();

        String sql = """
            SELECT * FROM requests
            WHERE date(createdAt) BETWEEN date(?) AND date(?)
            ORDER BY createdAt DESC
        """;

        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, dateFrom);
            ps.setString(2, dateTo);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));

        } catch (SQLException e) {
            System.out.println("❌ searchByDate: " + e.getMessage());
        }
        return list;
    }

    // ----------------------------------------------------
    // Search completed request history (old usage)
    // ----------------------------------------------------
    public static List<Request> searchHistory(String requestID) {
        List<Request> list = new ArrayList<>();

        String sql = "SELECT * FROM requests WHERE id = ? AND status = 'Completed'";

        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(requestID));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));

        } catch (SQLException e) {
            System.out.println("❌ searchHistory: " + e.getMessage());
        }
        return list;
    }

    // ----------------------------------------------------
    // ✅ NEW: View ALL Completed requests
    // ----------------------------------------------------
    public static List<Request> searchHistoryAll() {
        List<Request> list = new ArrayList<>();
        String sql = "SELECT * FROM requests WHERE status = 'Completed' ORDER BY createdAt DESC";

        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));

        } catch (SQLException e) {
            System.out.println("❌ searchHistoryAll: " + e.getMessage());
        }
        return list;
    }

    // ----------------------------------------------------
    // ✅ NEW: View Completed request by ID
    // ----------------------------------------------------
    public static List<Request> searchHistoryById(int id) {
        List<Request> list = new ArrayList<>();
        String sql = "SELECT * FROM requests WHERE id = ? AND status = 'Completed'";

        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));

        } catch (SQLException e) {
            System.out.println("❌ searchHistoryById: " + e.getMessage());
        }
        return list;
    }

    // ----------------------------------------------------
    // View CSR shortlist
    // ----------------------------------------------------
    public static List<Request> searchShortlist(int csrID) {
        List<Request> list = new ArrayList<>();

        String sql = """
            SELECT r.* FROM requests r
            JOIN shortlist s ON r.id = s.requestID
            WHERE s.csrID = ?
            ORDER BY s.createdAt DESC
        """;

        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, csrID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));

        } catch (SQLException e) {
            System.out.println("❌ searchShortlist: " + e.getMessage());
        }
        return list;
    }

    // ----------------------------------------------------
    // Search shortlist by request ID
    // ----------------------------------------------------
    public static List<Request> searchShortlistByRequest(int requestID, int csrID) {
        List<Request> list = new ArrayList<>();

        String sql = """
            SELECT r.* FROM requests r
            JOIN shortlist s ON r.id = s.requestID
            WHERE s.csrID = ? AND s.requestID = ?
        """;

        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, csrID);
            ps.setInt(2, requestID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(map(rs));

        } catch (SQLException e) {
            System.out.println("❌ searchShortlistByRequest: " + e.getMessage());
        }
        return list;
    }

    // ----------------------------------------------------
    // Increment view count
    // ----------------------------------------------------
    public static void incrementViews(int requestID) {
        String sql = "UPDATE requests SET viewsCount = viewsCount + 1 WHERE id = ?";

        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, requestID);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ incrementViews: " + e.getMessage());
        }
    }

    // ----------------------------------------------------
    // Shortlist + counter
    // ----------------------------------------------------
    public static boolean saveToShortlist(int csrID, int requestID) {
        String insert = "INSERT INTO shortlist(csrID, requestID) VALUES(?,?)";
        String bump   = "UPDATE requests SET shortlistCount = shortlistCount + 1 WHERE id = ?";

        try (Connection c = DriverManager.getConnection(URL)) {
            c.setAutoCommit(false);

            try (PreparedStatement ps1 = c.prepareStatement(insert);
                 PreparedStatement ps2 = c.prepareStatement(bump)) {

                ps1.setInt(1, csrID);
                ps1.setInt(2, requestID);
                ps1.executeUpdate();

                ps2.setInt(1, requestID);
                ps2.executeUpdate();

                c.commit();
                return true;

            } catch (SQLException inner) {
                c.rollback();
                System.out.println("❌ saveToShortlist: " + inner.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("❌ saveToShortlist(conn): " + e.getMessage());
        }
        return false;
    }

    // ----------------------------------------------------
    // DB row -> Request object
    // ----------------------------------------------------
    private static Request map(ResultSet rs) throws SQLException {
        return new Request(
                rs.getInt("id"),
                rs.getString("requesterName"),
                rs.getString("description"),
                rs.getString("status"),
                rs.getString("assignedCSR"),
                rs.getString("category"),
                rs.getInt("viewsCount"),
                rs.getInt("shortlistCount")
        );
    }

    // ----------------------------------------------------
    // Admin Summary Report
    // ----------------------------------------------------
    public record Summary(
            int total, int pending, int approved, int completed,
            String topCategory, int mostViewedId, int mostViewedCount) {}

    public static Summary adminSummary() {
        String qTotal      = "SELECT COUNT(*) FROM requests";
        String qPending    = "SELECT COUNT(*) FROM requests WHERE status='Pending'";
        String qApproved   = "SELECT COUNT(*) FROM requests WHERE status='Approved'";
        String qCompleted  = "SELECT COUNT(*) FROM requests WHERE status='Completed'";
        String qTopCat     = "SELECT category FROM requests GROUP BY category ORDER BY COUNT(*) DESC LIMIT 1";
        String qMostViewed = "SELECT id, viewsCount FROM requests ORDER BY viewsCount DESC LIMIT 1";

        try (Connection c = DriverManager.getConnection(URL)) {

            int total = scalarInt(c, qTotal);
            int pend  = scalarInt(c, qPending);
            int appr  = scalarInt(c, qApproved);
            int comp  = scalarInt(c, qCompleted);

            String topCat = scalarStr(c, qTopCat);
            int mvId = 0, mvCnt = 0;

            try (Statement st = c.createStatement();
                 ResultSet rs = st.executeQuery(qMostViewed)) {
                if (rs.next()) { mvId = rs.getInt(1); mvCnt = rs.getInt(2); }
            }

            return new Summary(total, pend, appr, comp, topCat, mvId, mvCnt);

        } catch (SQLException e) {
            System.out.println("❌ adminSummary: " + e.getMessage());
            return new Summary(0,0,0,0,"-",0,0);
        }
    }

    private static int scalarInt(Connection c, String sql) throws SQLException {
        try (Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    private static String scalarStr(Connection c, String sql) throws SQLException {
        try (Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            return rs.next() ? rs.getString(1) : "-";
        }
    }
}







