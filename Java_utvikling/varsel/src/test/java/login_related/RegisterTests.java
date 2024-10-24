package login_related;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mockito;
import varsel.login_related.FileHandler;
import varsel.login_related.Register;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterTest {

    private Register register;
    private HashMap<String, String> users;
    private FileHandler mockFileHandler;

    @BeforeEach
    public void setUp() {
        users = new HashMap<>();
        mockFileHandler = mock(FileHandler.class);
    }

    @Test
    public void testRegisterNewUser() {
        String email = "test@example.com";
        String password = "password123";

        // Simulerer input via ByteArrayInputStream som Scanner kan lese fra
        ByteArrayInputStream inputStream = new ByteArrayInputStream((email + "\n" + password).getBytes());
        Scanner scanner = new Scanner(inputStream);

        // Oppretter register-objekt med mocket FileHandler og Scanner
        register = new Register(users, mockFileHandler, scanner);

        // Sjekk at brukeren ikke eksisterer fra før
        assertFalse(users.containsKey(email));

        // Kall registerUser
        register.registerUser();

        // Sjekk at brukeren er lagt til i users
        assertTrue(users.containsKey(email));

        // Sjekk at passordet er hash'et riktig
        assertTrue(BCrypt.checkpw(password, users.get(email)));

        // Sjekk at FileHandler sin saveUser ble kalt med riktig email og hash'et passord
        verify(mockFileHandler, times(1)).saveUser(eq(email), anyString());
    }

    @Test
    public void testRegisterExistingUser() {
        String email = "existing@example.com";
        String hashedPassword = BCrypt.hashpw("existingPassword", BCrypt.gensalt());
        users.put(email, hashedPassword);

        // Simulerer input for en eksisterende bruker
        ByteArrayInputStream inputStream = new ByteArrayInputStream((email + "\nnewpassword").getBytes());
        Scanner scanner = new Scanner(inputStream);

        register = new Register(users, mockFileHandler, scanner);

        // Kall registerUser
        register.registerUser();

        // Sjekk at brukeren ikke er registrert på nytt
        assertEquals(1, users.size());
        assertEquals(hashedPassword, users.get(email));

        // Sjekk at FileHandler sin saveUser ikke ble kalt
        verify(mockFileHandler, never()).saveUser(anyString(), anyString());
    }

    @Test
    public void testFileHandlerCalledOnSuccessfulRegistration() {
        String email = "newuser@example.com";
        String password = "newPassword";

        // Simulerer input
        ByteArrayInputStream inputStream = new ByteArrayInputStream((email + "\n" + password).getBytes());
        Scanner scanner = new Scanner(inputStream);

        register = new Register(users, mockFileHandler, scanner);

        // Kall registerUser
        register.registerUser();

        // Sjekk at brukeren er lagt til i users
        assertTrue(users.containsKey(email));

        // Sjekk at FileHandler sin saveUser ble kalt med riktig email og hash'et passord
        verify(mockFileHandler, times(1)).saveUser(eq(email), anyString());
    }
}
