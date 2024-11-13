package com.varsel.GPS;

/**
 * A utility class that checks if a user is within a predfined "home" radius based on
 * geographic coordinates (Latitude/Longitude)
 *
 * The class will provide a single method to see if a given
 * position (latitude and longitude) is within the "home" radius around specified "home" coordinates
 *
 * Example usage:
 * <pre>
 *     HomeChecker checker = new HomeChecker();
 *     boolean is AtHome = checker.isWithinHomeRadius(userLatitude, userLongitude);
 * </pre>
 */