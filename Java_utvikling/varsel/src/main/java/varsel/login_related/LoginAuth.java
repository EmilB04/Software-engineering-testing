package varsel.login_related;

// Path: varsel/src/main/java/varsel/login_related/LoginAuth.java
// Use-case: LoginAuth.java is a class that provides methods to authenticate a user.

public class LoginAuth {
    private UserRepo userRepo;

    public LoginAuth(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean login(double user_id, String password) { // Method to authenticate a user
        User user = userRepo.findUserById(user_id); 
        if (user != null && user.getPassword().equals(password)) { 
            return true;
        }
        return false;
    }
}
