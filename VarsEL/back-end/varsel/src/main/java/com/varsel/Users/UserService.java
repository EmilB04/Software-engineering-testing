package varsel.login_related;

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

    public boolean registerUser(String email, String password) throws IOException {
        if (userRepository.findUserByEmail(email) != null) {
            return false;  // Bruker finnes allerede
        }
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        userRepository.saveUser(new User(email, hashedPassword));
        return true;
    }

    public boolean loginUser(String email, String password) throws IOException {
        User user = userRepository.findUserByEmail(email);
        return user != null && BCrypt.checkpw(password, user.getHashedPassword());
    }
}

