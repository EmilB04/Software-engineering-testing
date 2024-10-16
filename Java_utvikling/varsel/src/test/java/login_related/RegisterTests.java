/*package login_related;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import varsel.login_related.FileHandler;
import varsel.login_related.Register;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RegisterTests {

    @Test
    @DisplayName("Test user registration success")
    void testRegisterUserSuccess() {
        // Arrange
        HashMap<String, String> users = new HashMap<>();
        FileHandler mockFileHandler = Mockito.mock(FileHandler.class);
        Register register = new Register(users, mockFileHandler);

        // Simulerer brukerinput
        System.setIn(new ByteArrayInputStream("test@example.com\npassword123\n".getBytes()));

        // Act
        register.registerUser();

        // Assert
        assertTrue(users.containsKey("test@example.com"), "User should be registered.");
        verify(mockFileHandler, times(1)).saveUser("test@example.com", anyString());
    }

    @Test
    @DisplayName("Test duplicate email registration")
    void testDuplicateEmailRegistration() {
        // Arrange
        HashMap<String, String> users = new HashMap<>();
        FileHandler mockFileHandler = Mockito.mock(FileHandler.class);
        Register register = new Register(users, mockFileHandler);

        // Første registrering
        System.setIn(new ByteArrayInputStream("test@example.com\npassword123\n".getBytes()));
        register.registerUser();

        // Andre registrering med samme e-post
        System.setIn(new ByteArrayInputStream("test@example.com\npassword456\n".getBytes()));
        register.registerUser();

        // Assert
        assertEquals(1, users.size(), "There should be only one user registered.");
    }
}*/
