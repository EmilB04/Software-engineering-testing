import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import varsel.login_related.User;
import varsel.login_related.LoginAuth;
import varsel.login_related.UserRepo;

import static org.mockito.Mockito.*;


public class LoginAuthTests {
    @Test
    @DisplayName("Test login with valid credentials")
    void testLoginSuccess() {
        // Arrange
        UserRepo userRepo = Mockito.mock(UserRepo.class);
        User testUser1 = new User(1, "testuser@hiof.no", "password");

        when(userRepo.findUserById(1)).thenReturn(testUser1);

        // Act
        LoginAuth loginAuth = new LoginAuth(userRepo);
        boolean loginSuccess = loginAuth.login(1, "password");

        // Assert
        assertTrue(loginSuccess, "Login should be successful");

    }
}
