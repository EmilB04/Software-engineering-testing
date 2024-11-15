package com.varsel.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Handles HTTP GET requests to get data from a URL. 
 * 
 * This class sends GET requests and turn the response to a String.
 * 
 * Example usage:
 * <pre>
 * HttpCLient client = new HttpGetClient(); 
 * String apiResponse = client.get("https://api.apiexamplesite.com")
 * </pre>
 */

public class HttpGetClient implements HttpClient {

    private static final Logger logger = LoggerFactory.getLogger(HttpGetClient.class);

    /**
     * Sends GET requests to a URL and makes the response into a String
     * 
     * @param urlInput, the URL who recieves GET requests
     * @return Response as a String
     * @throws IoException If there's problems during HTTP requests or when reading the response
     * @throws URISyntaxException if there's a problem with the URL. 
     */
    @Override
    public String get(String urlInput) throws IOException, URISyntaxException {

        StringBuilder data = new StringBuilder();
        HttpURLConnection connection = null;

        try {
            URI uri = new URI(urlInput);
            URL url = uri.toURL();

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            logger.info("Connected to: {}", urlInput);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String infoLine; 
                while ((infoLine = in.readLine()) != null) {
                    data.append(infoLine);
                }
            }
            logger.info("Fetched data from: {}", urlInput);
        } catch (IOException | URISyntaxException e) {
            logger.error("An error occurred while retrieving data from the site", e);
            throw e; 
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return data.toString();
    }
    
}
