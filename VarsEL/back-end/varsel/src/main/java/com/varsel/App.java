package com.varsel;

import com.varsel.Users.LoginMenu;
import com.varsel.Users.UserRepository;
import com.varsel.Users.UserService;
import com.varsel.Utility.HttpGetClient;
import com.varsel.GPS.HomeChecker;
import com.varsel.GPS.LocationService;
import com.varsel.GPS.MainActivity;
import com.varsel.ElectricityPrices.ElectricityPriceCalculator;
import com.varsel.ElectricityPrices.ElectricityPriceData;
import com.varsel.ElectricityPrices.ElectricityPriceParser;
import com.varsel.ElectricityPrices.ElectricityPriceUrlBuilder;
import com.varsel.ElectricityPrices.GetElectricityPrices;
import com.varsel.ElectricityPrices.HttpHandler;
import com.varsel.GPS.GPSMenu;
import com.varsel.Notifications.FirebaseInitializer;
import com.varsel.Notifications.NotificationMenu;
import com.varsel.Notifications.PushNotifikasjonSender;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class App 
{
    private static final String FILE_PATH = "users.txt";

    public static void main(String[] args ) throws IOException {
        System.out.println( "Velkommen til app-siden" );

        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", "C:\\path\\to\\your\\service-account-file.json");

        // Bruker - komponenter
        // Se LoginMenu.java for mer informasjon
        UserRepository userRepository = new UserRepository(FILE_PATH);
        UserService userService = new UserService(userRepository);
        LoginMenu loginMenu = new LoginMenu(userService);

        // Strømpris - komponenter
        // Komponenter for å hente strømpriser: 
        ElectricityPriceParser parser = new ElectricityPriceParser();
        HttpHandler handler = new HttpHandler(new HttpGetClient());
        ElectricityPriceUrlBuilder urlBuilder = new ElectricityPriceUrlBuilder();
        GetElectricityPrices electricityPrices = new GetElectricityPrices(parser, handler, urlBuilder);
        ElectricityPriceCalculator calculator = new ElectricityPriceCalculator();


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


        // ---------------------------------
        // Meny for å teste vise ulike funksjoner
        // ---------------------------------
        try (Scanner scanner = new Scanner(System.in)) {
            String input;
            System.out.println("Vi gjør oppmerksom på at noen tester krever miljøvariabler for å kjøre. " +
        "Dette gjelder for: \n - Varsling\n");
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
                System.out.println("Velkommen til strømprismenyen!");
                while (true) {
                    System.out.print("\nVelg en prissone:\n" +
                        "1. Oslo/Øst-Norge\n" +
                        "2. Kristiansand/Sør-Norge\n" +
                        "3. Trondheim/Midt-Norge\n" +
                        "4. Tromsø/Nord-Norge\n" +
                        "5. Bergen/Vest-Norge\n" +
                        "6. Tilbake til hovedmenyen\n" +
                        "Ditt valg: ");
                    String inputZone = scanner.nextLine();
                    String zone = ""; 

                    if ("1".equals(inputZone)) {
                        zone = "NO1";
                    } else if ("2".equals(inputZone)) {
                        zone = "NO2";
                    } else if ("3".equals(inputZone)) {
                        zone = "NO3";
                    } else if ("4".equals(inputZone)) {
                        zone = "NO4";
                    } else if ("5".equals(inputZone)) {
                        zone = "NO5";
                    } else if ("6".equals(inputZone)) {
                        break;
                    } else {
                        System.out.println("Ugyldig valg. Prøv igjen.");
                    }

                    System.out.print("\nVelg ett alternativ:\n" +
                        "1. Dagens strømpriser\n" +
                        "2. Dagens laveste strømpris\n" +
                        "3. Dagens høyeste strømpris\n" +
                        "4. Dagens gjennomsnittspris\n" +
                        "Ditt valg: ");
                    String ElectricityPriceInput = scanner.nextLine();

                    //Plasserer strømprisdata hentet fra API i liste
                    List<ElectricityPriceData> prices = electricityPrices.fetchElectricityPrices(zone);

                    if ("1".equals(ElectricityPriceInput)) {
                        System.out.println("Dagens strømpriser: ");
                        for (ElectricityPriceData data : prices) {
                            System.out.println("Pris: " + data.getPrice() + ", Start: " + data.getTimeStart() + ", Slutt: " + data.getTimeStop());
                        }
                    } else if ("2".equals(ElectricityPriceInput)) {
                        double lowestPrice = calculator.calculateLowestPrice(prices);
                        System.out.println("Dagens laveste strømpris er: " + lowestPrice + " NOK/Kwh");
                    } else if ("3".equals(ElectricityPriceInput)) {
                        double highestPrice = calculator.calculateHighestPrice(prices);
                        System.out.println("Dagens høyeste strømpris er: " + highestPrice + " NOK/Kwh");
                    } else if ("4".equals(ElectricityPriceInput)) {
                        double averagePrice = calculator.calculateAveragePrice(prices);
                        System.out.println("Dagens gjennomsnittsstrømpris er: " + averagePrice + " NOK/Kwh");
                    } else {
                        System.out.println("Ugyldig valg. Prøv igjen");
                    }
                }
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