import android.location.Location;

public class HomeChecker {
    // Hjem kordinatorer og radius for å definere hvor "hjemmet" er
    private static final double HOME_LATITUDE = 59.911491; // Breddegrad (Latitude) for hjem, endre til riktig verdi
    private static final double HOME_LONGITUDE = 10.757933; // Lengdegrad (Longitude) for hjem, endre til riktig verdi
    private static final float HOME_RADIUS = 100; //Radius i meter rundt "hjemmet"


    //Metode som sjekker om bruker er innenfor "hjemmeradius"
    public static boolean isWithinHomeRadius(double latitude, double longitude){
        // resultatet vil lagre avstanden mellom "hjemmet" og nåværende posisjon
        float[] result = new float[1];
    }
}
