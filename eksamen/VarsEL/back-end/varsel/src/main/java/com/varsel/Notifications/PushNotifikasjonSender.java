package com.varsel.Notifications;

import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.FirebaseMessaging;

public class PushNotifikasjonSender {

    private NotifikasjonService notifikasjonService;

    public PushNotifikasjonSender() {
        this.notifikasjonService = new NotifikasjonService();
    }

    /**
     * Sender en notifikasjon til Firebase basert på strømpris.
     * @param tillatelse  Angir om sending av notifikasjon er tillatt.
     * @param strompris  Strømprisen som skal brukes til å bestemme hvilken notifikasjon som skal sendes.
     */
    public void sendStromprisNotifikasjon(boolean tillatelse, double strompris) {
        Notifikasjon notifikasjon = notifikasjonService.getStromprisNotifikasjon(tillatelse, strompris);
        // Hvis notifikasjonen ikke er lik null sendes den til Firebase.
        if (notifikasjon != null) {
            sendNotifikasjonTilFirebase(notifikasjon);
        }
    }

    /**
     * Sender en notifikasjon til Firebase basert på batterinivå.
     * @param stromniva  Batterinivået som skal brukes til å bestemme hvilken notifikasjon som skal sendes.
     */
    public void sendStromnivaNotifikasjon(int stromniva) {
        Notifikasjon notifikasjon = notifikasjonService.getStromnivaNotifikasjon(stromniva);
        // Sjekk om notifikasjonen er null, og send den til Firebase hvis den ikke er det.
        if (notifikasjon != null) {
            sendNotifikasjonTilFirebase(notifikasjon);
        }
    }

    /**
     * Privat metode som sender notifikasjonen til Firebase.
     * Denne metoden tar imot en generert notifikasjon og sender den til Firebase.
     * @param notifikasjon  Notifikasjonen som skal sendes til Firebase.
     */
    private void sendNotifikasjonTilFirebase(Notifikasjon notifikasjon) {
        Notification notification = Notification.builder()
                .setTitle(notifikasjon.getTitle()) 
                .setBody(notifikasjon.getBody())
                .build();

        Message message = Message.builder()
                .setToken(notifikasjon.getToken()) 
                .setNotification(notification)     
                .build();

        try {
            // Forsøk å sende meldingen til Firebase
            FirebaseMessaging.getInstance().send(message);
            // Utskriften kan fjærnes når koden er ferdig testet
            System.out.println("Notifikasjonen ble sendt til Firebase.");
        } catch (Exception e) {
            // Hvis det oppstår en feil under sendingen, skriv ut feilmeldingen
            System.out.println("Det oppstod en feil under sending av notifikasjonen");
            e.printStackTrace();
        }
    }
}
