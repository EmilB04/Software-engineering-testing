package com.varsel.NotificationTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.varsel.Notifications.NotifikasjonService;
import com.varsel.Notifications.Notifikasjon;

public class NotifikasjonServiceTest {

    private NotifikasjonService notifikasjonService = new NotifikasjonService();

    //Tester for stromprisnotifikasjon
    @Test
    public void testGratisStrom() {
        //Act
        Notifikasjon notifikasjon = notifikasjonService.getStromprisNotifikasjon(true, 0);
        
        //Assert
        assertNotNull(notifikasjon);
        assertEquals("12345", notifikasjon.getToken());
        assertEquals("Strømprisvarsel", notifikasjon.getTitle());
        assertEquals("Strømmen er gratis nå, du burde lade", notifikasjon.getBody());
    }

    @Test
    public void testStromUnder0_5() {
        //Act
        Notifikasjon notifikasjon = notifikasjonService.getStromprisNotifikasjon(true, 0.4);
        
        //Assert
        assertNotNull(notifikasjon);
        assertEquals("12345", notifikasjon.getToken());
        assertEquals("Strømprisvarsel", notifikasjon.getTitle());
        assertEquals("Strømmen er billig nå, du burde lade", notifikasjon.getBody());
    }

    @Test
    public void testStromMellom1_3() {
        //Act
        Notifikasjon notifikasjon = notifikasjonService.getStromprisNotifikasjon(true, 2);
        
        //Assert
        assertNotNull(notifikasjon);
        assertEquals("12345", notifikasjon.getToken());
        assertEquals("Strømprisvarsel", notifikasjon.getTitle());
        assertEquals("Strømmen er mellom 1 og 3 kr per kWh, du burde vente med å lade", notifikasjon.getBody());
    }

    @Test
    public void testStromOver5() {
        //Act
        Notifikasjon notifikasjon = notifikasjonService.getStromprisNotifikasjon(true, 6);
        
        //Assert
        assertNotNull(notifikasjon);
        assertEquals("12345", notifikasjon.getToken());
        assertEquals("Strømprisvarsel", notifikasjon.getTitle());
        assertEquals("Strømmen er over 5 kr per kWh, du burde vente med å lade", notifikasjon.getBody());
    }
    

     // Tester for stromnivånotifikasjon
    @Test
    public void testStromnivaUnder5() {
        //Act
        Notifikasjon notifikasjon = notifikasjonService.getStromnivaNotifikasjon( 4);
        
        //Assert
        assertNotNull(notifikasjon);
        assertEquals("12345", notifikasjon.getToken());
        assertEquals("Batterinotifikasjon", notifikasjon.getTitle());
        assertEquals("Batterinivået er under 5%, du burde lade", notifikasjon.getBody());
    }

    @Test
    public void testStromnivaUnder20() {
        //Act
        Notifikasjon notifikasjon = notifikasjonService.getStromnivaNotifikasjon( 15);
        
        //Assert
        assertNotNull(notifikasjon);
        assertEquals("12345", notifikasjon.getToken());
        assertEquals("Batterinotifikasjon", notifikasjon.getTitle());
        assertEquals("Batterinivået er under 20%, du burde lade", notifikasjon.getBody());
    }


    @Test
    public void testStromnivaOver50() {
        //Act
        Notifikasjon notifikasjon = notifikasjonService.getStromnivaNotifikasjon( 60);
        
        //Assert
        assertNotNull(notifikasjon);
        assertEquals("12345", notifikasjon.getToken());
        assertEquals("Batterinotifikasjon", notifikasjon.getTitle());
        assertEquals("Batterinivået er mellom 50 og 80%, du kan vente med å lade", notifikasjon.getBody());
    }

    @Test
    public void testStromnivaOver80() {
        //Act
        Notifikasjon notifikasjon = notifikasjonService.getStromnivaNotifikasjon( 85);
        
        //Assert
        assertNotNull(notifikasjon);
        assertEquals("12345", notifikasjon.getToken());
        assertEquals("Batterinotifikasjon", notifikasjon.getTitle());
        assertEquals("Batteriet er over 80%, det er ikke hensiktsmessig å lade nå", notifikasjon.getBody());
    }

}
