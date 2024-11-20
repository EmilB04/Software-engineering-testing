package com.varsel.UsersTests;

import com.varsel.Users.User;
import com.varsel.Users.UserRepository;
import com.varsel.Users.UserService;
import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTests {

    private UserRepository userRepository;
    private static final String TEST_FILE_PATH = "test_users.txt";

    @BeforeEach
    public void setUp() throws IOException {
        userRepository = new UserRepository(TEST_FILE_PATH);
    }

    @AfterEach
    public void tearDown() {
        new File(TEST_FILE_PATH).delete();  // Slette testfilen etter hver test
    }

    @Test
    @DisplayName("Should save user to file")
    public void testSaveUser() throws IOException {
        User user = new User("user@example.com", "hashedPassword123");
        userRepository.saveUser(user);

        User fetchedUser = userRepository.findUserByEmail("user@example.com");
        assertNotNull(fetchedUser, "Brukeren skal finnes i filen");
        assertEquals("user@example.com", fetchedUser.getEmail(), "E-postadresse skal matche");
    }

    @Test
    @DisplayName("Should return null if user is not found")
    public void testFindUser_UserNotFound() throws IOException {
        User user = userRepository.findUserByEmail("nonexistent@example.com");
        assertNull(user, "Brukeren skal ikke finnes");
    }

    @Test
    @DisplayName("Should create file if it does not exist")
    public void testFileCreation() throws IOException {
        File file = new File(TEST_FILE_PATH);
        assertTrue(file.exists(), "Filen skal eksistere etter at den er opprettet");
    }

    @Test
    @DisplayName("Should store password as a hash instead of plain text")
    public void testPasswordHashing() throws IOException {
        // Opprett UserRepository
        UserRepository userRepository = new UserRepository(TEST_FILE_PATH);

        // Opprett UserService med det opprettede UserRepository
        UserService userService = new UserService(userRepository);

        // Register brukeren med plain password
        String plainPassword = "password123";
        userService.registerUser("user@example.com", plainPassword);

        // Hent det lagrede passordet fra UserRepository
        String storedHashedPassword = userRepository.findUserByEmail("user@example.com").getHashedPassword();

        // Valider at passordet er lagret som en hash og ikke i klartekst
        assertNotEquals(plainPassword, storedHashedPassword, "Passordet skal være lagret som en hash og ikke i klartekst");
        assertTrue(storedHashedPassword.startsWith("$2a$"), "Passordhashen skal være i bcrypt-format");
    }


}
