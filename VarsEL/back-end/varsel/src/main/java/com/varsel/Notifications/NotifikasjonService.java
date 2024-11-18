package com.varsel.Notifications;

public class NotifikasjonService {

    private boolean tillatelse;

    
    public Notifikasjon getStromprisNotifikasjon(boolean tillatelse, double strompris) {
        if (tillatelse) {
            if (strompris <= 0) {
                return new Notifikasjon("12345", "Strømprisvarsel", "Strømmen er gratis nå, du burde lade");
            } else if (strompris < 0.5) {
                return new Notifikasjon("12345", "Strømprisvarsel", "Strømmen er billig nå, du burde lade");
            } else if (strompris > 1.5) {
                return new Notifikasjon("12345", "Strømprisvarsel", "Strømmen er over 1.5 kr per kWh, du burde vente med å lade");
            } else if (strompris > 5) {
                return new Notifikasjon("12345", "Strømprisvarsel", "Strømmen er over 5 kr per kWh, du burde ikke lade nå hvis du ikke må");
            }
        }
        return null; // Returner null hvis ingen notifikasjon skal sendes
    }

    public Notifikasjon getStromnivaNotifikasjon(String lokasjon, int stromniva) {
        if ("Home".equals(lokasjon)) {
            if (stromniva <= 5) {
                return new Notifikasjon("12345", "Batterinotifikasjon", "Batterinivået er under 5%, du burde lade");
            } else if (stromniva < 20) {
                return new Notifikasjon("12345", "Batterinotifikasjon", "Batterinivået er under 20%, du burde lade");
            } else if (stromniva > 50 && stromniva <= 79) {
                return new Notifikasjon("12345", "Batterinotifikasjon", "Batterinivået er over 50%");
            } else if (stromniva > 80) {
                return new Notifikasjon("12345", "Batterinotifikasjon", "Batteriet er over 80%, det er ikke hensiktsmessig å lade nå");
            }
        }
        return null; // Returner null hvis ingen notifikasjon skal sendes
    }

    //Getter og setter
    public boolean getTillatelse() {
        return tillatelse;
    }

    public void setTillatelse(boolean tillatelse) {
        this.tillatelse = tillatelse;
    }
}
