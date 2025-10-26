package project.repository;

import project.entity.Profile;
import java.util.Collection;

public interface ProfileRepository {
    void save(Profile profile);
    Profile findByUsername(String username);
    void delete(String username);
    Collection<Profile> findAll();
}

