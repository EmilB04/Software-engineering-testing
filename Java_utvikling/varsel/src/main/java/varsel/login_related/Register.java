package varsel.login_related;

import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Scanner;

public class Register {
    // HashMap for å lagre brukerkontoer med e-post som nøkkel og passord som verdi
    private HashMap<String, String> users;
    private Scanner scanner = new Scanner(System.in);
    private FileHandler fileHandler;

    // Konstruktør som tar inn både HashMap og FileHandler
    public Register(HashMap<String, String> users, FileHandler fileHandler) {
        this.users = users;
        this.fileHandler = fileHandler;
    }

    public void registerUser() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        // Sjekk om e-posten allerede er registrert
        if (users.containsKey(email)) {
            System.out.println("This email is already registered. Try logging in.");
            return;
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Hash passordet ved hjelp av BCrypt
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Legg til ny bruker i HashMap
        users.put(email, hashedPassword);

        // Lagre bruker i filen via FileHandler
        fileHandler.saveUser(email, hashedPassword);

        System.out.println("Registration successful! You can now log in.");
    }
}
