package com.varsel.Notifications;

import java.util.Scanner;

public class NotificationMenu {
    private final PushNotifikasjonSender pushNotifikasjonSender;

    // Constructor for dependency injection
    public NotificationMenu(PushNotifikasjonSender pushNotifikasjonSender) {
        this.pushNotifikasjonSender = pushNotifikasjonSender;
    }

    // Method to display the notification menu
    public void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nVarslingsmeny:");
            System.out.println("1: Test strømprisbasert varsling (Eksempel 1)");
            System.out.println("2: Test strømprisbasert varsling (Eksempel 2)");
            System.out.println("3: Test strømprisbasert varsling (Eksempel 3)");
            System.out.println("4: Test batterinivåbasert varsling (Eksempel 1)");
            System.out.println("5: Test batterinivåbasert varsling (Eksempel 2)");
            System.out.println("6: Test batterinivåbasert varsling (Eksempel 3)");
            System.out.println("7: Tilbake til hovedmenyen");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> testStromprisNotifikasjon(true, 0.3);
                case "2" -> testStromprisNotifikasjon(true, 5.2);
                case "3" -> testStromprisNotifikasjon(false, 0.5);
                case "4" -> testStromnivaNotifikasjon(4);
                case "5" -> testStromnivaNotifikasjon(26);
                case "6" -> testStromnivaNotifikasjon(82);
                case "7" -> {
                    System.out.println("Tilbake til hovedmenyen.");
                    return;
                }
                default -> System.out.println("Ugyldig input. Prøv igjen.");
            }
        }
    }

    // Helper method to test strømprisbasert varsling
    private void testStromprisNotifikasjon(boolean tillatelse, double strompris) {
        System.out.println("\nTester strømprisbasert varsling...");
        pushNotifikasjonSender.sendStromprisNotifikasjon(tillatelse, strompris);
    }

    // Helper method to test batterinivåbasert varsling
    private void testStromnivaNotifikasjon(int stromniva) {
        System.out.println("\nTester batterinivåbasert varsling...");
        pushNotifikasjonSender.sendStromnivaNotifikasjon(stromniva);
    }
}
