package controller;

import model.Profile;
import java.util.List;

public class ViewProfileController {

    public List<Profile> viewProfiles() {
        return Profile.viewAllProfiles();
    }
}


