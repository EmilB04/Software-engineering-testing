package varsel.login_related;

import java.io.*;

/**
 * Denne klassen håndterer lagring og uthenting av brukere fra en tekstfil.
 */
public class UserRepository {

    private final String filePath;

    public UserRepository(String filePath) throws IOException {
        this.filePath = filePath;
        ensureFileExists();  // Sørg for at filen eksisterer
    }

    // Metode for å sjekke om filen finnes, og opprette den hvis den ikke gjør det
    private void ensureFileExists() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            if (file.createNewFile()) {
                System.out.println("Filen ble opprettet: " + filePath);
            } else {
                System.out.println("Kunne ikke opprette filen: " + filePath);
            }
        }
    }

    public void saveUser(User user) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(user.getEmail() + ":" + user.getHashedPassword());
            writer.newLine();
        }
    }

    public User findUserByEmail(String email) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equals(email)) {
                    return new User(parts[0], parts[1]);
                }
            }
        }
        return null;
    }
}
