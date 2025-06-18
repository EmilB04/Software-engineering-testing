package com.varsel.UsersTests;

import com.varsel.Users.User;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserTests {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("user@example.com", "hashedPassword123");
    }

    @Test
    @DisplayName("Should return correct email")
    public void testGetEmail() {
        assertEquals("user@example.com", user.getEmail(), "E-postadressen skal være korrekt");
    }

    @Test
    @DisplayName("Should return correct hashed password")
    public void testGetHashedPassword() {
        assertEquals("hashedPassword123", user.getHashedPassword(), "Hashet passord skal være korrekt");
    }
}
