package com.varsel.GPS;

/**
 * The "MainActivity" class checks if a user is within the "home" area

 * This class combines functionality from the "HomeChecker" class and the "LocationService" class
 * to simulate a scenario where the users location is updated, and a check is preformed to
 * determine if the user is within the home radius

 * Example usage:
 * <pre>
 *      MainActivity mainActivity = new MainActivity();
 *      mainActivity.checkUserLocation();
 * </pre>
 */

public class MainActivity {
    //Instances of HomeChecker for distance checking
    private final HomeChecker homeChecker;

    // Instance of LocationService to provide current location data
    private final LocationService locationService;

    /**
     * Constructor to initialize MainActivity with HomeChecker and LocationService instances

     * @param homeChecker a HomeChecker instance to check home radius
     * @param locationService a LocationService instance to retrieve user location
     */

    public MainActivity(HomeChecker homeChecker, LocationService locationService){
        this.homeChecker = homeChecker;
        this.locationService = locationService;
    }

    /**
     * Checks if the users current location is within the home radius and prints a message
     * to the console

     * This method retrieves the users current latitude and longitude form LocationService,
     * then checks with HomeChecker to see if the user is within the defined "home" radius.
     */

    public void checkUserLocation(){
        double userLatitude = locationService.getLatitude();
        double userLongitude = locationService.getLongitude();

        boolean isAtHome = homeChecker.isWithinHomeRadius(userLatitude, userLongitude);

        // Output result to the console
        if (isAtHome){
            System.out.println("The user is within the home area.");
        } else {
            System.out.println("The user is outside the home area.");
        }
    }

    /**
     * Main method to simulate the applications functionality

     * @param args Command-line arguments (not used)
     */

    public static void Main(String[] args){
        // Initialize components
        HomeChecker homeChecker = new HomeChecker();
        LocationService locationService = new LocationService(59.911491,  10.757933); // Set starting location

        // Create MainActivity instance
        MainActivity mainActivity = new MainActivity(homeChecker, locationService);

        // Check and output if the user is at home
        mainActivity.checkUserLocation();
    }
}