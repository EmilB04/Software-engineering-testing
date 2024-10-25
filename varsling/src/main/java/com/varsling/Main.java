package com.varsling;

import com.varsling.modules.FirebaseInitializer;
import com.varsling.modules.Notifikasjon;
import com.varsling.modules.PushNotifikasjonSender;

public class Main {
    public static void main(String[] args) {
        // Initialiser Firebase
        FirebaseInitializer.initializeFirebaseApp();

        // Opprett en notifikasjon
        Notifikasjon notifikasjon = new Notifikasjon("YOUR_DEVICE_FCM_TOKEN", "Test Notifikasjon", "Dette er en test.");

        // Send notifikasjonen
        PushNotifikasjonSender.sendNotifikasjon(notifikasjon);
    }
}
