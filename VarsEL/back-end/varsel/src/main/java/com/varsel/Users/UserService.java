package com.varsel.Users;

import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;

/**
 * Denne klassen håndterer brukerregistrering og innlogging.
 */
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Registrerer en ny bruker hvis e-post og passord er gyldige og brukeren ikke allerede eksisterer.
     *
     * @param email    Brukerens e-postadresse
     * @param password Brukerens passord
     * @return true hvis registrering er vellykket, false ellers
     * @throws IOException hvis det oppstår en feil ved tilgang til databasen
     */
    public boolean registerUser(String email, String password) throws IOException {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return false;  // Returnerer false hvis e-post eller passord er tomt
        }

        if (userRepository.findUserByEmail(email) != null) {
            return false;  // Bruker finnes allerede
        }

        // Fortsetter med registrering dersom email og passord er gyldige
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        userRepository.saveUser(new User(email, hashedPassword));
        return true;
    }

    /**
     * Logger inn en bruker hvis e-post og passord er korrekte.
     *
     * @param email    Brukerens e-postadresse
     * @param password Brukerens passord
     * @return true hvis innlogging er vellykket, false ellers
     * @throws IOException hvis det oppstår en feil ved tilgang til databasen
     */
    public boolean loginUser(String email, String password) throws IOException {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return false;  // Innlogging skal feile hvis e-post eller passord er tomt
        }

        User user = userRepository.findUserByEmail(email);
        return user != null && BCrypt.checkpw(password, user.getHashedPassword());
    }
}
