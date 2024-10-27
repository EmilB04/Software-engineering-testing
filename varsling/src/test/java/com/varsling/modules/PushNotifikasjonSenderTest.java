package com.varsling.modules;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PushNotifikasjonSenderTest {

    // Test for å sende en notifikasjon
    @Test
    public void testSendNotifikasjon() {
        // Arrange
        Notifikasjon notifikasjon = new Notifikasjon("token1234", "Gunstig lading", "Strømmen er billig nå\nDu brude lade bilen din nå");

        // Act
        PushNotifikasjonSender.sendNotifikasjon(notifikasjon);

        // Assert
        assertEquals("token1234", notifikasjon.getToken());
        assertEquals("Gunstig lading", notifikasjon.getTitle());
        assertEquals("Strømmen er billig nå\nDu brude lade bilen din nå", notifikasjon.getBody());
        
    }
}
