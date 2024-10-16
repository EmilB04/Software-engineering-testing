package login_related;
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
        UserRepo userRepo = Mockito.mock(UserRepo.class);                           // Simulere en database med brukere
        User testUser1 = new User(1, "testuser@hiof.no", "password");    // Oppretter en testbruker

        when(userRepo.findUserByEmail("testuser@hiof.no")).thenReturn(testUser1);                           // Når findUserByEmail(1) kalles, returner testUser1 

        // Act
        LoginAuth loginAuth = new LoginAuth(userRepo);                                          // Bruker UserRepo for å autentisere brukere i LoginAuth klassen
        boolean loginSuccess = loginAuth.login("testuser@hiof.no", "password");                 // Prøver å logge inn med testUser1

        // Assert
        assertTrue(loginSuccess, "Login should be successful");                         // Forventer at loginSuccess er true

    }

    @Test
    @DisplayName("Test login with invalid credentials")
    void testLoginFail() {
        // Arrange
        UserRepo userRepo = Mockito.mock(UserRepo.class);                           
        User testUser1 = new User(1, "testuser@hiof.no", "password");

        when(userRepo.findUserByEmail("testuser@hiof.no")).thenReturn(testUser1);                           

        // Act
        LoginAuth loginAuth = new LoginAuth(userRepo);                                          
        boolean loginFail = loginAuth.login("testuser@hiof.no", "wrongpassword");
        // Assert
        assertFalse(loginFail, "Login should fail");                                    
    }

    @Test
    @DisplayName("Test login with invalid user_id")
    void testLoginFail_InvalidUser() {
        // Arrange
        UserRepo userRepo = Mockito.mock(UserRepo.class);                           
        User testUser1 = new User(1, "testuser@hiof.no", "password");

        when(userRepo.findUserByEmail("testuser@hiof.no")).thenReturn(testUser1);                           

        // Act
        LoginAuth loginAuth = new LoginAuth(userRepo);                                          
        boolean loginFail = loginAuth.login("wronguser@hiof.no", "password");                    

        // Assert
        assertFalse(loginFail, "Login should fail");                                    
    }
}
