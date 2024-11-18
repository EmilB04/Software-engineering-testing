package com.varsel.NotificationTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.varsel.Notifications.FirebaseInitializer;

public class FirebaseInitializerTest {

    @BeforeEach
    public void resetFirebaseInitializer() {
        FirebaseInitializer.setInitialized(false);
    }

    @Test
    public void testFirebaseInitializerCorrect() {
        //Arrange
        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", "path/to/correct/service-account-file.json");

        //Act
        FirebaseInitializer.initializeFirebaseApp();

        //Assert
        assertTrue(FirebaseInitializer.getInitialized(), "Firebase er initialisert korrekt");         
    }
    
    @Test
    public void testFirebaseInitializerWrong() {
        //Arrange
        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", "path/to/wrong/service-account-file.json");

        //Act
        FirebaseInitializer.initializeFirebaseApp();

        //Assert
        assertFalse(FirebaseInitializer.getInitialized(), "Firebase er ikke initialisert korrekt på grunn av feil filsti");         
    }

    @Test
    public void testFirebaseInitializerNoPath() {
        //Arrange
        System.clearProperty("GOOGLE_APPLICATION_CREDENTIALS");

        //Act
        FirebaseInitializer.initializeFirebaseApp();

        //Assert
        assertFalse(FirebaseInitializer.getInitialized(), "Firebase er ikke initialisert korrekt på grunn av manglende filsti parameter");         
    }
}
