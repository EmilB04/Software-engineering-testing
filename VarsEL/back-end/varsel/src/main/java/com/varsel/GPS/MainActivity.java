package com.varsel.GPS;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView; 
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    private LocationService locationService; // Variabel for å lagre LocationService
    private TextView statusTextView;         // TextView for å vise brukerens status

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Setter opp layout for aktiviteten

        //Oppretter en ny LocationService og starter posisjonsoppdateringer
        locationService = new LocationService (this);
        locationService.setLocationUpdates();

        //Referanse til TextView for status i UI
        statusTextView = findViewById(R.id.statusTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Oppdaterer staus når appen er aktiv igjen
        updateHomeStatus();
    }

    // Sjekker og oppdaterer brukerens status (hjemme eller ikke) i UI
    private void updateHomeStatus(){
        if (locationService.isAtHome()){
            // Brukeren er hjemme
             statusTextView.setText("Du er hjemme!");
        } else {
            //Brukeren er ikke hjemme
            statusTextView.setText("Du er ikke hjemme.");
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        // Stopper posisjoneringsoppdateringer når appen avsluttes
        locationService.stopLocationUpdates();
    }
}
    
