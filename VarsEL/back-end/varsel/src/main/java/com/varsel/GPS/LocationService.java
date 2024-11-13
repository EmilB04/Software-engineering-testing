package com.varsel.GPS;

/**
 * This is a Service class to simulate user location data retrieval
 *
 * This class provides methods to retrieve a users current location (latitude and longitude)
 *
 * Example usage:
 * <pre>
 * LocationService locationService = new LocationService();
 * double latitude = locationService.getLatitude();
 * double longitude = locationService.getLongitude();
 * </pre>
 */

public class LocationService {
    //Simulated coordinates
    private double latitude;
    private double longitude;

    /**
     * Constructor to initialize the location service with predefined coordinates.
     *
     * @param latitude The latitude to set for the users location
     * @param longitude The longitude to set for the users location
     */

    public LocationService(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Retrieves the latitude of the users current location
     *
     * @return The users latitude
     */
    public double getLatitude(){
        return latitude;
    }

    /**
     * Retrieves the longitude of the users current location
     *
     * @return The users longitude
     */

    public double getLongitude(){
        return longitude;
    }

    /**
     * Sets a new simulated location for the user
     *
     * @param latitude The new latitude for the users location
     * @param longitude The new longitude for the users location
     */

    public void updateLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}