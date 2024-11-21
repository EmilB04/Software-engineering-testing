package com.varsel.GPS;

/**
 * A utility class that checks if a user is within a predefined "home" radius based on
 * geographic coordinates (Latitude/Longitude)

 * The class will provide a single method to see if a given
 * position (latitude and longitude) is within the "home" radius around specified "home" coordinates

 * Example usage:
 * <pre>
 *     HomeChecker checker = new HomeChecker();
 *     boolean is AtHome = checker.isWithinHomeRadius(userLatitude, userLongitude);
 * </pre>
 */

public class HomeChecker {
    // Home coordinates (latitude and longitude) and radius in meters
    private double HOME_LATITUDE = 59.911491;   // Latitude
    private double HOME_LONGITUDE = 10.757933; // Longitude
    private static final float HOME_RADIUS = 100; // Radius in meters

    /**
     * Checks if a user is within the "home" radius

     * This method calculates the distance between the users location and the "home" coordinates,
     * and compares it to the defined "home" radius
     *
     * @param latitude The users latitude
     * @param longitude The users longitude
     * @return True if the user is within the home radius, false otherwise
     */

    public boolean isWithinHomeRadius(double latitude, double longitude) {
        // Earths radius in meters
        double earthRadius = 6371000;

        // Calculating the difference in latitude and longitude in radius
        double dLat = Math.toRadians(latitude - HOME_LATITUDE);
        double dLon = Math.toRadians(longitude - HOME_LONGITUDE);

        // Haversine formula for calculating the distance between two latitude/longitude points
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(HOME_LATITUDE)) *
                           Math.cos(Math.toRadians(latitude)) *
                           Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = earthRadius * c;

        // This Return returns true if the calculated distance is less than the home radius
        return distance <= HOME_RADIUS;
    }
     /**
     * Updates the home latitude and longitude
     *
     * @param latitude The new home latitude
     * @param longitude The new home longitude
     */
    public void setHomePosition(double latitude, double longitude) {
        this.HOME_LATITUDE = latitude;
        this.HOME_LONGITUDE = longitude;
        System.out.println("Hjemmeposisjonen er oppdatert til: Latitude=" + latitude + ", Longitude=" + longitude);
    }
}