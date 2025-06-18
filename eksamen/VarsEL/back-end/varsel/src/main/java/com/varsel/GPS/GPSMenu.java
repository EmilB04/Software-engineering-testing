package com.varsel.GPS;

import java.util.Scanner;

/**
 * The GPSMenu class provides a menu-based interface for interacting with GPS-related functionalities,
 * such as setting a new home position and checking if the user is currently at home based on their location.
 *
 * This class is used to manage GPS-specific interactions and integrates with the HomeChecker,
 * LocationService, and MainActivity classes to perform required operations.
 */
public class GPSMenu {
    // References to required components for GPS functionalities
    private final HomeChecker homeChecker;
    private final LocationService locationService;
    private final MainActivity mainActivity;

    public GPSMenu(HomeChecker homeChecker, LocationService locationService, MainActivity mainActivity) {
        this.homeChecker = homeChecker;
        this.locationService = locationService;
        this.mainActivity = mainActivity;
    }
    /**
     * Displays the GPS menu to the user and handles their input.
     *
     * This method provides options for the user to:
     * 1. Set a new home position
     * 2. Check if they are currently at home
     * 3. Exit back to the main menu
     *
     * @param scanner A Scanner object for reading user input
     */
    // GPS-menu
    public void showMenu(Scanner scanner) {
        String gpsInput;
        System.out.println("Velkommen til GPS siden!");
        while (true) {
            System.out.println("\nVelg et alternativ:");
            System.out.println("1. Sett ny hjemposisjon");
            System.out.println("2. Sjekk om brukeren er hjemme");
            System.out.println("3. Tilbake til hovedmeny");
            System.out.print("Ditt valg: ");
            gpsInput = scanner.nextLine();

            if ("1".equals(gpsInput)) {
                handleSetHomePosition(scanner);
            } else if ("2".equals(gpsInput)) {
                handleCheckUserPosition(scanner);
            } else if ("3".equals(gpsInput)) {
                System.out.println("Går tilbake til hovedmeny...");
                break;
            } else {
                System.out.println("Ugyldig valg. Prøv igjen.");
            }
        }
    }
    /**
     * Handles setting a new home position.
     *
     * Prompts the user to input latitude and longitude values for the new home position,
     * updates the HomeChecker instance, and confirms the change to the user.
     *
     * @param scanner A Scanner object for reading user input
     */
    private void handleSetHomePosition(Scanner scanner) {
        try {
            System.out.print("Skriv inn ny breddegrad (latitude): ");
            double newLatitude = Double.parseDouble(scanner.nextLine());
            System.out.print("Skriv inn ny lengdegrad (longitude): ");
            double newLongitude = Double.parseDouble(scanner.nextLine());

            homeChecker.setHomePosition(newLatitude, newLongitude); // Oppdaterer hjemmeposisjon
            System.out.println("Ny hjemposisjon er satt: " + newLatitude + ", " + newLongitude);
        } catch (NumberFormatException e) {
            System.out.println("Ugyldig input. Vennligst skriv inn gyldige tall.");
        }
    }
    /**
     * Handles checking the user's current position against the home position.
     *
     * Prompts the user to input their current latitude and longitude,
     * updates the LocationService with the new position, and checks if the user is at home.
     *
     * @param scanner A Scanner object for reading user input
     */
    private void handleCheckUserPosition(Scanner scanner) {
        try {
            System.out.print("Skriv inn breddegrad for nåværende posisjon: ");
            double currentLatitude = Double.parseDouble(scanner.nextLine());
            System.out.print("Skriv inn lengdegrad for nåværende posisjon: ");
            double currentLongitude = Double.parseDouble(scanner.nextLine());

            locationService.updateLocation(currentLatitude, currentLongitude); // Oppdaterer nåværende posisjon
            mainActivity.checkUserLocation(); // Sjekker om brukeren er hjemme
        } catch (NumberFormatException e) {
            System.out.println("Ugyldig input. Vennligst skriv inn gyldige tall.");
        }
    }
}
