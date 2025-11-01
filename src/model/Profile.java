package model;

public class Profile {
    private int id;
    private int userID;
    private String fullName;
    private String email;
    private String phoneNum;

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

    // ------------------ Update Profile ------------------
    public boolean updateProfile(String userID, String newFullName, String newEmail, String newPhoneNum) {
        System.out.println("Updating profile for user ID: " + userID);
        System.out.println("New Full Name: " + newFullName);
        System.out.println("New Email: " + newEmail);
        System.out.println("New Phone Number: " + newPhoneNum);
        return true; 
    }
    
    // ------------------ Suspend Profile ------------------
public boolean susProfile(String userID) {
    System.out.println("Suspending profile for user ID: " + userID);
    return true; 
    }

}
