package com.varsel.UsersTests;

import com.varsel.Users.UserService;
import com.varsel.Users.UserRepository;
import com.varsel.Users.User;
import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTests {

    private UserService userService;
    private static final String TEST_FILE_PATH = "test_users.txt";

    @BeforeEach
    public void setUp() throws IOException {
        UserRepository userRepository = new UserRepository(TEST_FILE_PATH);
        userService = new UserService(userRepository);
    }

    @AfterEach
    public void tearDown() {
        new File(TEST_FILE_PATH).delete();  // Slette testfilen etter hver test
    }

    @Test
    @DisplayName("Should register user successfully")
    public void testRegisterUser_Success() throws IOException {
        boolean result = userService.registerUser("user@example.com", "password123");
        assertTrue(result, "Brukeren skal registreres");
    }

    @Test
    @DisplayName("Should not register user with empty email")
    public void testRegisterUser_EmptyEmail() throws IOException {
        boolean result = userService.registerUser("", "password123");
        assertFalse(result, "Registrering skal feile med tom e-post");
    }

    @Test
    @DisplayName("Should not register user with empty password")
    public void testRegisterUser_EmptyPassword() throws IOException {
        boolean result = userService.registerUser("user@example.com", "");
        assertFalse(result, "Registrering skal feile med tomt passord");
    }

    @Test
    @DisplayName("Should not register user if user already exists")
    public void testRegisterUser_UserAlreadyExists() throws IOException {
        userService.registerUser("user@example.com", "password123");
        boolean result = userService.registerUser("user@example.com", "password456");
        assertFalse(result, "Bruker finnes allerede");
    }

    @Test
    @DisplayName("Should login user successfully with correct credentials")
    public void testLoginUser_Success() throws IOException {
        userService.registerUser("user@example.com", "password123");
        boolean result = userService.loginUser("user@example.com", "password123");
        assertTrue(result, "Innlogging skal lykkes med korrekt passord");
    }

    @Test
    @DisplayName("Should not login with incorrect password")
    public void testLoginUser_WrongPassword() throws IOException {
        userService.registerUser("user@example.com", "password123");
        boolean result = userService.loginUser("user@example.com", "wrongpassword");
        assertFalse(result, "Innlogging skal feile med feil passord");
    }

    @Test
    @DisplayName("Should not login with non-existing user")
    public void testLoginUser_UserDoesNotExist() throws IOException {
        boolean result = userService.loginUser("nonexistent@example.com", "password123");
        assertFalse(result, "Innlogging skal feile med ikke-eksisterende bruker");
    }

    @Test
    @DisplayName("Should register user with special characters in email and password")
    public void testRegisterUser_SpecialCharacters() throws IOException {
        boolean result = userService.registerUser("user+special@example.com", "p@$$w0rd!");
        assertTrue(result, "Brukeren skal kunne registreres med spesialtegn i e-post og passord");
    }

    @Test
    @DisplayName("Should login successfully with special characters in email and password")
    public void testLoginUser_SpecialCharacters() throws IOException {
        userService.registerUser("user+special@example.com", "p@$$w0rd!");
        boolean result = userService.loginUser("user+special@example.com", "p@$$w0rd!");
        assertTrue(result, "Innlogging skal lykkes med spesialtegn i e-post og passord");
    }

}
