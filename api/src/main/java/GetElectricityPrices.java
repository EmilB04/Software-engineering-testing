import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetElectricityPrices {
    

    // Henter strømprisdata fra Api
    public List<Double> gettingElectricityPrices(String year, String month, String day, String zone) {
        List<Double> electricityPrices = new ArrayList<>();

        try {
            // Lager URL og bruker Api hentet fra  https://www.hvakosterstrommen.no/strompris-api
            String electricityUrl = "https://www.hvakosterstrommen.no/api/v1/prices/" + year + "/" + month + "-" + day + "_" + zone + ".json";
        }
    }
}
