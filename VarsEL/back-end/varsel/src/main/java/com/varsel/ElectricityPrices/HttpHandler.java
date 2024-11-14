package com.varsel.ElectricityPrices;

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
 * Handles HTTP requests to get JSON data from a URL
 * 
 * The class sends GET requests to a URLs, retrieving JSON-data and returns the data as a String.
 * 
 * Example usage:
 * <pre>
 * HttpHandler httpHandler = HttpHandler();
 * String jsonData = httpHandler.getJSONDataFromUrl("exampleURL");
 * </pre>
 */
public class HttpHandler {

    private static final Logger logger = LoggerFactory.getLogger(HttpHandler.class);

    /**
     * Retrieves JSON-data from a URL
     * 
     * @param urlInput the URL for the API
     * @return JSON data as a String
     * @throws IOException if a network error occurs
     */
    public String getJSONDataFromUrl (String urlInput) throws IOException, URISyntaxException { 

        StringBuilder data = new StringBuilder(); 
        HttpURLConnection connection = null;

        try {
            URI uri = new URI(urlInput);
            URL url = uri.toURL(); 

            connection = (HttpURLConnection) url.openConnection(); 
            connection.setRequestMethod("GET");
            logger.info("Connected to: {}", urlInput);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String infoLine;
            while ((infoLine = in.readLine()) != null) {
                data.append(infoLine);
            }

            in.close();
            logger.info("Fetched data from site: "); 
        } catch (Exception e) {
            logger.error("An error occurred while retrieving data from the site ", e);
            throw e;
         } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return data.toString();
    }
}
