package com.varsel;

import com.varsel.Users.LoginMenu;
import com.varsel.Users.UserRepository;
import com.varsel.Users.UserService;
import com.varsel.GPS.HomeChecker;
import com.varsel.GPS.LocationService;
import com.varsel.GPS.MainActivity;
import com.varsel.GPS.GPSMenu;
import com.varsel.Notifications.FirebaseInitializer;
import com.varsel.Notifications.NotificationMenu;
import com.varsel.Notifications.PushNotifikasjonSender;

import java.io.IOException;
import java.util.Scanner;

public class App 
{
    private static final String FILE_PATH = "users.txt";

    public static void main(String[] args ) throws IOException {
        System.out.println( "Velkommen til test-siden" );

        /*
        Under er kode som tester registrering og innlogging ved hjelp av et enkelt brukergrensesnitt.
        Det lager en tekstfil som lagrer input email og passord(kryptert), og leser input fra bruker
        dersom man ønsker å logge inn og sjekker om det er likt noe av det som er lagret i filen.
         */
        // Bruker - komponenter
        UserRepository userRepository = new UserRepository(FILE_PATH);
        UserService userService = new UserService(userRepository);
        LoginMenu loginMenu = new LoginMenu(userService);

        // GPS - komponenter 
        HomeChecker homeChecker = new HomeChecker();
        LocationService locationService = new LocationService(59.911491, 10.757933);
        MainActivity mainActivity = new MainActivity(homeChecker, locationService);
        GPSMenu gpsMenu = new GPSMenu(homeChecker, locationService, mainActivity);

        // Varsling - komponenter
        FirebaseInitializer.initializeFirebaseApp();
        PushNotifikasjonSender pushNotifikasjonSender = new PushNotifikasjonSender();
        NotificationMenu notificationMenu = new NotificationMenu(pushNotifikasjonSender);



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
            } 
            else if ("GPS".equalsIgnoreCase(input) || "3".equals(input)) {
                System.out.println("\n");
                gpsMenu.showMenu(scanner);
            } 
            else if ("Varsling".equalsIgnoreCase(input) || "4".equals(input)) {
                System.out.println("\n");
                notificationMenu.showMenu(scanner);
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
        
    
        /**
         * Koden under viser hvordan push-notifikasjoner kan sendes til en bruker
         * ved hjelp av Firebase Cloud Messaging (FCM) og en service account.
         * Koden viser både strømprisbaserte og batterinivåbaserte notifikasjoner.
         * 
         * Først blir Firebase initialisert. Deretter opprettes en instans av 
         * PushNotifikasjonSender, som håndterer sendingen av notifikasjoner.
         * Testene demonstrerer hvordan notifikasjoner kan sendes basert på strømpris
         * og batterinivå ved hjelp av Firebase.
         * 
         * Feilhåndtering:
         * - Firebase-initialisering og eventuelle feil ved oppkobling til Firebase 
         *   håndteres i FirebaseInitializer-klassen.
         * - Eventuelle feil som kan oppstå når notifikasjoner sendes til 
         *   Firebase håndteres i PushNotifikasjonSender-klassen.
         * 
         * For at koden skal funke må Miljøvariabelen 'GOOGLE_APPLICATION_CREDENTIALS' settes,
         * dette kan gjøres ved å kjøre følgende kommando i terminalen:
         * set GOOGLE_APPLICATION_CREDENTIALS=C:\path\to\your\service-account-file.json
         */
    
    }
    
}
