package controller;

import model.Profile;

public class CreateProfileController {

    public boolean createProfile(int userID, String fullName, String email, String phoneNum) {
        Profile profile = new Profile(userID, fullName, email, phoneNum);
        return profile.createProfile();
    }
}




