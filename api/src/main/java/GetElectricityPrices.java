import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetElectricityPrices {

    // Lager en logger for ekstra informasjon når vi kjører klassen
    private static final Logger logger = LoggerFactory.getLogger(GetElectricityPrices.class);
    

    // Henter strømprisdata fra Api
    public List<Double> gettingElectricityPrices(String year, String month, String day, String zone) {
        List<Double> electricityPrices = new ArrayList<>();

        // Plasserer hovedkoden i en try/except block
        try {
            // Lager URL og bruker API hentet fra  https://www.hvakosterstrommen.no/strompris-api
            String electricityUrl = "https://www.hvakosterstrommen.no/api/v1/prices/" + year + "/" + month + "-" + day + "_" + zone + ".json";
            // Logger hvilken URL som har blitt brukt
            logger.info("Henter data fra: {}", electricityUrl);
            URL url = new URL(electricityUrl);

            // Åpner tilkoblingen til serveren via API
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
            connection.setRequestMethod("GET");

            // Leser API responsen
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String infoLine;
            // Samler alle linjene/data i en string builder
            StringBuilder data = new StringBuilder();

            // Hvis infoLine ikke er tom appender data infoLine
            while ((infoLine = in.readLine()) != null) {
                data.append(infoLine);
            }

            in.close();

            // Kobler fra serveren 
            connection.disconnect();

            // Parse JSON data ved å bruke toString();
            JSONArray jsonArray = new JSONArray(data.toString());
            // Logger antall objekter som har blitt hentet
            logger.info("Hentet: {} objekter", jsonArray.length());

            // Bruker en for loop for å iterere gjennom arrayet for å hente data
            for (int i =0; i < jsonArray.length(); i++) {
                JSONObject electricityPriceObject = jsonArray.getJSONObject(i);

                // Henter ut strømpris per time
                double price = electricityPriceObject.getDouble("NOK_per_kWh");

                // Legger til pris i electricityPrices
                electricityPrices.add(price);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Det skjedd en feil under henting av strømpriser: ", e);
        }

        // Returnerer strømpriser i listeform 
        return electricityPrices; 
    }

    // Main metode for teste koden
    public static void main(String[] args) {
        GetElectricityPrices electricityPricesFetcher = new GetElectricityPrices();
        List<Double> prices = electricityPricesFetcher.gettingElectricityPrices("2024", "10", "17", "NO1");

        // Skriv ut prisene for å se om de er hentet
        System.out.println(prices);
    }
}
