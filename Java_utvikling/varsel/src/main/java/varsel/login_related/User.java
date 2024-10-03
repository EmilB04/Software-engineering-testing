package varsel.login_related;

// Path: varsel/src/main/java/varsel/login_related/User.java
// Use-case: User.java is a class that represents a user in the system.
// Attributes: user_id, email, and password.

public class User {
    private double user_id;
    private String email;
    private String password;

    public User(double user_id, String email, String password) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
    }

    public double getUser_id() {
        return user_id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}