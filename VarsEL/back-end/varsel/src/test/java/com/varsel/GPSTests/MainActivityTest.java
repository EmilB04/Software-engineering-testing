package com.varsel.GPSTests;

import com.varsel.GPS.MainActivity;
import com.varsel.GPS.LocationService;
import com.varsel.GPS.HomeChecker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class MainActivityTest {

    @Test
    @DisplayName("User is within the radius")
    public void testUserInsideRadius() {
        // Arrange
        LocationService locationService = mock(LocationService.class);
        HomeChecker homeChecker = mock(HomeChecker.class);
        MainActivity mainActivity = new MainActivity(homeChecker, locationService);

        // Simulate a location within the radius
        double userLatitude = 59.911550;
        double userLongitude = 10.757900;
        when(locationService.getLatitude()).thenReturn(userLatitude);
        when(locationService.getLongitude()).thenReturn(userLongitude);
        when(homeChecker.isWithinHomeRadius(userLatitude, userLongitude)).thenReturn(true);

        // Act
        mainActivity.checkUserLocation(); // <- This would print to the console

        // Assert
        verify(homeChecker).isWithinHomeRadius(userLatitude, userLongitude);
    }

    @Test
    @DisplayName("User outside the radius")
    public void testUserOutsideRadius() {
        // Arrange
        LocationService locationService = mock(LocationService.class);
        HomeChecker homeChecker = mock(HomeChecker.class);
        MainActivity mainActivity = new MainActivity(homeChecker, locationService);

        // Simulate a location outside the radius
        double userLatitude = 59.914000;
        double userLongitude = 10.760000;
        when(locationService.getLatitude()).thenReturn(userLatitude);
        when(locationService.getLongitude()).thenReturn(userLongitude);
        when(homeChecker.isWithinHomeRadius(userLatitude, userLongitude)).thenReturn(false);

        // Act
        mainActivity.checkUserLocation(); // <- This would print to the console

        // Assert
        verify(homeChecker).isWithinHomeRadius(userLatitude, userLongitude);
    }
}
