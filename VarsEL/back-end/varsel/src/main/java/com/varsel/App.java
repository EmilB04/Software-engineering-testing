package com.varsel;

import com.varsel.Users.LoginMenu;
import com.varsel.Users.UserRepository;
import com.varsel.Users.UserService;
import com.varsel.GPS.HomeChecker;
import com.varsel.GPS.LocationService;
import com.varsel.GPS.MainActivity;

import java.io.IOException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    //private static final String FILE_PATH = "users.txt";

    public static void main(String[] args ) throws IOException {
        System.out.println( "Velkommen til test-siden" );

        /*
        Under er kode som tester registrering og innlogging ved hjelp av et enkelt brukergrensesnitt.
        Det lager en tekstfil som lagrer input email og passord(kryptert), og leser input fra bruker
        dersom man ønsker å logge inn og sjekker om det er likt noe av det som er lagret i filen.
         */
        // Bruker - komponenter
        UserRepository userRepository = new UserRepository("users.txt");
        UserService userService = new UserService(userRepository);
        LoginMenu loginMenu = new LoginMenu(userService);
        // GPS - komponenter 
        HomeChecker homeChecker = new HomeChecker(); // HomeChecker-instans
        LocationService locationService = new LocationService(59.911491, 10.757933); // Standard hjemposisjon
        MainActivity mainActivity = new MainActivity(homeChecker, locationService); // MainActivity-instans

        try (Scanner scanner = new Scanner(System.in)) {
            String input;
            while (true) {
            System.out.print("\nSkriv ett av alternativene under for å teste ulike funksjoner:\n" +
                "1. User\n" +
                "2. Strømpris\n" +
                "3. GPS\n" +
                "4. Varsling\n" +
                "5. Avslutt\n" +
                "Ditt valg: ");
            input = scanner.nextLine();

            if ("User".equalsIgnoreCase(input) || "1".equals(input)) {
                System.out.println("\n");
                loginMenu.start();  // Starter menyen
            } 
            else if ("Strømpris".equalsIgnoreCase(input) || "2".equals(input)) {
                System.out.println("\n");
                // Strømprisfunksjon
            } 
            else if ("GPS".equalsIgnoreCase(input) || "3".equals(input)) {
                System.out.println("\n");
                handleGPSMenu(scanner, homeChecker, locationService, mainActivity);
               // GPSfunksjon
            } 
            else if ("Varsling".equalsIgnoreCase(input) || "4".equals(input)) {
                System.out.println("\n");
                // Varslingsfunksjon
            }
            else if ("Avslutt".equalsIgnoreCase(input) || "5".equals(input)) {
                System.out.println("Avslutter programmet.");
                break;
            }
            else {
                System.out.println("Ugyldig input. Prøv igjen.");
                }
            }
        }
    }
    private static void handleGPSMenu(Scanner scanner, HomeChecker homeChecker, LocationService locationService, MainActivity mainActivity) {
        String gpsInput;
        while (true) {
            System.out.print("\nGPS-meny:\n" +
                "1. Sett ny hjemposisjon\n" +
                "2. Sjekk om brukeren er hjemme\n" +
                "3. Tilbake til hovedmeny\n" +
                "Ditt valg: ");
            gpsInput = scanner.nextLine();

            if ("1".equals(gpsInput)) {
                try {
                    System.out.print("Skriv inn ny breddegrad (latitude): ");
                    double newLatitude = Double.parseDouble(scanner.nextLine());
                    System.out.print("Skriv inn ny lengdegrad (longitude): ");
                    double newLongitude = Double.parseDouble(scanner.nextLine());

                    homeChecker.setHomePosition(newLatitude, newLongitude); // Oppdaterer hjemmeposisjon i HomeChecker
                    System.out.println("Ny hjemposisjon er satt: " + newLatitude + ", " + newLongitude);
                } catch (NumberFormatException e) {
                    System.out.println("Ugyldig input. Vennligst skriv inn gyldige tall.");
                }
            } else if ("2".equals(gpsInput)) {
                try {
                    System.out.print("Skriv inn breddegrad for nåværende posisjon: ");
                    double currentLatitude = Double.parseDouble(scanner.nextLine());
                    System.out.print("Skriv inn lengdegrad for nåværende posisjon: ");
                    double currentLongitude = Double.parseDouble(scanner.nextLine());

                    locationService.updateLocation(currentLatitude, currentLongitude); // Oppdaterer posisjonen i LocationService
                    mainActivity.checkUserLocation(); // Sjekker om brukeren er hjemme
                } catch (NumberFormatException e) {
                    System.out.println("Ugyldig input. Vennligst skriv inn gyldige tall.");
                }
            } else if ("3".equals(gpsInput)) {
                System.out.println("Går tilbake til hovedmeny...");
                break;
            } else {
                System.out.println("Ugyldig valg. Prøv igjen.");
            }
        }
    }
}
