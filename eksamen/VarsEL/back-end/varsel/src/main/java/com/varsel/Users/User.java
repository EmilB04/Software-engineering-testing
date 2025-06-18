package com.varsel.Users;

/**
 * Denne klassen representerer en bruker med e-post og kryptert passord.
 */
public class User {
    private final String email;
    private final String hashedPassword;

    /**
     * Konstruktør som initialiserer en bruker med e-post og kryptert passord.
     *
     * @param email          E-postadresse til brukeren
     * @param hashedPassword Hashet versjon av brukerens passord
     */
    public User(String email, String hashedPassword) {
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    /**
     * Henter e-postadressen til brukeren.
     *
     * @return e-postadressen som en String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Henter den krypterte versjonen av brukerens passord.
     *
     * @return Hashet passord som en String
     */
    public String getHashedPassword() {
        return hashedPassword;
    }
}
