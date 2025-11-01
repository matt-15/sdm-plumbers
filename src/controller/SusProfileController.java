package controller;

import database.ProfileDAO;

public class SusProfileController {
    private final ProfileDAO profileDAO = new ProfileDAO();

    public boolean susProfile(String userID) {
        try {
            int id = Integer.parseInt(userID);
            return profileDAO.suspendProfile(id);
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid userID: " + userID);
            return false;
        }
    }
}


