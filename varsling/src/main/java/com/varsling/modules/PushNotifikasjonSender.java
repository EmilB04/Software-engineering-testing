package com.varsling.modules;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

public class PushNotifikasjonSender {

    /**
     * Metode for å sende en notifikasjon til en spesifikk enhet ved bruk av et Notifikasjon-objekt.
     * Metoden tar inn et Notifikasjon-objekt som inneholder enhetens token, tittel og tekst.
     * Hvis det oppstår en feil under sending av notifikasjonen vil det skrives ut en feilmelding.
     */
    public static void sendNotifikasjon(Notifikasjon notifikasjon) {
        Notification notification = Notification.builder()
                .setTitle(notifikasjon.getTitle())
                .setBody(notifikasjon.getBody())
                .build();

        Message message = Message.builder()
                .setToken(notifikasjon.getToken())
                .setNotification(notification)
                .build();

        try {
            FirebaseMessaging.getInstance().send(message);
            //Utskriften er teknisk sett ikke nødvendig, kan fjernes etter testing
            System.out.println("Notifikasjonen ble sendt");
        } 
        catch (Exception e) {
            System.out.println("Det oppstod en feil under sending av notifikasjonen");
            e.printStackTrace();
        }
    }
}
