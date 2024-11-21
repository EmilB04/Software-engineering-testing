package com.varsel;

import com.varsel.Users.LoginMenu;
import com.varsel.Users.UserRepository;
import com.varsel.Users.UserService;
import com.varsel.GPS.HomeChecker;
import com.varsel.GPS.LocationService;
import com.varsel.GPS.MainActivity;
import com.varsel.GPS.GPSMenu;
import com.varsel.Notifications.FirebaseInitializer;
import com.varsel.Notifications.PushNotifikasjonSender;

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
        HomeChecker homeChecker = new HomeChecker();
        LocationService locationService = new LocationService(59.911491, 10.757933);
        MainActivity mainActivity = new MainActivity(homeChecker, locationService);
        GPSMenu gpsMenu = new GPSMenu(homeChecker, locationService, mainActivity);

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
                gpsMenu.showMenu(scanner);
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

        FirebaseInitializer.initializeFirebaseApp();

        PushNotifikasjonSender pushNotifikasjonSender = new PushNotifikasjonSender();

        //Strømmprisbaserte notifikasjoner
        //Eksempel 1
        boolean tillatelse = true;
        double strompris = 0.3;  
        pushNotifikasjonSender.sendStromprisNotifikasjon(tillatelse, strompris);
        //Forventet output: "Strømmen er billig nå, du burde lade"

        //Eksempel 2
        tillatelse = true;
        strompris = 5.2;
        pushNotifikasjonSender.sendStromprisNotifikasjon(tillatelse, strompris);
        //Forventet output: "Strømmen er over 5 kr per kWh, du burde ikke lade nå hvis du ikke må"

        //Eksempel 3
        tillatelse = false; //Brukeren har ikke gitt tillatelse til å motta notifikasjoner
        strompris = 0.5;
        pushNotifikasjonSender.sendStromprisNotifikasjon(tillatelse, strompris);
        //Forventet output: null(siden brukeren ikke har gitt tillatelse)


        //Batterinivåbaserte notifikasjoner
        //Eksempel 3
        int stromniva = 4;
        pushNotifikasjonSender.sendStromnivaNotifikasjon(stromniva);
        //Forventet output: "Batterinivået er under 5%, du burde lade"

        //Eksempel 4:
        stromniva = 26;
        pushNotifikasjonSender.sendStromnivaNotifikasjon(stromniva);
        //Forventet output: "Batterinivået er mellom 20 og 50%, det er kanskje lurt å lade"

        //Eksempel 5:
        stromniva = 82;
        pushNotifikasjonSender.sendStromnivaNotifikasjon(stromniva);
        //Forventet output: "Batteriet er over 80%, det er ikke hensiktsmessig å lade nå"
    }
    
}
