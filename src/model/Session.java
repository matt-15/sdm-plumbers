package model;

public class Session {
    private static int loggedUserID;
    private static String loggedRole;
    private static String loggedUsername;

    public static void setUser(int id, String role, String username) {
        loggedUserID = id;
        loggedRole = role;
        loggedUsername = username;
    }

    public static int getUserID() { return loggedUserID; }
    public static String getUserRole() { return loggedRole; }
    public static String getUsername() { return loggedUsername; }
}

