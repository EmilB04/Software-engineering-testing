package com.varsel.UsersTests;

import org.junit.jupiter.api.*;
import com.varsel.Users.UserRepository;
import com.varsel.Users.UserService;

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
    @DisplayName("Should register a user successfully")
    public void testRegisterUser_Success() throws IOException {
        boolean result = userService.registerUser("user@example.com", "password123");
        assertTrue(result, "Brukeren skal kunne registreres");
    }

    @Test
    @DisplayName("Should fail registration if user already exists")
    public void testRegisterUser_UserAlreadyExists() throws IOException {
        userService.registerUser("user@example.com", "password123");
        boolean result = userService.registerUser("user@example.com", "password456");
        assertFalse(result, "Brukeren finnes allerede, registrering skal feile");
    }

    @Test
    @DisplayName("Should login successfully with correct password")
    public void testLoginUser_Success() throws IOException {
        userService.registerUser("user@example.com", "password123");
        boolean result = userService.loginUser("user@example.com", "password123");
        assertTrue(result, "Innlogging skal lykkes med riktig passord");
    }

    @Test
    @DisplayName("Should fail login with incorrect password")
    public void testLoginUser_WrongPassword() throws IOException {
        userService.registerUser("user@example.com", "password123");
        boolean result = userService.loginUser("user@example.com", "wrongpassword");
        assertFalse(result, "Innlogging skal feile med feil passord");
    }

    @Test
    @DisplayName("Should fail login if user does not exist")
    public void testLoginUser_UserDoesNotExist() throws IOException {
        boolean result = userService.loginUser("nonexistent@example.com", "password123");
        assertFalse(result, "Innlogging skal feile for ikke-eksisterende bruker");
    }

    @Test
    @DisplayName("Should fail registration if email is empty")
    public void testRegisterUser_EmptyEmail() throws IOException {
        boolean result = userService.registerUser("", "password123");
        assertFalse(result, "Registrering skal feile med tom e-post");
    }

    @Test
    @DisplayName("Should fail registration if password is empty")
    public void testRegisterUser_EmptyPassword() throws IOException {
        boolean result = userService.registerUser("user@example.com", "");
        assertFalse(result, "Registrering skal feile med tomt passord");
    }

    @Test
    @DisplayName("Should fail login if email is empty")
    public void testLoginUser_EmptyEmail() throws IOException {
        userService.registerUser("user@example.com", "password123");
        boolean result = userService.loginUser("", "password123");
        assertFalse(result, "Innlogging skal feile med tom e-post");
    }

    @Test
    @DisplayName("Should fail login if password is empty")
    public void testLoginUser_EmptyPassword() throws IOException {
        userService.registerUser("user@example.com", "password123");
        boolean result = userService.loginUser("user@example.com", "");
        assertFalse(result, "Innlogging skal feile med tomt passord");
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

    @Test
    @DisplayName("Should store password as a hash instead of plain text")
    public void testPasswordHashing() throws IOException {
        String plainPassword = "password123";
        userService.registerUser("user@example.com", plainPassword);
        UserRepository userRepository = new UserRepository(TEST_FILE_PATH);
        String storedHashedPassword = userRepository.findUserByEmail("user@example.com").getHashedPassword();

        // Sjekker at passordet er lagret som en hash og ikke i klartekst
        assertNotEquals(plainPassword, storedHashedPassword, "Passordet skal være lagret som en hash og ikke i klartekst");
        assertTrue(storedHashedPassword.startsWith("$2a$"), "Passordhashen skal være i bcrypt-format");
    }
}
