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

        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", "C:\\path\\to\\your\\service-account-file.json");

        // Bruker - komponenter
        // Se LoginMenu.java for mer informasjon
        UserRepository userRepository = new UserRepository(FILE_PATH);
        UserService userService = new UserService(userRepository);
        LoginMenu loginMenu = new LoginMenu(userService);

        // GPS - komponenter 
        // Se GPSMenu.java for mer informasjon
        HomeChecker homeChecker = new HomeChecker();
        LocationService locationService = new LocationService(59.911491, 10.757933);
        MainActivity mainActivity = new MainActivity(homeChecker, locationService);
        GPSMenu gpsMenu = new GPSMenu(homeChecker, locationService, mainActivity);

        // Varsling - komponenter
        // Se NotificationMenu.java for mer informasjon
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
                // Strømprismeny her
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
    }
    
}
