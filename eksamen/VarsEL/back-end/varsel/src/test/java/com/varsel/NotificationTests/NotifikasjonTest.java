package com.varsel.NotificationTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.varsel.Notifications.Notifikasjon;

public class NotifikasjonTest {

    @Test
    public void testConstructor() {
        //arrange og act
        Notifikasjon notifikasjon = new Notifikasjon("12345", "Test", "Test");

        //assert
        assertEquals("12345", notifikasjon.getToken());
        assertEquals("Test", notifikasjon.getTitle());
        assertEquals("Test", notifikasjon.getBody());
    }

    @Test
    public void testSetter(){
        //arrange
        Notifikasjon notifikasjon2 = new Notifikasjon("12345", "gammel", "gammel");
        String nyToken = "54321";
        String nyTitle = "ny";
        String nyBody = "ny";

        //act
        notifikasjon2.setToken(nyToken);
        notifikasjon2.setTitle(nyTitle);
        notifikasjon2.setBody(nyBody);

        //assert
        assertEquals(nyToken, notifikasjon2.getToken());
        assertEquals(nyTitle, notifikasjon2.getTitle());
        assertEquals(nyBody, notifikasjon2.getBody());
    }
    
}
