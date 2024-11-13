package login_related;

import org.junit.jupiter.api.*;
import varsel.login_related.UserRepository;
import varsel.login_related.UserService;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;
    private static final String TEST_FILE_PATH = "test_users.txt";

    @BeforeEach
    public void setUp() throws IOException {
        File file = new File(TEST_FILE_PATH);
        file.createNewFile();
        UserRepository userRepository = new UserRepository(TEST_FILE_PATH);
        userService = new UserService(userRepository);
    }

    @AfterEach
    public void tearDown() {
        new File(TEST_FILE_PATH).delete();
    }

    @Test
    public void testRegisterUser_Success() throws IOException {
        boolean result = userService.registerUser("user@example.com", "password123");
        assertTrue(result, "Brukeren skal kunne registreres");
    }

    @Test
    public void testRegisterUser_UserAlreadyExists() throws IOException {
        userService.registerUser("user@example.com", "password123");
        boolean result = userService.registerUser("user@example.com", "password456");
        assertFalse(result, "Brukeren finnes allerede, registrering skal feile");
    }

    @Test
    public void testLoginUser_Success() throws IOException {
        userService.registerUser("user@example.com", "password123");
        boolean result = userService.loginUser("user@example.com", "password123");
        assertTrue(result, "Innlogging skal lykkes med riktig passord");
    }

    @Test
    public void testLoginUser_WrongPassword() throws IOException {
        userService.registerUser("user@example.com", "password123");
        boolean result = userService.loginUser("user@example.com", "wrongpassword");
        assertFalse(result, "Innlogging skal feile med feil passord");
    }

    @Test
    public void testLoginUser_UserDoesNotExist() throws IOException {
        boolean result = userService.loginUser("nonexistent@example.com", "password123");
        assertFalse(result, "Innlogging skal feile for ikke-eksisterende bruker");
    }
}

