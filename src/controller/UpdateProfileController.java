package controller;

import database.ProfileDAO;

public class UpdateProfileController {

    private final ProfileDAO profileDAO = new ProfileDAO();

    public boolean updateProfile(int userID, String newFullName, String newEmail, String newPhoneNum) {
        return profileDAO.updateProfile(userID, newFullName, newEmail, newPhoneNum);
    }
}



