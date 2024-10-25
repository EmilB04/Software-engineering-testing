package com.varsling;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer {

    private static boolean initialized = false;

    /**
     * Metode for å initialisere Firebase.
     * Metoden sjekker om Firebase allerede er initialisert for å unngå reinitialisering.
     * Hvis Firebase ikke er initialisert, vil metoden initialisere Firebase med service-account-file.JSON filen.
     * Hvis det oppstår en IOException, vil det skrives ut en feilmelding.
     * Hvis Firebase alerede er initialisert, skrives det ut som en melding.
     */

    public static void initializeFirebaseApp() {
        if (initialized == false) {
            try {
                //Initialiserer Firebase
                FileInputStream serviceAccount = new FileInputStream("service-account-file.json");

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);
                initialized = true;

            } 
            //Feil ved initialisering
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Hvis Firebase allerede er initialisert gjøres ingenting 
    }
}
