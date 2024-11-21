package com.varsel;

import com.varsel.Notifications.FirebaseInitializer;
import com.varsel.Notifications.PushNotifikasjonSender;

public class App 
{
    public static void main(String[] args) {
            
        /**
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
         * 
         * For at koden skal funke må Miljøvariabelen 'GOOGLE_APPLICATION_CREDENTIALS' settes,
         * dette kan gjøres ved å kjøre følgende kommando i terminalen:
         * set GOOGLE_APPLICATION_CREDENTIALS=C:\path\to\your\service-account-file.json
         */

        FirebaseInitializer.initializeFirebaseApp();

        PushNotifikasjonSender pushNotifikasjonSender = new PushNotifikasjonSender();

        //Strømmprisbaserte notifikasjoner
        //Eksempel 1
        boolean tillatelse = true;
        double strompris = 0.3;  
        pushNotifikasjonSender.sendStromprisNotifikasjon(tillatelse, strompris);
        //Forventet output: "Strømmen er billig nå, du burde lade"

        //Eksempel 2
        tillatelse = true;
        strompris = 5.2;
        pushNotifikasjonSender.sendStromprisNotifikasjon(tillatelse, strompris);
        //Forventet output: "Strømmen er over 5 kr per kWh, du burde ikke lade nå hvis du ikke må"

        //Eksempel 3
        tillatelse = false; //Brukeren har ikke gitt tillatelse til å motta notifikasjoner
        strompris = 0.5;
        pushNotifikasjonSender.sendStromprisNotifikasjon(tillatelse, strompris);
        //Forventet output: null(siden brukeren ikke har gitt tillatelse)


        //Batterinivåbaserte notifikasjoner
        //Eksempel 3
        int stromniva = 4;
        pushNotifikasjonSender.sendStromnivaNotifikasjon(stromniva);
        //Forventet output: "Batterinivået er under 5%, du burde lade"

        //Eksempel 4:
        stromniva = 26;
        pushNotifikasjonSender.sendStromnivaNotifikasjon(stromniva);
        //Forventet output: "Batterinivået er mellom 20 og 50%, det er kanskje lurt å lade"

        //Eksempel 5:
        stromniva = 82;
        pushNotifikasjonSender.sendStromnivaNotifikasjon(stromniva);
        //Forventet output: "Batteriet er over 80%, det er ikke hensiktsmessig å lade nå"
    }
    
}
