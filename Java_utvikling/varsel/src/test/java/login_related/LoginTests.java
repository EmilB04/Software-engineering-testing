package login_related;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mockito;
import varsel.login_related.Login;
import varsel.login_related.User;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginTests {

    @Test
    @DisplayName("Test login with valid credentials")
    void testLoginSuccess() {
        // Arrange
        HashMap<String, String> users = new HashMap<>();
        String email = "testuser@hiof.no";
        String password = "password";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        users.put(email, hashedPassword);

        Login login = new Login(users);

        // Act
        boolean loginSuccess = login.loginUser(email, password);

        // Assert
        assertTrue(loginSuccess, "Login should be successful");
    }

    @Test
    @DisplayName("Test login with invalid password")
    void testLoginFailInvalidPassword() {
        // Arrange
        HashMap<String, String> users = new HashMap<>();
        String email = "testuser@hiof.no";
        String password = "password";
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        users.put(email, hashedPassword);

        Login login = new Login(users);

        // Act
        boolean loginFail = login.loginUser(email, "wrongpassword");

        // Assert
        assertFalse(loginFail, "Login should fail due to invalid password");
    }

    @Test
    @DisplayName("Test login with non-existent user")
    void testLoginFailNonExistentUser() {
        // Arrange
        HashMap<String, String> users = new HashMap<>();
        Login login = new Login(users);

        // Act
        boolean loginFail = login.loginUser("nonexistent@hiof.no", "password");

        // Assert
        assertFalse(loginFail, "Login should fail due to non-existent user");
    }
}
