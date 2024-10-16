/*package login_related;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import varsel.login_related.FileHandler;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTests {

    private final String testFileName = "test_users.txt";


    @Test
    @DisplayName("Test saving user")
    void testSaveUser() throws IOException {
        // Arrange
        FileHandler fileHandler = new FileHandler(testFileName);
        String email = "test@example.com";
        String password = "hashedpassword";

        // Act
        fileHandler.saveUser(email, password);

        // Verify that the user is saved to the file
        File file = new File(testFileName);
        assertTrue(file.exists(), "File should be created after saving user");

        // Cleanup
        file.delete();
    }

    @Test
    @DisplayName("Test loading users")
    void testLoadUsers() throws IOException {
        // Arrange
        // Først, opprett en testfil og skriv til den
        try (FileWriter writer = new FileWriter(testFileName)) {
            writer.write("test@example.com:hashedpassword\n");
            writer.write("user2@example.com:hashedpassword2\n");
        }

        FileHandler fileHandler = new FileHandler(testFileName);

        // Act
        HashMap<String, String> users = fileHandler.loadUsers();

        // Assert
        assertEquals(2, users.size(), "There should be 2 users loaded from the file");
        assertTrue(users.containsKey("test@example.com"), "First user should be loaded");
        assertTrue(users.containsKey("user2@example.com"), "Second user should be loaded");

        // Cleanup: Slett testfilen etter testen
        new File(testFileName).delete();
    }

}*/
