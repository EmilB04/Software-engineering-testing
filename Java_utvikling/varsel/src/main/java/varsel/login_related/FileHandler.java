package varsel.login_related;

import java.io.*;
import java.util.HashMap;

public class FileHandler {
    private static final String FILE_NAME = "users.txt";

    public FileHandler(String filename) {

    }


    // Laster inn brukere fra filen til et HashMap
    public HashMap<String, String> loadUsers() {
        HashMap<String, String> users = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);  // Legg til bruker i HashMap
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }

        return users;
    }

    // Lagrer ny bruker i filen
    public void saveUser(String email, String hashedPassword) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {  // Append modus
            writer.write(email + ":" + hashedPassword + "\n");
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }
}