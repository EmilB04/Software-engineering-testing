package com.varsel.Notifications;

import java.util.Scanner;


/*
    * Koden under viser hvordan push-notifikasjoner kan sendes til en bruker
    * ved hjelp av Firebase Cloud Messaging (FCM) og en service account.
    * Koden viser både strømprisbaserte og batterinivåbaserte notifikasjoner.
    * 
    * Først blir Firebase initialisert. Deretter opprettes en instans av 
    * PushNotifikasjonSender, som håndterer sendingen av notifikasjoner.
    * Testene demonstrerer hvordan notifikasjoner kan sendes basert på strømpris
    * og batterinivå ved hjelp av Firebase.
    * 
    * Feilhåndtering:
    * - Firebase-initialisering og eventuelle feil ved oppkobling til Firebase 
    *   håndteres i FirebaseInitializer-klassen.
    * - Eventuelle feil som kan oppstå når notifikasjoner sendes til 
    *   Firebase håndteres i PushNotifikasjonSender-klassen.
*/


public class NotificationMenu {
    private final PushNotifikasjonSender pushNotifikasjonSender;

    public NotificationMenu(PushNotifikasjonSender pushNotifikasjonSender) {
        this.pushNotifikasjonSender = pushNotifikasjonSender;
    }

    /**
     * Viser varslingstestmenyen og lar brukeren velge hvilken type varsling som skal testes.
     * @param scanner Scanner-objekt for å lese input fra brukeren
     */
    public void showMenu(Scanner scanner) {
        String input;
        System.out.println("Velkommen til varslingstestmenyen!\n");
        while (true) {
            System.out.print("Velg et alternativ\n" +
                "1. Test varsling med tillatelse\n" +
                "2. Test varsling uten tillatelse\n" +
                "3. Test batterinivåvarsling (under 5%)\n" +
                "4. Test batterinivåvarsling (20-50%)\n" +
                "5. Test batterinivåvarsling (over 80%)\n" +
                "6. Tilbake til hovedmenyen\n" +
                "Ditt valg: ");
            input = scanner.nextLine();

            if ("1".equals(input)) {
                // Test varsling med tillatelse
                System.out.println("Tester varsling med tillatelse...");
                boolean tillatelse = true;
                double strompris = 0.3;  // Eksempel strømpris
                pushNotifikasjonSender.sendStromprisNotifikasjon(tillatelse, strompris);
            } 
            else if ("2".equals(input)) {
                // Test varsling uten tillatelse
                System.out.println("Tester varsling uten tillatelse...");
                boolean tillatelse = false;
                double strompris = 5.2;  // Eksempel strømpris
                pushNotifikasjonSender.sendStromprisNotifikasjon(tillatelse, strompris);
            } 
            else if ("3".equals(input)) {
                // Test batterinivåbasert varsling for nivå under 5%
                System.out.println("Tester batterinivåvarsling for nivå under 5%...");
                int stromniva = 4;
                pushNotifikasjonSender.sendStromnivaNotifikasjon(stromniva);
            }
            else if ("4".equals(input)) {
                // Test batterinivåbasert varsling for nivå mellom 20% og 50%
                System.out.println("Tester batterinivåvarsling for nivå mellom 20% og 50%...");
                int stromniva = 26;
                pushNotifikasjonSender.sendStromnivaNotifikasjon(stromniva);
            }
            else if ("5".equals(input)) {
                // Test batterinivåbasert varsling for nivå over 80%
                System.out.println("Tester batterinivåvarsling for nivå over 80%...");
                int stromniva = 82;
                pushNotifikasjonSender.sendStromnivaNotifikasjon(stromniva);
            }
            else if ("6".equals(input)) {
                // Gå tilbake til hovedmenyen
                System.out.println("Tilbake til hovedmenyen.");
                return;
            } 
            else {
                System.out.println("Ugyldig input. Prøv igjen.");
            }
        }
    }
}
