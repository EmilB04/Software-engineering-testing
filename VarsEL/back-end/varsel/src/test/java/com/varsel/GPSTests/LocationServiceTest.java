package com.varsel.GPSTests;

import com.varsel.GPS.LocationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LocationServiceTest {
    //Test for initialization of position
    @Test
    @DisplayName("Initial position should match provided latitude and longitude")
    public void testInitialPosition() {
        // Arrange
        LocationService locationService = new LocationService(59.911491, 10.757933);

        // Act
        double latitude = locationService.getLatitude();
        double longitude = locationService.getLongitude();

        // Assert
        Assertions.assertEquals(59.911491, latitude, "The latitude should be initialized correctly.");
        Assertions.assertEquals(10.757933, longitude, "The longitude should be initialized correctly.");
    }

    // Test for updating of position
    @Test
    @DisplayName ("Position should be updated correctly")
    public void testUpdatePosition() {
        // Arrange
        LocationService locationService = new LocationService(59.911491, 10.757933);

        // Act
        locationService.updateLocation(59.913000, 10.759000);
        double updatedLatitude = locationService.getLatitude();
        double updatedLongitude = locationService.getLongitude();

        //Assert
        Assertions.assertEquals(59.913000, updatedLatitude, "The latitude should be updated correctly.");
        Assertions.assertEquals(10.759000, updatedLongitude, "The longitude should be updated correctly.");
    }
}
