package controller;

import model.Profile;
import java.util.List;

public class SearchProfileController {

    public List<Profile> searchProfile(String name) {
        return Profile.searchProfile(name);
    }
}



