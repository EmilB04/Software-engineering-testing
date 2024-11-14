package com.varsel.Users;

// Path: varsel/src/main/java/varsel/login_related/User.java
// Use-case: User.java is a class that represents a user in the system.
// Attributes: email, and password.

/**
 * Denne klassen representerer en bruker med e-post og kryptert passord.
 */
public class User {
    private final String email;
    private final String hashedPassword;

    public User(String email, String hashedPassword) {
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
