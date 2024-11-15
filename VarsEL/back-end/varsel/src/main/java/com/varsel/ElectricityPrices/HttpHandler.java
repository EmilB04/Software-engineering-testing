package com.varsel.ElectricityPrices;
import com.varsel.Utility.HttpClient;

import java.io.IOException;
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
 * HttpHandler httpHandler = new HttpHandler(new HttpGetClient());
 * String jsonData = httpHandler.getJSONDataFromUrl("exampleURL");
 * </pre>
 */
public class HttpHandler {

    private static final Logger logger = LoggerFactory.getLogger(HttpHandler.class);
    private final HttpClient httpClient; 

    public HttpHandler(HttpClient httpClient) {
        this.httpClient = httpClient; 
    }

    /**
     * Retrieves JSON-data from a URL
     * 
     * @param urlInput the URL for the API
     * @return JSON data as a String
     * @throws IOException if a network error occurs
     * @throws URISyntaxException if URL is invalid
     */
    public String getJSONDataFromUrl (String urlInput) throws IOException, URISyntaxException { 
        try {
            String fetchedData = httpClient.get(urlInput);
            logger.info("Fetched data from site: "); 
            return fetchedData;
        } catch (IOException | URISyntaxException e) {
            logger.error("An error occurred while retrieving data from the site ", e);
            throw e;
        }
    }
}
