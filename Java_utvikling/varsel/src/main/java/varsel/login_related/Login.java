package varsel.login_related;

import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Scanner;

public class Login {

    private HashMap<String, String> users;
    private Scanner scanner = new Scanner(System.in);

    public Login(HashMap<String, String> users) {
        this.users = users;
    }

    public void loginUser() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Sjekk om e-post og passord matcher en eksisterende konto
        if (users.containsKey(email)) {
            String storedHashedPassword = users.get(email);

            // Verifiser passordet ved hjelp av BCrypt
            if (BCrypt.checkpw(password, storedHashedPassword)) {
                System.out.println("Login successful! Welcome, " + email);
            } else {
                System.out.println("Invalid password. Please try again.");
            }
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }

    public boolean loginUser(String email, String password123) {
        return users.containsKey(email) && BCrypt.checkpw(password123, users.get(email));
    }
}
