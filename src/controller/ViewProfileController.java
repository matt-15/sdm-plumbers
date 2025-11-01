package controller;

import database.ProfileDAO;
import model.Profile;
import java.util.List;

public class ViewProfileController {
    private final ProfileDAO dao = new ProfileDAO();

    public List<Profile> viewAllProfiles() {
        return dao.getAllProfiles();
    }
}

