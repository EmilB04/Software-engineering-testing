package varsel.login_related;

import java.io.IOException;
import java.util.Scanner;

/**
 * LoginMenu tilbyr brukergrensesnitt for registrering og innlogging.
 */
public class LoginMenu {

    private final UserService userService;
    private final Scanner scanner;

    public LoginMenu(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Velkommen til brukerregistrering og innlogging!");

        while (true) {
            System.out.println("\nVelg et alternativ:");
            System.out.println("1. Registrer ny bruker");
            System.out.println("2. Logg inn");
            System.out.println("3. Avslutt");
            System.out.print("Ditt valg: ");
            String choice = scanner.nextLine();


            switch (choice) {
                case "1":
                    registerUser();
                    break;
                case "2":
                    loginUser();
                    break;
                case "3":
                    System.out.println("Avslutter programmet.");
                    return;
                default:
                    System.out.println("Ugyldig valg, prøv igjen.");
            }
        }
    }

    private void registerUser() {
        System.out.print("Oppgi e-post for registrering: ");
        String email = scanner.nextLine();
        System.out.print("Oppgi passord: ");
        String password = scanner.nextLine();

        try {
            boolean success = userService.registerUser(email, password);
            if (success) {
                System.out.println("Bruker registrert!");
            } else {
                System.out.println("Bruker med denne e-posten finnes allerede.");
            }
        } catch (IOException e) {
            System.out.println("Det oppstod en feil ved registrering: " + e.getMessage());
        }
    }

    private void loginUser() {
        System.out.print("Oppgi e-post for innlogging: ");
        String email = scanner.nextLine();
        System.out.print("Oppgi passord: ");
        String password = scanner.nextLine();

        try {
            boolean success = userService.loginUser(email, password);
            if (success) {
                System.out.println("Innlogging vellykket! Velkommen tilbake.");
            } else {
                System.out.println("Innlogging mislyktes. Sjekk e-post og passord.");
            }
        } catch (IOException e) {
            System.out.println("Det oppstod en feil ved innlogging: " + e.getMessage());
        }
    }
}
