package com.varsel.Notifications;

public class NotifikasjonService {

    private boolean tillatelse;

    public Notifikasjon getStromprisNotifikasjon(boolean tillatelse, double strompris){
        //Hvis tillatesle ikke er gitt sakl ikke notifikasjoner kunne sendes
        if (!tillatelse) {
            return null;
        }

        String body;
        if (strompris <= 0){
            body = "Strømmen er gratis nå, du burde lade";
        } else if (strompris < 0.5){
            body = "Strømmen er billig nå, du burde lade";
        } else if (strompris >= 1 && strompris <= 3){
            body = "Strømmen er mellom 1 og 3 kr per kWh, du burde vente med å lade";
        } else if (strompris > 5){
            body = "Strømmen er over 5 kr per kWh, du burde ikke lade nå hvis du ikke må";
        } else {
            return null; // Ingen notifikasjon
        }
        return new Notifikasjon("12345", "Strømprisvarsel", body);      
    }

    public Notifikasjon getStromnivaNotifikasjon(int stromniva){
        String body;
        if (stromniva <= 5) {
            body = "Batterinivået er under 5%, du burde lade";
        } else if (stromniva <= 20) {
            body = "Batterinivået er under 20%, du burde lade";
        } else if (stromniva <= 50) {
            body = "Batterinivået er mellom 20 og 50%, det er kasnkje lurt å lade";
        } else if (stromniva <= 79) {
            body = "Batterinivået er mellom 50 og 80%, du kan vente med å lade";
        } else {
            body = "Batteriet er over 80%, det er ikke hensiktsmessig å lade nå";
        }

        return new Notifikasjon("12345", "Batterinotifikasjon", body);       
    }

    //Getter og setter
    public boolean getTillatelse() {
        return tillatelse;
    }

    public void setTillatelse(boolean tillatelse) {
        this.tillatelse = tillatelse;
    }
}
