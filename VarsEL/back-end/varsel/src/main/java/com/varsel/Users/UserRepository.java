package com.varsel.Users;

import java.io.*;

/**
 * Denne klassen håndterer lagring og uthenting av brukere fra en tekstfil.
 */
public class UserRepository {

    private final String filePath;

    /**
     * Konstruktør som angir filbane og sørger for at filen eksisterer.
     *
     * @param filePath Stien til tekstfilen som brukes til å lagre brukerdata
     * @throws IOException hvis det oppstår en feil ved filhåndtering
     */
    public UserRepository(String filePath) throws IOException {
        this.filePath = filePath;
        ensureFileExists();  // Sørg for at filen eksisterer
    }

    /**
     * Sjekker om filen finnes, og oppretter den hvis den ikke gjør det.
     *
     * @throws IOException hvis det oppstår en feil ved opprettelse av filen
     */
    private void ensureFileExists() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            if (file.createNewFile()) {
                System.out.println("Filen ble opprettet: " + filePath);
            } else {
                System.out.println("Kunne ikke opprette filen: " + filePath);
            }
        }
    }

    /**
     * Lagrer en ny bruker i tekstfilen.
     *
     * @param user Brukerobjekt som skal lagres
     * @throws IOException hvis det oppstår en feil ved skriving til filen
     */
    public void saveUser(User user) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(user.getEmail() + ":" + user.getHashedPassword());
            writer.newLine();
        }
    }

    /**
     * Finner en bruker i filen basert på e-postadresse.
     *
     * @param email E-postadresse til brukeren som skal finnes
     * @return Brukerobjekt hvis brukeren finnes, ellers null
     * @throws IOException hvis det oppstår en feil ved lesing fra filen
     */
    public User findUserByEmail(String email) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equals(email)) {
                    return new User(parts[0], parts[1]);
                }
            }
        }
        return null;
    }
}
