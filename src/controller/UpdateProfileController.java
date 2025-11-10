package controller;

import model.Profile;

public class UpdateProfileController {

    public boolean updateProfile(int userID, String newFullName, String newEmail, String newPhoneNum) {
        return Profile.updateProfile(userID, newFullName, newEmail, newPhoneNum);
    }
}



