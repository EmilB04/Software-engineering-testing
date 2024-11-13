/*package com.varsel.GPS;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
public class LocationService implements LocationListener{
    private LocationManager locationManager; // Håndterer tilgang til lokasjonstjenester
    private Context context;                 // Konteksten for applikasjonen
    private boolean isAtHome;                // Variabel for å lagre om bruker er hjemme

    // Konstruktør som tar inn Context og setter opp LocationManager
    public LocationService (Context context){
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    //Starter posisjonsopptaderinger
    public void setLocationUpdates() {
        try {
            //Spør om posisjonsoppdateringer via GPS
           locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        } catch (SecurityException e) {
            e.printStackTrace(); //Logger hvis det mangler tillatelse
        }
    }

    //Stopper posisjonsoppdateringer
    public void stopLocationUpdates(){
        locationManager.removeUpdates(this); //Stopper GPS - oppdateringer
    }

    //Returnerer true dersom bruker er hjemme, ellers false
    public boolean isAtHome() {
        return isAtHome;
    }

    @Override
    public void onLocationChanged(Location location) {
        // Kaller HomeChecker for å sjekke om brukeren er hjemme
        isAtHome = HomeChecker.isWithinHomeRadius(
                location.getLatitude(),
                location.getLongitude()
        );
    }

    // Metoder for LocationListener
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}
}
*/
