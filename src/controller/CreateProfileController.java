package controller;

import database.ProfileDAO;
import model.Profile;

public class CreateProfileController {
    private final ProfileDAO profileDAO = new ProfileDAO();

    public boolean createProfile(int userID, String fullName, String email, String phoneNum) {
        if (fullName == null || fullName.isEmpty() ||
            email == null || email.isEmpty() ||
            phoneNum == null || phoneNum.isEmpty()) {
            System.out.println("⚠ Please fill in all fields.");
            return false;
        }

        Profile profile = new Profile(userID, fullName, email, phoneNum);
        return profileDAO.createProfile(profile);
    }
}


