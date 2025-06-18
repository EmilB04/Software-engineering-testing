package com.varsel.GPSTests;

import com.varsel.GPS.HomeChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HomeCheckerTest {
    private final HomeChecker homeChecker = new HomeChecker();

    // Test to see if user is within the radius
    @Test
    @DisplayName("User within the radius.")
    public void testUserInsideRadius(){
        //Arrange
        double userLatitude = 59.911550;
        double userLongitude = 10.757900;

        //Act
        boolean result = homeChecker.isWithinHomeRadius(userLatitude,userLongitude);

        //Assert
        Assertions.assertTrue(result, "The User should be within the radius. ");
    }

    @Test
    @DisplayName("User outside the radius")
    public void testUserOutsideRadius() {
        // Arrange
        double userLatitude = 59.914000;
        double userLongitude = 10.760000;

        // Act
        boolean result = homeChecker.isWithinHomeRadius(userLatitude, userLongitude);

        // Assert
        Assertions.assertFalse(result, "User should be outside the radius.");
    }

    // Test for edge case (on the radius boundary)
    @Test
    @DisplayName("User on the radius boundary")
    public void testUserOnRadiusEdge() {
        // Arrange
        double userLatitude = 59.911491;
        double userLongitude = 10.757933;

        // Act
        boolean result = homeChecker.isWithinHomeRadius(userLatitude, userLongitude);

        //Assert
        Assertions.assertTrue(result, "The user should be within the radius (on the boundary)");
    }
}
