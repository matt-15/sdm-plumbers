package controller;

import database.ProfileDAO;
import model.Profile;
import java.util.List;

public class SearchProfileController {
    private final ProfileDAO dao = new ProfileDAO();

    public List<Profile> searchProfile(String username) {
        return dao.searchProfile(username);
    }
}



