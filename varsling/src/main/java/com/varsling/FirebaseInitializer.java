package com.varsling;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer {

    private static boolean initialized = false;

    /**
     * Bash kommando i terminalen:
     * set GOOGLE_APPLICATION_CREDENTIALS=C:\path\to\your\service-account-file.json
     * ex:
     * set GOOGLE_APPLICATION_CREDENTIALS=C:\Users\idakt\OneDrive - Østfold University College\Dokumenter\Høyskole\Høst24\Software Engineering og testing\Til_gruppeprosjekt\service-account-file.json
     * 
     * Metode for å initialisere Firebase.
     * Metoden sjekker om Firebase allerede er initialisert for å unngå reinitialisering.
     * Hvis Firebase ikke er initialisert, vil metoden initialisere Firebase med 
     * lokalvariabel "GOOGLE_APPLICATION_CREDENTIALS" for å ungå sensitiv informasjon
     * i de offentlig ligjengelige filene.
     * Hvis det oppstår en IOException eller IllegalStateException vil det skrives ut en feilmelding.
     * Hvis Firebase alerede er initialisert gjøres ingenting. 
     */

    public static void initializeFirebaseApp() {
        if (initialized == false) {
            try {
                String serviceAccountPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
                
                if (serviceAccountPath == null || serviceAccountPath.isEmpty()) {
                    throw new IllegalStateException("Miljøvariabelen 'GOOGLE_APPLICATION_CREDENTIALS' er ikke satt.");
                }

                // Initialiserer Firebase
                FileInputStream serviceAccount = new FileInputStream(serviceAccountPath);

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .build();

                FirebaseApp.initializeApp(options);
                initialized = true;

            } catch (IOException e) {
                e.printStackTrace();

            } catch (IllegalStateException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
