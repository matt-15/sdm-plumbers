package project.repository;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import project.entity.Profile;
import project.repository.UserRepository;

public class InMemoryProfileRepository implements ProfileRepository {
    private Map<String, Profile> profiles = new HashMap<>();

    @Override
    public void save(Profile profile) {
        profiles.put(profile.getUsername(), profile);
    }

    @Override
    public Profile findByUsername(String username) {
        return profiles.get(username);
    }

    @Override
    public void delete(String username) {
        profiles.remove(username);
    }

    @Override
    public Collection<Profile> findAll() {
        return profiles.values();
    }
}