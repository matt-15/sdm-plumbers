package project.entity;
public class User {
    private String username;
    private String passwordHash;
    private String role; // ADMIN, CSR, PIN, PLATFORM
    private boolean active;

    public User(String username, String passwordHash, String role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.active = true;
    }

    
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }
    
    public boolean isActive() { return active; }

    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setRole(String role) { this.role = role; }
    
    public void suspend() {
        this.active = false;
    }
    
    public void activate() {
        this.active = true;
    }
    public boolean isAdmin() { return "ADMIN".equalsIgnoreCase(role); }
    public boolean isCSR() { return "CSR".equalsIgnoreCase(role); }
    public boolean isPIN() { return "PIN".equalsIgnoreCase(role); }
    public boolean isPlatformManager() { return "PLATFORM".equalsIgnoreCase(role); }
}