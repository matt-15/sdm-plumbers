package project.controller;
import project.repository.ProfileRepository;
import project.entity.Profile;

public class ProfileController {
    private ProfileRepository repo;

    public ProfileController(ProfileRepository repo) {
        this.repo = repo;
    }

    public void createProfile(Profile profile) {
        repo.save(profile);
        System.out.println("Profile created for: " + profile.getUsername());
    }

    public Profile viewProfile(String username) {
        return repo.findByUsername(username);
    }

    public void updateProfile(Profile profile) {
        repo.save(profile);
        System.out.println("Profile updated: " + profile.getUsername());
    }

    public void deleteProfile(String username) {
        repo.delete(username);
        System.out.println("Profile deleted: " + username);
    }
}
