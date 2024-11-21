package com.varsel.Users;

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

    /**
     * Starter hovedmenyen for brukerinteraksjon, lar brukeren registrere seg, logge inn eller avslutte programmet.
     */
    public void start() {
        System.out.println("Velkommen til brukerregistrering og innlogging!");

        while (true) {
            System.out.println("\nVelg et alternativ:");
            System.out.println("1. Registrer ny bruker");
            System.out.println("2. Logg inn");
            System.out.println("3. Tilbake til hovedmenyen");
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

    /**
     * Metode for å registrere ny bruker ved å be om e-post og passord.
     * Utfører input-validering og kaller registerUser på UserService.
     */
    private void registerUser() {
        String email = "";
        String password = "";

        // Sjekker at e-postinput ikke er tom
        while (email.isEmpty()) {
            System.out.print("Oppgi e-post for registrering: ");
            email = scanner.nextLine();
            if (email.isEmpty()) {
                System.out.println("E-post kan ikke være tom. Prøv igjen.");
            }
        }

        // Sjekker at passordinput ikke er tom
        while (password.isEmpty()) {
            System.out.print("Oppgi passord: ");
            password = scanner.nextLine();
            if (password.isEmpty()) {
                System.out.println("Passord kan ikke være tomt. Prøv igjen.");
            }
        }

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

    /**
     * Metode for å logge inn en bruker ved å be om e-post og passord.
     * Utfører input-validering og kaller loginUser på UserService.
     */
    private void loginUser() {
        String email = "";
        String password = "";

        // Sjekker at e-postinput ikke er tom
        while (email.isEmpty()) {
            System.out.print("Oppgi e-post for innlogging: ");
            email = scanner.nextLine();
            if (email.isEmpty()) {
                System.out.println("E-post kan ikke være tom. Prøv igjen.");
            }
        }

        // Sjekker at passordinput ikke er tom
        while (password.isEmpty()) {
            System.out.print("Oppgi passord: ");
            password = scanner.nextLine();
            if (password.isEmpty()) {
                System.out.println("Passord kan ikke være tomt. Prøv igjen.");
            }
        }

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
