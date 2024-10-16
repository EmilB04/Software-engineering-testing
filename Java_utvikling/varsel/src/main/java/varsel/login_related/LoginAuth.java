package varsel.login_related;

// Path: varsel/src/main/java/varsel/login_related/LoginAuth.java
// Use-case: LoginAuth.java is a class that provides methods to authenticate a user.

import java.util.Objects;

public class LoginAuth {
    private UserRepo userRepo;

    public LoginAuth(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean login(String email, String password) { // Method to authenticate a user
        User user = userRepo.findUserByEmail(email);
        if (user != null && user.getPassword().equals(password) && Objects.equals(user.getEmail(), email)) {
            return true;
        }
        return false;
    }
}
