package project;

import project.controller.*;
import project.boundary.*;
import project.entity.User;
import project.repository.*;

public class Main {
    public static void main(String[] args) {
        // Instantiate repositories
        UserRepository userRepo = new InMemoryUserRepository();
        ProfileRepository profileRepo = new InMemoryProfileRepository();

        // Instantiate controllers
        UserController userController = new UserController(userRepo);
        ProfileController profileController = new ProfileController(profileRepo);
        AuthController authController = new AuthController(userRepo);
        RequestController requestController = new RequestController();
        CategoryController categoryController = new CategoryController();
        ReportController reportController = new ReportController();

        // Seed demo users
        userController.createUser(new User("admin", "admin123", "ADMIN"));
        userController.createUser(new User("csr1", "pass", "CSR"));
        userController.createUser(new User("pin1", "pass", "PIN"));
        userController.createUser(new User("plat1", "pass", "PLATFORM"));

        // Launch login GUI
        new LoginUI(authController,
                    userController,
                    profileController,
                    requestController,
                    categoryController,
                    reportController);
    }
}
